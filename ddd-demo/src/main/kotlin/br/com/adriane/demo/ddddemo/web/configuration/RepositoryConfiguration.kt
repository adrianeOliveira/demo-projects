package br.com.adriane.demo.ddddemo.web.configuration

import br.com.adriane.demo.ddddemo.infrastructure.repository.CustomerOrderRepositoryImpl
import br.com.adriane.demo.ddddemo.infrastructure.repository.JpaCustomerOrderRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun agendaRepository(jpaCustomerOrderRepository: JpaCustomerOrderRepository) =
        CustomerOrderRepositoryImpl(jpaCustomerOrderRepository)
}