package br.com.adriane.demo.ddddemo.web.models

data class ContactResponse(
    val contactId: Int,
    val fullName: String,
    val phone: String,
    val email: String
)