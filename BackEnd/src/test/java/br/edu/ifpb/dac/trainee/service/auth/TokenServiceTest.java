package br.edu.ifpb.dac.trainee.service.auth;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootTest
class TokenServiceTest {

	@Value("${trainee.jwt.expiration}")
	private String expiration;

	@Value("${trainee.jwt.secret}")
	private String secret;

	@Autowired
	private TokenService tokenService;

	@Test
	void testValidToken() {
		Date hoje = new Date();
		Date dataDeExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

		String tokenValid = Jwts.builder().setIssuer("Api System Trainee").setSubject(1l + "").setIssuedAt(hoje)
				.setExpiration(dataDeExpiracao).signWith(SignatureAlgorithm.HS256, secret).compact();
		
		assertEquals(true, tokenService.isValid(tokenValid));

	}
	
	@Test
	void testInValidToken() {
		
		String tokenValid = "kldshfshdiohfishdfiohsdifhisdhifhsidhfiosdhfiohsidfhioshfihisdhifhsdifs";
		
		assertEquals(false, tokenService.isValid(tokenValid));
	}


}
