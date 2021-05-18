package br.com.adriane.demo.reactiveprog.controller;

import br.com.adriane.demo.reactiveprog.clients.HttpBinClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bin")
public class BinController {

  private final HttpBinClient binClient;

  public BinController(HttpBinClient binClient) {
    this.binClient = binClient;
  }

  @GetMapping
  public Mono<String> webClientRequest() {
    return WebClient.create("https://httpbin.org/")
      .get()
      .uri("/anything")
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(String.class).log();
  }

  @GetMapping("/client")
  public Mono<String> httpClientRequest() {
    return binClient.anything();
  }

}
