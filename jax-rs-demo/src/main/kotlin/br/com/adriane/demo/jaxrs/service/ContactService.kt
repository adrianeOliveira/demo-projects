package br.com.adriane.demo.jaxrs.service

import br.com.adriane.demo.jaxrs.entity.Contact
import br.com.adriane.demo.jaxrs.repository.ContactRepository
import br.com.adriane.demo.jaxrs.rest.dto.ContactDto
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ContactService(
        private val contactRepository: ContactRepository
) {
    private val log = LoggerFactory.getLogger(ContactService::class.java)

    fun insertContact(dto: ContactDto): ContactDto {
        return mapEntityToDto(
                contactRepository.save(
                        mapDtoToEntity(dto)
                )
        )
    }

    fun listContacts(): List<ContactDto> =
            contactRepository.findAll().map(this::mapEntityToDto)

    private fun mapDtoToEntity(dto: ContactDto): Contact =
            Contact(dto.id, dto.name, dto.email)

    private fun mapEntityToDto(contact: Contact): ContactDto =
            ContactDto(contact.id, contact.name, contact.email)
}