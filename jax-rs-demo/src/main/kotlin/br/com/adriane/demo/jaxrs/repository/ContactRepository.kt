package br.com.adriane.demo.jaxrs.repository

import br.com.adriane.demo.jaxrs.entity.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactRepository: JpaRepository<Contact, Int>