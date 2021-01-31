package br.com.adriane.demo.ddddemo.application

import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import org.slf4j.LoggerFactory

class CustomerOrderCommandHandlers(
    private val customerOrderRepository: CustomerOrderRepository
) {

    private val log = LoggerFactory.getLogger(CustomerOrderCommandHandlers::class.java)

    fun addNewProduct(orderId: Int, productId: Int, quantity: Int, unitPrice: Double) {
        log.info("method=addNewProduct, orderId=$orderId, productId=$productId")

        val customerOrder = customerOrderRepository.findCustomerOrder(orderId) ?:
            throw IllegalArgumentException("Pedido não existe, orderId=$orderId")

        customerOrder.addNewProduct(productId, quantity, unitPrice)
        customerOrderRepository.saveCustomerOrder(orderId, customerOrder)
        log.info("method=addNewProduct, info=produto $productId adicionado no pedido $orderId")
    }
}