package br.edu.ifpb.dac.trainee.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.edu.ifpb.dac.trainee.repository.UserRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired	
	private AutenticationService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository usuarioRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
	
		return super.authenticationManager();
	}
	
	//Configurações de  autenticação
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(autenticacaoService) 
			.passwordEncoder(new BCryptPasswordEncoder());
	
	}
	
	//Configurações de autorização
	/**
	 *
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http		
			.authorizeHttpRequests()
			.antMatchers(HttpMethod.GET,"/tarefas").permitAll()
			.antMatchers(HttpMethod.GET,"/tarefas/*").permitAll()
			.antMatchers(HttpMethod.POST,"/auth/**").permitAll()
			.antMatchers(HttpMethod.GET,"/actuator/**").permitAll()
			.antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()	
			.antMatchers(HttpMethod.DELETE, "/tarefas/*").hasRole("ADMINISTRADOR")		    
			.anyRequest().authenticated()
			.and()
			.cors().and().csrf().disable()						
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AutenticationViaTokenFilter(tokenService,usuarioRepository), UsernamePasswordAuthenticationFilter.class);				
	}
	
	//Configurações de recursos estaticos (css, js, etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
		 web.ignoring()
	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	

	
	
	
	
}

