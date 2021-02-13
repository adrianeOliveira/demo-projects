package br.com.adriane.demo.ddddemo.web.controllers

import br.com.adriane.demo.ddddemo.infrastructure.mappers.CustomerOrderMapper
import br.com.adriane.demo.ddddemo.infrastructure.queries.CustomerOrderQuery
import br.com.adriane.demo.ddddemo.web.exceptions.CustomerOrderNotFoundException
import br.com.adriane.demo.ddddemo.web.models.CustomerOrderResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customerOrders")
class CustomerOrderController(
    private val customerOrderQuery: CustomerOrderQuery
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{orderId}")
    fun findOrderById(@PathVariable orderId: Int) : CustomerOrderResponse {
        val orderEntity = customerOrderQuery.findCustomerOrderById(orderId)
            ?: throw CustomerOrderNotFoundException()

        return CustomerOrderMapper.customerOrderEntityToResponse(orderEntity)
    }

}