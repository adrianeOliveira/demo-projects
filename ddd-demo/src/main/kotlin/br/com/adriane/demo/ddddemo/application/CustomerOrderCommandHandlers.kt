package br.com.adriane.demo.ddddemo.application

import br.com.adriane.demo.ddddemo.application.command.OrderAddProductCommand
import br.com.adriane.demo.ddddemo.domain.CustomerOrder
import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import org.slf4j.LoggerFactory

class CustomerOrderCommandHandlers(
    private val customerOrderRepository: CustomerOrderRepository
) {

    private val log = LoggerFactory.getLogger(CustomerOrderCommandHandlers::class.java)

    fun addNewProduct(command: OrderAddProductCommand) {
        log.info("method=addNewProduct, orderId=${command.orderId}, productId=${command.productId}")

        val customerOrder = customerOrderRepository.findCustomerOrder(command.orderId) ?:
            throw IllegalArgumentException("Pedido não existe, orderId=${command.orderId}")

        customerOrder.addNewProduct(command.productId, command.quantity, command.unitPrice)

        customerOrderRepository.updateCustomerOrder(command.orderId, customerOrder)

        log.info("method=addNewProduct, info=produto ${command.productId} adicionado no pedido ${command.orderId}")
    }

    fun createNewCustomerOrder() : Int {
        log.info("m=createNewCustomerOrder, info=criação de nova ordem")

        val customerOrderId = customerOrderRepository.createCustomerOrder(
            CustomerOrder("", "")
        )

        log.info("m=createNewCustomerOrder, info=orderId=$customerOrderId")
        return customerOrderId
    }
}