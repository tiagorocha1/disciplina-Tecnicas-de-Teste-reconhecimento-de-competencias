package br.edu.ifpb.dac.trainee.controller.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.trainee.controller.dto.TokenDto;
import br.edu.ifpb.dac.trainee.controller.dto.form.LoginForm;
import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.model.repository.UserRepository;
import br.edu.ifpb.dac.trainee.service.auth.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken dadosLogin = form.toConvert();
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			User user = (User) authentication.getPrincipal();
			String token = tokenService.gerarToken(user);

			return ResponseEntity.ok(new TokenDto(token, true));
		} catch (AuthenticationException e) {

			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("validateToken")	
	public ResponseEntity<TokenDto> validateToken(String token) {
		System.out.println(token);
				
		if(tokenService.isValid(token)) {
			return ResponseEntity.ok().body(new TokenDto(token, true));	
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@PostMapping("signup")	
	public boolean signup(String email, String password) {
							
		User user = new User(email,password);
		
		if(userRepository.save(user) != null ) {
			return true;
		}
		
		return false;
	}
}

