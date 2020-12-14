package br.com.adrianerodrigues.aopdemo.service.impl;

import br.com.adrianerodrigues.aopdemo.service.BarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BarServiceImpl implements BarService {
    private final Logger log = LoggerFactory.getLogger(BarServiceImpl.class);

    @Override
    public void bar() {
        log.info("M=bar, I=Hello bar");
    }
}
