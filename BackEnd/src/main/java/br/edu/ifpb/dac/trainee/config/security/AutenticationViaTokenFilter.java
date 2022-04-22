package br.edu.ifpb.dac.trainee.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.repository.UserRepository;

public class AutenticationViaTokenFilter  extends OncePerRequestFilter{
	
	private TokenService tokenService;
	
	private UserRepository usuarioRepository;
	
	public AutenticationViaTokenFilter (TokenService tokenService, UserRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
			
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = tokenService.recuperarToken(request);
		
		boolean valido = tokenService.isValid(token);
	
		if(valido) {
			autenticarUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		User usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}


	
}
