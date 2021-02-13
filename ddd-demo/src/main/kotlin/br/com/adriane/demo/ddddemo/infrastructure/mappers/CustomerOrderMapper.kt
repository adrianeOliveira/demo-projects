package br.com.adriane.demo.ddddemo.infrastructure.mappers

import br.com.adriane.demo.ddddemo.domain.CustomerOrder
import br.com.adriane.demo.ddddemo.domain.OrderItem
import br.com.adriane.demo.ddddemo.infrastructure.entities.CustomerOrderEntity
import br.com.adriane.demo.ddddemo.infrastructure.entities.OrderItemEntity
import br.com.adriane.demo.ddddemo.web.models.CustomerOrderItemResponse
import br.com.adriane.demo.ddddemo.web.models.CustomerOrderResponse

class CustomerOrderMapper {
    companion object {
        fun customerOrderEntityToResponse(customerOrder: CustomerOrderEntity) =
            CustomerOrderResponse(
                customerOrder.orderId,
                customerOrder.totalPrice,
                customerOrder.address,
                customerOrder.paymentMethod,
                mapOrderItemEntityToResponse(customerOrder)
            )

    private fun mapOrderItemEntityToResponse(customerOrder: CustomerOrderEntity) =
        customerOrder.orderItems.map {
            CustomerOrderItemResponse(
                it.orderItemId,
                it.productId,
                it.quantity,
                it.unitPrice,
                it.customerOrder.orderId
            )
        }.toMutableList()

        fun mapDomainOrderToEntity(orderId: Int, customerOrder: CustomerOrder) =
            CustomerOrderEntity(
                orderId,
                customerOrder.address(),
                customerOrder.totalPrice,
                customerOrder.paymentMethod(),
                mutableListOf()
            )

        fun mapDomainItemsToEntity(orderEntity: CustomerOrderEntity, domainItems: MutableList<OrderItem>) :
                MutableList<OrderItemEntity> {
            val resultList = mutableListOf<OrderItemEntity>()

            domainItems.forEach {
                resultList.add(
                    OrderItemEntity(
                    0,
                    it.productId,
                    it.quantity,
                    it.unitPrice,
                    orderEntity
                )
                )
            }

            return resultList
        }
    }
}