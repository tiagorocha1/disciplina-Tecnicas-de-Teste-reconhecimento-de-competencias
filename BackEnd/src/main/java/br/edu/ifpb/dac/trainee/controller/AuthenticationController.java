package br.edu.ifpb.dac.trainee.controller;

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

import br.edu.ifpb.dac.trainee.config.security.TokenService;
import br.edu.ifpb.dac.trainee.controller.form.LoginForm;
import br.edu.ifpb.dac.trainee.model.User;
import br.edu.ifpb.dac.trainee.repository.UserRepository;

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

			String token = tokenService.gerarToken(authentication);

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

