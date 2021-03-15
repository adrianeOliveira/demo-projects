package br.com.adriane.hexagonaldemo.infrastructure

import br.com.adriane.hexagonaldemo.domain.Order
import br.com.adriane.hexagonaldemo.domain.repository.OrderRepository
import br.com.adriane.hexagonaldemo.infrastructure.entities.ItemEntity
import br.com.adriane.hexagonaldemo.infrastructure.entities.OrderEntity

class OrderRepositoryImpl(
    private val orderRepository: JpaOrderRepository
) : OrderRepository{

    override fun saveNewOrder(order: Order) {
        val orderEntity = OrderEntity(
            totalPrice = order.totalPrice,
            status = order.status.name,
            items = order.items.map { ItemEntity(
                productId = it.productId,
                unitPrice = it.unitPrice,
                quantity = it.quantity
            )  }.toMutableList()
        )

        orderRepository.save(orderEntity)
    }
}