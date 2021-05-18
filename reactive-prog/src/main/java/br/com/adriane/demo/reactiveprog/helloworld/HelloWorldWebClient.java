package br.com.adriane.demo.reactiveprog.helloworld;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class HelloWorldWebClient {

    private final WebClient client = WebClient.create("http://localhost:8080");

    private final Mono<ClientResponse> result = client.get()
            .uri("/hello")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(ClientResponse.class);

    public String getResult() {
        return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class)).block();
    }
}
