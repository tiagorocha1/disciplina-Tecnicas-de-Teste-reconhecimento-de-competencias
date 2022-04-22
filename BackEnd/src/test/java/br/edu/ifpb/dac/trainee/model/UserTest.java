package br.edu.ifpb.dac.trainee.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class UserTest {

	private String passwordTest;
	private User testUser;
	private BCryptPasswordEncoder encoder;

	@BeforeEach
	public void initialeTestUser() {

		this.encoder = new BCryptPasswordEncoder();
		this.passwordTest = "123";
		this.testUser = new User("email@teste", passwordTest);
	}

	@Test
	void testPassword123Encode() {
		String encode = encoder.encode("123");
		assertTrue(encoder.matches("123", encode));
	}

	@Test
	void testUserPasswordEncode() {

		assertTrue(encoder.matches("123", this.testUser.getPassword()));
	}

	void testIfDifferentHashesAreGeneratedForTheSamePassword() {
		String passwordEncode1 = encoder.encode("123");
		String passwordEncode2 = testUser.getPassword();

		assertNotEquals(passwordEncode1, passwordEncode2);
	}

}
