package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.domain.CustomerOrder
import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import br.com.adriane.demo.ddddemo.infrastructure.mappers.CustomerOrderMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CustomerOrderRepositoryImpl(
    private val repository: JpaCustomerOrderRepository
) : CustomerOrderRepository {

    @Transactional
    override fun createCustomerOrder(customerOrder: CustomerOrder) : Int {
        val orderEntity = CustomerOrderMapper
            .mapDomainOrderToEntity(0, customerOrder)

        return repository.save(orderEntity).orderId
    }

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
    override fun updateCustomerOrder(orderId: Int, customerOrder: CustomerOrder) {
        customerOrder.calculateTotalPrice()

        val orderEntity = CustomerOrderMapper
            .mapDomainOrderToEntity(orderId, customerOrder)

        orderEntity.orderItems = CustomerOrderMapper
            .mapDomainItemsToEntity(orderEntity, customerOrder.orderItems())

        repository.save(orderEntity)
    }

}