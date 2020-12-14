package br.com.adrianerodrigues.aopdemo;

import br.com.adrianerodrigues.aopdemo.aspect.SimpleAspect;
import br.com.adrianerodrigues.aopdemo.service.SimplePojo;
import br.com.adrianerodrigues.aopdemo.service.impl.BarServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class AopDemoApplication {

	private static final Logger log = LoggerFactory.getLogger(AopDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);

		final var pojo = new SimplePojo();
		String className = pojo.getClass().getName();
		log.info("M=main; I=criando instância com 'new', className={}", className);
		pojo.foo();

		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(pojo);
		proxyFactory.addAspect(new SimpleAspect());

		final var pojoProxied = (SimplePojo) proxyFactory.getProxy();

		className = pojoProxied.getClass().getName();
		log.info("M=main; I=criando instância com 'AspectProxyFactory', className={}", className);
		pojoProxied.foo();

		AnnotationConfigApplicationContext applicationContext =
				new AnnotationConfigApplicationContext("br.com.adrianerodrigues.aopdemo");

		final var pojoBean = applicationContext.getBean(SimplePojo.class);

		className = pojoBean.getClass().getName();
		log.info("M=main; I=criando instância com container do Spring, className={}", className);
		pojoBean.foo();

		final var barBean = applicationContext.getBean(BarServiceImpl.class);
		className = barBean.getClass().getName();
		log.info("M=main; I=criando instância com container do Spring, className={}", className);
		barBean.bar();
	}

}
