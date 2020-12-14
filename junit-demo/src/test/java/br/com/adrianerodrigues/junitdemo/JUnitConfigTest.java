package br.com.adrianerodrigues.junitdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringJUnitConfig(JUnitConfigTest.Config.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JUnitConfigTest.Config.class)
public class JUnitConfigTest {
    @Configuration
    static class Config {}

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void assertApplicationContextNotNullWithSuccess() {
        assertNotNull(applicationContext);
    }
}
