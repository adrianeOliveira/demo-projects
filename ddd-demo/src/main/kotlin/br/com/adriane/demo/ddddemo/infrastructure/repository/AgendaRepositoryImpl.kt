package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.domain.AgendaRepository
import br.com.adriane.demo.ddddemo.web.models.ContactRequest
import br.com.adriane.demo.ddddemo.web.models.ContactResponse
import org.springframework.data.repository.findByIdOrNull

class AgendaRepositoryImpl(
    private val repository: JpaAgendaRepository
) : AgendaRepository {

    override fun findContact(id: Int): ContactResponse? {
        val contact = repository.findByIdOrNull(id) ?: return null

        return ContactResponse(
            contact.contactId,
            contact.fullName,
            contact.phone,
            contact.email
        )
    }

    override fun newContact(contact: ContactRequest) {
        TODO("Not yet implemented")
    }
}