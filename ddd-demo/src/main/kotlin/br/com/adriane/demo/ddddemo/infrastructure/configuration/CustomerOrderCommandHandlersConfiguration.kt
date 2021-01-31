package br.com.adriane.demo.ddddemo.infrastructure.configuration

import br.com.adriane.demo.ddddemo.application.CustomerOrderCommandHandlers
import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerOrderCommandHandlersConfiguration {

    @Bean
    fun customerOrderCommandHandlers(customerOrderRepository: CustomerOrderRepository) =
        CustomerOrderCommandHandlers(customerOrderRepository)
}