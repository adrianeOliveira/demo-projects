package br.com.adriane.hexagonaldemo.infrastructure

import br.com.adriane.hexagonaldemo.infrastructure.entities.OrderEntity

interface JpaOrderRepository {
    fun save(orderEntity: OrderEntity)
}