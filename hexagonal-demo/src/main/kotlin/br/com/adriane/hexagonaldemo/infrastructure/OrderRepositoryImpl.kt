package br.com.adriane.hexagonaldemo.infrastructure

import br.com.adriane.hexagonaldemo.domain.Order
import br.com.adriane.hexagonaldemo.domain.OrderRepository

class OrderRepositoryImpl(
    private val mapper: OrderMapper,
    private val orderRepository: JpaOrderRepository
) : OrderRepository {

    override fun saveNewOrder(order: Order) {
        val orderEntity = mapper.fromDomainToEntity(order)

        orderRepository.save(orderEntity)
    }
}