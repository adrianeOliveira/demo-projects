package br.com.adriane.demo.ddddemo.infrastructure.configuration

import br.com.adriane.demo.ddddemo.infrastructure.queries.CustomerOrderQuery
import br.com.adriane.demo.ddddemo.infrastructure.repository.JpaCustomerOrderRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerOrderQueryConfiguration(
    private val repository: JpaCustomerOrderRepository
) {

    @Bean
    fun customerOrderQuery() =
        CustomerOrderQuery(repository)
}