package br.com.adriane.demo.jaxrs.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Contact(
        @Id
        @GeneratedValue
        val id: Int,
        val name: String,
        val email: String
)