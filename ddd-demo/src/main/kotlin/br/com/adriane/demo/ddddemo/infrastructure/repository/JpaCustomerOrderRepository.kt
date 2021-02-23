package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.infrastructure.entities.CustomerOrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaCustomerOrderRepository : JpaRepository<CustomerOrderEntity, Int>