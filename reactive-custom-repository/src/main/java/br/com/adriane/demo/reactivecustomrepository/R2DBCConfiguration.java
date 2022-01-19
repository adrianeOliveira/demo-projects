package br.com.adriane.demo.reactivecustomrepository;

import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.boot.r2dbc.ConnectionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import static io.r2dbc.spi.ConnectionFactoryOptions.DATABASE;
import static io.r2dbc.spi.ConnectionFactoryOptions.DRIVER;
import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.PROTOCOL;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

@Configuration
@EnableR2dbcRepositories
public class R2DBCConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions.Builder optionsBuilder = ConnectionFactoryOptions.builder()
                .option(DRIVER, "h2")
                .option(PROTOCOL, "mem")
                .option(USER, "sa")
                .option(PASSWORD, "")
                .option(DATABASE, "r2dbc:h2:mem:///testdb");

        return ConnectionFactoryBuilder.withOptions(optionsBuilder).build();
    }
}
