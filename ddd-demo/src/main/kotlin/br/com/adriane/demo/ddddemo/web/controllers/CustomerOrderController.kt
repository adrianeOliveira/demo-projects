package br.com.adriane.demo.ddddemo.web.controllers

import br.com.adriane.demo.ddddemo.infrastructure.service.CustomerOrderQueryService
import br.com.adriane.demo.ddddemo.web.exceptions.CustomerOrderNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customerOrders")
class CustomerOrderController(
    private val customerOrderQueryService: CustomerOrderQueryService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{orderId}")
    fun findOrderById(@PathVariable orderId: Int) =
        customerOrderQueryService.findCustomerOrderById(orderId)
            ?: throw CustomerOrderNotFoundException()

}