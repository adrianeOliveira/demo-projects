package br.com.adriane.demo.reactiveprog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactiveProgApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveProgApplication.class, args);
		HelloWorldWebClient webClient = new HelloWorldWebClient();
		System.out.println(webClient.getResult());
	}

}
