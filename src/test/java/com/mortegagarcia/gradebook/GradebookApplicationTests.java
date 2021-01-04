package com.mortegagarcia.gradebook;

import java.net.URI;

import com.mortegagarcia.gradebook.dto.ProfessorDTO;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GradebookApplicationTests {

	@Autowired
	private TestRestTemplate restTest;

	@LocalServerPort
	int randomServerPort;

	@Test
	void testGetProfessorByID1Success() throws Exception {
		// arrange

		// act
		final int professorID = 1;
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/professor/" + professorID;
		URI uri = new URI(baseUrl);
		ResponseEntity<ProfessorDTO> response = restTest.getForEntity(uri, ProfessorDTO.class);
		System.out.println("--test-result--" + response.getBody().getFirstName());

		// assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("Mario");

	}

	@Test
	void testAddProfessorSuccess() throws Exception {
		// arrange

		// act
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/professor/";
		URI uri = new URI(baseUrl);
		ProfessorDTO professor = new ProfessorDTO(9999, "test@email.com", "firstName", "lastName", "5555555555");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<ProfessorDTO> entity = new HttpEntity<>(professor, headers);

		ResponseEntity<ProfessorDTO> response = restTest.postForEntity(uri, entity, ProfessorDTO.class);

		// assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("firstName");

	}

	@Test
	void testAddProfessorMissingHeader() throws Exception {
		// arrange

		// act
		final String baseUrl = "http://localhost:" + randomServerPort + "/api/professor/";
		URI uri = new URI(baseUrl);
		ProfessorDTO professor = new ProfessorDTO(9999, "test@email.com", "firstName", "lastName", "5555555555");

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<ProfessorDTO> entity = new HttpEntity<>(professor, headers);

		ResponseEntity<ProfessorDTO> response = restTest.postForEntity(uri, entity, ProfessorDTO.class);

		// assert
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("firstName");

	}
}
