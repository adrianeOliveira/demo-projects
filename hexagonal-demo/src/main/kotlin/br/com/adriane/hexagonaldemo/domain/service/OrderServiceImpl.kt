package br.com.adriane.hexagonaldemo.domain.service

import br.com.adriane.hexagonaldemo.application.NewOrderRequest
import br.com.adriane.hexagonaldemo.domain.Order
import br.com.adriane.hexagonaldemo.domain.Status
import br.com.adriane.hexagonaldemo.domain.repository.OrderRepository

class OrderServiceImpl(
    private val orderRepository: OrderRepository
) : OrderService {

    override fun openOrder(newOrderRequest: NewOrderRequest) {
        val newOrder = Order(Status.OPEN)

        newOrder.addProduct(
            productId = newOrderRequest.productId,
            quantity = newOrderRequest.quantity,
            price = newOrderRequest.unitPrice
        )

        orderRepository.saveNewOrder(newOrder)
    }
}