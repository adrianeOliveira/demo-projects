# springboot-demo

## Spring Boot Auto Configuration
 O Spring boot é uma ferramente responsável por automatizar a criação de uma estrutura de um projeto em java e facilitar o desenvolvedor ne desenvolvimento do projeto. Através da auto configuração fornecida por essa ferramenta, é possível focar na lógica de negócio enquanto o spring boot fornece configurações prontas para uso, como beans para conectar com banco de dados, libs para integração com outro sistemas, mecanismos de monitoramento etc. 

### Custom Auto Configuration
 Para customizar uma configuration no spring boot é necessário uma classe com a anotação `@Configuration` e é preciso registrar essa classe no arquivo `META-INF/spring.factories` para que o Spring Boot indentifca-la: 
 
```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.mycorp.libx.autoconfigure.LibXAutoConfiguration,\
com.mycorp.libx.autoconfigure.LibXWebAutoConfiguration
```

É possível adicionar condições na qual os beans devem ser criados com a anotação `@Conditional`.
