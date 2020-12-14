package br.com.adrianerodrigues.aopdemo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SimpleAspect {

    private Logger logger = LoggerFactory.getLogger(SimpleAspect.class);

    @Before("execution(public void br.com.adrianerodrigues.aopdemo.service.SimplePojo.foo())")
    public void fooAdvice() {
        logger.info("M=fooAdvice, I=Hello foo");
    }

    @After("execution(public void br.com.adrianerodrigues.aopdemo.service.BarService.bar())")
    public void barAdvise() {
        logger.info("M=barAdvise, I=Bye bar");
    }
}
