package br.com.adriane.demo.ddddemo.web.models

data class CustomerOrderResponse(
    val orderId: Int,
    val totalPrice: Double,
    val address: String,
    val paymentMethod: String,
    val items: MutableList<CustomerOrderItemResponse>
)

data class CustomerOrderItemResponse(
    var orderItemId: Int,
    var productId: Int,
    var quantity: Int,
    var unitPrice: Double,
    var customerOrderId: Int
)