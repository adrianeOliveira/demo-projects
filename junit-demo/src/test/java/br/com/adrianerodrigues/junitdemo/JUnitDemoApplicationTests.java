package br.com.adrianerodrigues.junitdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JUnitDemoApplicationTests {

	@Test
	@Order(1)
	@DisplayName("Primeiro cenário de teste")
	void firstTest() {
		log.info("M=primeiroCenarioDeTeste, I=Primeiro teste");
		assertTrue(Stream.of(1,2,3,4,5)
						.mapToInt(i -> i)
						.sum() > 5,
				() -> "O total da soma deve ser maior que 5");
	}

	@Test
	@Disabled("Segundo cenário de teste não deve ser executado")
	void secondTest() {
		log.info("M=terceiroCenarioDeTeste, I=Terceiro teste");
		assertTrue(Boolean.TRUE);
	}

	@Test
	void assumptionEnvVariableTest() {
		log.info("M=assumptionTest, env={}", System.getenv("ENV"));
		Assumptions.assumeTrue("test".equals(System.getenv("ENV")),
				() -> "A variável ENV deve ser definida");
	}

	@Test
	@Order(2)
	void assertThrowExceptionMessageTest() {
		Throwable ex = assertThrows(UnsupportedOperationException.class, ()->{
			throw new UnsupportedOperationException("Errow");
		});
		assertEquals(ex.getMessage(), "Errow");
	}

	@Test
	@Order(3)
	void assertExceptionTypeTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Integer.valueOf(null);
		});
	}

}
