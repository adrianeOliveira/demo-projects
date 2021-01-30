package br.com.adriane.demo.ddddemo.web.controllers

import br.com.adriane.demo.ddddemo.domain.AgendaRepository
import br.com.adriane.demo.ddddemo.web.models.ContactResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/contacts")
class ContactController(
    private val agendaRepository: AgendaRepository
) {

    @GetMapping("/{contactId}")
    fun findContactFromId(@PathVariable contactId: Int) : ContactResponse? {
        return agendaRepository.findContact(contactId)
    }
}