package br.com.adriane.demo.ddddemo.web.controllers

import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import br.com.adriane.demo.ddddemo.web.models.CustomerOrderResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customerOrders")
class CustomerOrderController(
    private val customerOrderRepository: CustomerOrderRepository
) {

    @GetMapping("/{orderId}")
    fun findOrderById(@PathVariable orderId: Int) : CustomerOrderResponse? {
        TODO()
    }
}