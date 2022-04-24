package br.edu.ifpb.dac.trainee.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserTest {

	private BCryptPasswordEncoder encoder;

	@BeforeEach
	public void initialeTestUser() {
		this.encoder = new BCryptPasswordEncoder();
	}

	@Test
	void testPassword123Encode() {
		User user = new User("test@task.app", "123");

		assertTrue(encoder.matches("123", user.getPassword()));
	}

	@Test
	void testIfDifferentHashesAreGeneratedForTheSamePassword() {

		User user1 = new User("test@task.app", "123");
		User user2 = new User("test2@task.app", "123");

		assertNotEquals(user1.getPassword(), user2.getPassword());
	}

}



