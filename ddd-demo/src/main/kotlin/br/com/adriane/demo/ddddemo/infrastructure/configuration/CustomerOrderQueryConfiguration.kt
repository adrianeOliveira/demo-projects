package br.com.adriane.demo.ddddemo.infrastructure.configuration

import br.com.adriane.demo.ddddemo.infrastructure.service.CustomerOrderQueryService
import br.com.adriane.demo.ddddemo.infrastructure.repository.JpaCustomerOrderRepository
import br.com.adriane.demo.ddddemo.infrastructure.service.impl.CustomerOrderQueryServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CustomerOrderQueryConfiguration(
    private val repository: JpaCustomerOrderRepository
) {

    @Bean
    fun customerOrderQuery() : CustomerOrderQueryService {
        return CustomerOrderQueryServiceImpl(repository)
    }

}