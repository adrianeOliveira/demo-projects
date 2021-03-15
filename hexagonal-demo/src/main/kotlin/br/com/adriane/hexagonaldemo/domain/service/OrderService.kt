package br.com.adriane.hexagonaldemo.domain.service

import br.com.adriane.hexagonaldemo.application.NewOrderRequest

interface OrderService {

    fun openOrder(newOrderRequest: NewOrderRequest)
}