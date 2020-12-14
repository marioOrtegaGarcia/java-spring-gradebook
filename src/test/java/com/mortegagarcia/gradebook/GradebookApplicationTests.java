package com.mortegagarcia.gradebook;

import com.mortegagarcia.gradebook.model.Professor;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GradebookApplicationTests {

	@Autowired
	private TestRestTemplate restTest;

	@Test
	void contextLoads() throws Exception {
		// arrange

		// act
		ResponseEntity<Professor> response = restTest.getForEntity("/api/professor/1", Professor.class);

		// assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("Mario");

	}

}
