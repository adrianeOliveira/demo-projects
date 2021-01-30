package br.com.adriane.demo.ddddemo.domain

import br.com.adriane.demo.ddddemo.web.models.ContactRequest
import br.com.adriane.demo.ddddemo.web.models.ContactResponse

interface AgendaRepository {

    fun findContact(id: Int): ContactResponse?

    fun newContact(contact: ContactRequest)

}