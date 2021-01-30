package br.com.adriane.demo.ddddemo.infrastructure.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "contact")
data class ContactEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val contactId: Int,
    val fullName: String,
    val phone: String,
    val email: String
)