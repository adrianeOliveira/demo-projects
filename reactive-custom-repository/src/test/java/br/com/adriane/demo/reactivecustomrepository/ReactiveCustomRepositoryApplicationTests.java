package br.com.adriane.demo.reactivecustomrepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@DataR2dbcTest
class ReactiveCustomRepositoryApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void findUserByIdTest() {
		User user = new User();
		user.setId(1);
		user.setName("Adriane");
		user.setEmail("rodrigues.adriane@outlook.com");
		StepVerifier.create(userRepository.findById(1).log())
				.expectNext(user)
				.verifyComplete();
	}

}
