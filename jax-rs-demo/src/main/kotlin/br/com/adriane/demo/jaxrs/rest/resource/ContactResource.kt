package br.com.adriane.demo.jaxrs.rest.resource

import br.com.adriane.demo.jaxrs.rest.dto.ContactDto
import br.com.adriane.demo.jaxrs.service.ContactService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Component
@Path("/contact")
class ContactResource(
        val contactService: ContactService
) {
    private val log = LoggerFactory.getLogger(ContactResource::class.java)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun listContacts(): List<ContactDto> {
        log.info("M=listContacts, I=Lista de contatos")
        return contactService.listContacts()
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun insertContact(contact: ContactDto): ContactDto {
        log.info("M=insertContact, I=contato $contact")
        return contactService.insertContact(contact)
    }

}