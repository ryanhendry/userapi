package com.ryanhendry.userapi;

import static org.junit.jupiter.api.Assertions.*;

import com.ryanhendry.userapi.domain.entity.dao.UserDao;
import com.ryanhendry.userapi.domain.entity.dto.UserDto;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void saveUser() {
		UserDto request = new UserDto(
				"AzureDiamond1",
				"Hunter2",
				LocalDate.of(2002,5,16),
				"7463812635382615");
		UserDao result = this.restTemplate.postForObject(String.format("http://localhost:%d/register", port), request, UserDao.class);
		assertEquals(request.getUsername(), result.getUsername());
		assertEquals(request.getPassword(), result.getPassword());
		assertEquals(request.getDob(), result.getDob());
		assertEquals(request.getPaymentCardNumber(), result.getPan());
	}

	@ParameterizedTest
	@ValueSource(strings = {"Azure Diamond", ""})
	void usernameValidation(String username) {
		UserDto request = new UserDto(
				username,
				"Hunter2",
				LocalDate.of(2002,5,16),
				"7463812635382615");
		ResponseEntity<UserDao> result = this.restTemplate.postForEntity(
				String.format("http://localhost:%d/register", port), request, UserDao.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@ParameterizedTest
	@ValueSource(strings = {"hunter2", "", "Hunter", "Hu2", "hunter"})
	void passwordValidation(String password) {
		UserDto request = new UserDto(
				"AzureDiamond",
				password,
				LocalDate.of(2002,5,16),
				"7463812635382615");
		ResponseEntity<UserDao> result = this.restTemplate.postForEntity(
				String.format("http://localhost:%d/register", port), request, UserDao.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	void dobValidation() {
		UserDto request = new UserDto(
				"AzureDiamond",
				"Hunter2",
				null,
				"7463812635382615");
		ResponseEntity<UserDao> result = this.restTemplate.postForEntity(
				String.format("http://localhost:%d/register", port), request, UserDao.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@ParameterizedTest
	@ValueSource(strings = {"7463", "", "11111111111111", "99999999999999999999"})
	void paymentCardValidation(String paymentCardNumber) {
		UserDto request = new UserDto(
				"AzureDiamond",
				"Hunter2",
				LocalDate.of(2002,5,16),
				paymentCardNumber
				);
		ResponseEntity<UserDao> result = this.restTemplate.postForEntity(
				String.format("http://localhost:%d/register", port), request, UserDao.class);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
}
