package br.com.adriane.demo.jaxrs

import io.restassured.RestAssured
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JaxRsApplicationTests {

	@LocalServerPort
	private val port = 8080

	@Test
	fun getContactsWithSuccess() {
		RestAssured
			.given()
				.port(port)
			.`when`()
				.get("/contacts")
			.then()
				.statusCode(HttpStatus.SC_OK)
	}

}
