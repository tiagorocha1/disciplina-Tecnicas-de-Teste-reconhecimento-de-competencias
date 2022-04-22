package br.edu.ifpb.dac.trainee.config.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.trainee.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${trainee.jwt.expiration}")
	private String expiration;

	@Value("${trainee.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		User logado = (User) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataDeExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder().setIssuer("Api System Trainee")
							.setSubject(logado.getId().toString())
							.setIssuedAt(hoje)
							.setExpiration(dataDeExpiracao)
							.signWith(SignatureAlgorithm.HS256, secret)
							.compact();
	}

	public boolean isValid(String token) {

		try {

			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);

			return true;
		} catch (Exception e) {
			// Token invalido gera exception
			return false;
		}

	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	public String recuperarToken(HttpServletRequest request) {

		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || token.isBlank() || !token.startsWith("Bearer ") ) {
			return null;
		}
		
		
		return token.substring(7, token.length());
	}

	

}
