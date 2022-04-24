package br.edu.ifpb.dac.trainee.service.auth;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifpb.dac.trainee.model.User;

@SpringBootTest
class AutenticationServiceTest {

	@Autowired
	private AutenticationService autenticationService;

	@Test
	void testLoadUserByUsernameNotExist() {
		String userNameNotExist = "notExist@email";

		assertThrows(UsernameNotFoundException.class, 
				() -> autenticationService.loadUserByUsername(userNameNotExist));
	}

	@Test
	void testLoadUserByUsernameExist() {

		String userNameExist = "admin@task";
		User user = (User) autenticationService.loadUserByUsername(userNameExist);
		assertAll(
				() -> assertNotNull(user),
				() -> assertTrue(user.getId() > 0)
				);

	}

	
}

