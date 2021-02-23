package br.com.adriane.demo.ddddemo.infrastructure.service

import br.com.adriane.demo.ddddemo.web.models.CustomerOrderResponse

interface CustomerOrderQueryService {
    fun findCustomerOrderById(orderId: Int): CustomerOrderResponse?
}