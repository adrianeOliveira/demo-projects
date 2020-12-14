package br.com.adrianerodrigues.aopdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SimplePojo {

    private Logger logger = LoggerFactory.getLogger(SimplePojo.class);

    public void foo() {
        logger.info("M=foo, I=Hello");
    }
}
