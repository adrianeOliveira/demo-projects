package br.com.adriane.demo.ddddemo.domain

class CustomerOrder(
    private val paymentMethod: String,
    private val address: String
) {
    private val orderItems = mutableListOf<OrderItem>()

    var totalPrice: Double = 0.0
        private set

    fun paymentMethod() = this.paymentMethod
    fun address() = this.address
    fun orderItems() = this.orderItems

    fun addNewProduct(productId: Int, quantity: Int, unitPrice: Double) {
        orderItems.add(OrderItem(
            productId,
            quantity,
            unitPrice
        ))
    }

    fun calculateTotalPrice() {
        totalPrice = orderItems.stream()
            .map(OrderItem::unitPrice)
            .reduce(0.0, Double::plus)
    }
}

data class OrderItem (
    val productId: Int,
    val quantity: Int,
    val unitPrice: Double
)