package com.mortegagarcia.gradebook;

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

import java.net.URI;
import java.util.Objects;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class GradebookApplicationTests {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    private TestRestTemplate restTest;

    @Test
    void testGetProfessorByID1Success() throws Exception {
        final int professorID = 1;
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/professor/" + professorID;
        URI uri = new URI(baseUrl);
        ResponseEntity<ProfessorDTO> response = restTest.getForEntity(uri, ProfessorDTO.class);
        System.out.println("--test-result-- " + Objects.requireNonNull(response.getBody()).getFirstName());

        // assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getFirstName()).isEqualTo("Mario");
    }

    @Test
    void testAddProfessorSuccess() throws Exception {
        // act
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/professor/";
        URI uri = new URI(baseUrl);
        ProfessorDTO professor = new ProfessorDTO()
                .setId(9999)
                .setEmail("test@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPhoneNumber("5555555555");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ProfessorDTO> entity = new HttpEntity<>(professor, headers);

        ResponseEntity<ProfessorDTO> response = restTest.postForEntity(uri, entity, ProfessorDTO.class);

        // assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getFirstName()).isEqualTo("firstName");
    }

    @Test
    void testAddProfessorMissingHeader() throws Exception {
        // act
        final String baseUrl = "http://localhost:" + randomServerPort + "/api/professor/";
        URI uri = new URI(baseUrl);
        ProfessorDTO professor = new ProfessorDTO()
                .setId(9999)
                .setEmail("test@email.com")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPhoneNumber("5555555555");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ProfessorDTO> entity = new HttpEntity<>(professor, headers);

        ResponseEntity<ProfessorDTO> response = restTest.postForEntity(uri, entity, ProfessorDTO.class);

        // assert
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(Objects.requireNonNull(response.getBody()).getFirstName()).isEqualTo("firstName");
    }
}
