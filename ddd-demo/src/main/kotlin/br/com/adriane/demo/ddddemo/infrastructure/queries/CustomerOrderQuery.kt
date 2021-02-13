package br.com.adriane.demo.ddddemo.infrastructure.queries

import br.com.adriane.demo.ddddemo.infrastructure.repository.JpaCustomerOrderRepository
import org.springframework.data.repository.findByIdOrNull

class CustomerOrderQuery(
    private val repository: JpaCustomerOrderRepository
) {
    fun findCustomerOrderById(orderId: Int) =
        repository.findByIdOrNull(orderId)
}