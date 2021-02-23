package br.com.adriane.demo.ddddemo

import br.com.adriane.demo.ddddemo.application.CustomerOrderCommandHandlers
import br.com.adriane.demo.ddddemo.application.command.OrderAddProductCommand
import br.com.adriane.demo.ddddemo.domain.CustomerOrderRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DddDemoApplicationTests {

	@Autowired
	private lateinit var customerOrderHandler: CustomerOrderCommandHandlers

	@Autowired
	private lateinit var customerRepository: CustomerOrderRepository

	@Test
	fun `add new product to customer order with success`() {
		customerOrderHandler.addNewProduct(OrderAddProductCommand(
			1,
			362,
			1,
			34.80
		))

		val customerOrder = customerRepository.findCustomerOrder(1)
		val orderItem = customerOrder?.orderItems()?.find { it.productId == 362 }

		assertNotNull(orderItem)
		assertEquals(orderItem?.productId, 362)
		assertEquals(orderItem?.quantity, 1)
		assertEquals(orderItem?.unitPrice, 34.8)
	}

}
