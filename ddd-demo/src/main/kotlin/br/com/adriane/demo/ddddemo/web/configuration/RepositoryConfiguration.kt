package br.com.adriane.demo.ddddemo.web.configuration

import br.com.adriane.demo.ddddemo.infrastructure.repository.AgendaRepositoryImpl
import br.com.adriane.demo.ddddemo.infrastructure.repository.JpaAgendaRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepositoryConfiguration {
    @Bean
    fun agendaRepository(jpaAgendaRepository: JpaAgendaRepository) =
        AgendaRepositoryImpl(jpaAgendaRepository)
}