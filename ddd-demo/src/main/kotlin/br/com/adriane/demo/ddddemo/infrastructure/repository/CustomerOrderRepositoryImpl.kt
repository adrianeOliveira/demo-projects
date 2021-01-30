package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.domain.CustomerOrder
import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import org.springframework.data.repository.findByIdOrNull

class CustomerOrderRepositoryImpl(
    private val repository: JpaCustomerOrderRepository
) : CustomerOrderRepository {
    override fun findCustomerOrder(id: Int): CustomerOrder? {
        val orderEntity = repository.findByIdOrNull(id) ?: return null

        val customerOrder = CustomerOrder(orderEntity.paymentMethod, orderEntity.address)
        orderEntity.orderItems.forEach {
            customerOrder.addNewProduct(it.productId, it.quantity, it.unitPrice)
        }

        return customerOrder
    }

    override fun saveCustomerOrder(customerOrder: CustomerOrder) {
        TODO("Not yet implemented")
    }
}