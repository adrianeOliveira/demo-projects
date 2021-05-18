package br.com.adriane.demo.reactiveprog.clients;

import feign.Headers;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Headers({ "Accept: application/json" })
@ReactiveFeignClient(name = "binClient", url = "https://httpbin.org/")
public interface HttpBinClient {
  @GetMapping(value = "/anything")
  Mono<String> anything();
}
