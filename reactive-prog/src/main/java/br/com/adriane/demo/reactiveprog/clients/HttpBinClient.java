package br.com.adriane.demo.reactiveprog.clients;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "binClient", url = "https://httpbin.org/")
public interface HttpBinClient {
  @GetMapping(value = "/anything", consumes = MediaType.APPLICATION_JSON_VALUE)
  Mono<String> anything();
}
