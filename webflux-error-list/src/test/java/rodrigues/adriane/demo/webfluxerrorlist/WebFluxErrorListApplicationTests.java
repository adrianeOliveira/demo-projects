package rodrigues.adriane.demo.webfluxerrorlist;

import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import rodrigues.adriane.demo.webfluxerrorlist.request.CreateContactRequest;

@SpringBootTest
@AutoConfigureWebTestClient
class WebFluxErrorListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void givenANewContact_WhenCreaeteNewContact_ThenReturnNoContent() {
		CreateContactRequest request = new CreateContactRequest();

		request.setName("Adriane Rodrigues");
		request.setEmail("rodrigue.adriane@outlook.com");

		webTestClient.post()
				.uri("/api")
					.accept(MediaType.APPLICATION_JSON)
					.bodyValue(request)
				.exchange()
				.expectStatus()
					.isNoContent();

	}

	@Test
	void givenAContactId_WhenSearchForContact_ThenReturnTheContact() {
		webTestClient.get()
				.uri("/api/1")
				.exchange()
				.expectStatus()
					.isOk()
				.expectBody()
					.json("{\"id\": 1,\"name\": \"Jessica\",\"email\": \"jessica@email.com\"}");

	}

}
