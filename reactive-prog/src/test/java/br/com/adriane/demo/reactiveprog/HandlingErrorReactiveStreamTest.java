package br.com.adriane.demo.reactiveprog;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class HandlingErrorReactiveStreamTest {

    @Test
    public void testOnErrorResume() {
        Flux<Boolean> integerFlux = Flux.just(true, true)
            .concatWith(Flux.error(new RuntimeException("Erro lançado")))
            .concatWith(Flux.just(true))
            .onErrorResume(e -> {
                System.out.println("onErrorResume");
                return Flux.just(false);
            });

        StepVerifier.create(integerFlux.log())
            .expectNext(Boolean.TRUE, Boolean.TRUE)
            .expectNext(Boolean.FALSE)
        .verifyComplete();
    }

    @Test
    public void testOnErrorReturn() {
        Flux<Boolean> integerFlux = Flux.just(true, true)
            .concatWith(Flux.error(new RuntimeException("Erro lançado")))
            .concatWith(Flux.just(true))
            .onErrorReturn(Boolean.FALSE);

        StepVerifier.create(integerFlux.log())
            .expectNext(Boolean.TRUE, Boolean.TRUE)
            .expectNext(Boolean.FALSE)
            .verifyComplete();
    }

    @Test
    public void testError() {
        Flux<Boolean> integerFlux = Flux.just(true, true)
            .concatWith(Flux.error(new RuntimeException("Erro lançado")))
            .concatWith(Flux.just(true));

        StepVerifier.create(integerFlux.log())
            .expectNext(Boolean.TRUE, Boolean.TRUE)
            .expectError()
            .verify();
    }

    @Test
    public void testOnErrorContinue() {
        Flux<Boolean> integerFlux = Flux.just(true, true)
            .concatWith(Flux.error(new RuntimeException("Erro lançado")))
            .concatWith(Flux.just(true))
            .onErrorContinue((error, obj) ->{
                System.out.println("onErrorContinue");
            });

        StepVerifier.create(integerFlux.log())
            .expectNext(Boolean.TRUE, Boolean.TRUE)
            .expectError()
            .verify();
    }

}
