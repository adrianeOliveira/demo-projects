package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.domain.Order
import br.com.adriane.demo.ddddemo.infrastructure.entities.OrderEntity
import org.springframework.data.repository.findByIdOrNull

class OrderRepository(
    val jpaOrderRepository: JpaOrderRepository
) {
    fun createOrder(order: Order) {
        TODO("mapear o obj order para entity")
        TODO("e salvar na base")
    }

    fun findById(id: Int): Order {
        val orderEntity = jpaOrderRepository.findByIdOrNull(id)
        TODO("mapear o retorno para o obj de dominio")
    }
}