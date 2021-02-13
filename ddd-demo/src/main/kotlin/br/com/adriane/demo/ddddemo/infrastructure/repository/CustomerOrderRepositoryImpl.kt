package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.domain.CustomerOrder
import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import br.com.adriane.demo.ddddemo.domain.OrderItem
import br.com.adriane.demo.ddddemo.infrastructure.entities.CustomerOrderEntity
import br.com.adriane.demo.ddddemo.infrastructure.entities.OrderItemEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CustomerOrderRepositoryImpl(
    private val repository: JpaCustomerOrderRepository
) : CustomerOrderRepository {

    @Transactional(readOnly = true)
    override fun findCustomerOrder(id: Int): CustomerOrder? {
        val orderEntity = repository.findByIdOrNull(id) ?: return null

        val customerOrder = CustomerOrder(orderEntity.paymentMethod, orderEntity.address)
        orderEntity.orderItems.forEach {
            customerOrder.addNewProduct(it.productId, it.quantity, it.unitPrice)
        }
        customerOrder.calculateTotalPrice()
        return customerOrder
    }

    @Transactional
    override fun saveCustomerOrder(orderId: Int, customerOrder: CustomerOrder) {
        customerOrder.calculateTotalPrice()
        val orderEntity = CustomerOrderEntity(
            orderId,
            customerOrder.address(),
            customerOrder.totalPrice,
            customerOrder.paymentMethod(),
            mutableListOf()
        )
        orderEntity.orderItems = mapDomainItemsToEntity(orderEntity, customerOrder.orderItems())
        repository.save(orderEntity)
    }

    private fun mapDomainItemsToEntity(orderEntity: CustomerOrderEntity, domainItems: MutableList<OrderItem>) :
            MutableList<OrderItemEntity> {
        val resultList = mutableListOf<OrderItemEntity>()

        domainItems.forEach {
            resultList.add(OrderItemEntity(
                0,
                it.productId,
                it.quantity,
                it.unitPrice,
                orderEntity
            ))
        }

        return resultList
    }
}