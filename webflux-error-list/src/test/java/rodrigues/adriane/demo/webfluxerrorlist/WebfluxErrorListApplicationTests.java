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
class WebfluxErrorListApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
		CreateContactRequest request = new CreateContactRequest();

		request.setName("Adriane Rodrigues");
		request.setEmail("rodrigue.adriane@outlook.com");

		webTestClient.post()
				.uri("/api")
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(request)
				.exchange()
				.expectStatus().isNoContent();

	}

}
