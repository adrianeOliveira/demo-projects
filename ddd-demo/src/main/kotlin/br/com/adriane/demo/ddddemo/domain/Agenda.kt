package br.com.adriane.demo.ddddemo.domain

import org.slf4j.LoggerFactory

class Agenda() {
    private val log = LoggerFactory.getLogger(Agenda::class.java)

    private val contacts: MutableList<Contact> = mutableListOf()

    fun addNewContact(newFullName: String, newPhone: String, newEmail: String) {
        val contact = Contact(newFullName, newPhone, newEmail)
        contacts.add(contact)
        log.info("method=addNewContact, status=success, contact ${this.contacts.last()}")
    }
}

data class Contact (
    val fullName: String,
    val phone: String,
    val email: String
)