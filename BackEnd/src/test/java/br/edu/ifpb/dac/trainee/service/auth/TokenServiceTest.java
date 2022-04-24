package br.edu.ifpb.dac.trainee.service.auth;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.ifpb.dac.trainee.model.User;

@SpringBootTest
class TokenServiceTest {

	@Autowired
	private TokenService tokenService;

	@Test
	void testValidToken() {

		User user = new User(1l,"test@task.app", "123");

		String token = tokenService.gerarToken(user);

		assertEquals(true, tokenService.isValid(token));

	}

	@Test
	void testInValidToken() {

		String tokenValid = "Bh#4q!f3ZYW%vpDOx2aK7CU$00MNyPk2b^xHr#nHh$%a@tOZ0K";

		assertEquals(false, tokenService.isValid(tokenValid));
	}

}
