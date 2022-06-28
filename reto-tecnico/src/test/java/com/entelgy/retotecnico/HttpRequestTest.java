package com.entelgy.retotecnico;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void respuestaDeControllerAlMetodoGet() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/reto",	String.class)).contains("se aplico get exitosamente");
	}

	@Test
	public void respuestaDeControllerAlMetodoPost() throws Exception {
		String response="{\n\"data\": [\n\"1|Bluth|george.bluth@reqres.in\",\n\"2|Weaver|janet.weaver@reqres.in\",\n\"3|Wong|emma.wong@reqres.in\",\n\"4|Holt|eve.holt@reqres.in\",\n\"5|Morris|charles.morris@reqres.in\",\n\"6|Ramos|tracey.ramos@reqres.in\"\n]\n}";
		String request = "{\"accion\":\"si\"}";

		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/reto", request ,String.class)).contains(response);
	}
}