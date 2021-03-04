package br.com.adriane.hexagonaldemo.domain

class Order(
    val status: Status
) {
    var items = mutableListOf<Item>()
    private var totalPrice = 0.0

    fun addItem(productId: Int, price: Double) {
        items.add(Item(productId, price))
        totalPrice += price
    }

    fun removeProduct(productId: Int) {
        items.forEach {
            if (it.productId == productId) {
                totalPrice -= it.unitPrice
            }
        }

        items.removeIf { it.productId == productId }
    }
}

class Item(
    val productId: Int,
    val unitPrice: Double
)

enum class Status {
    OPEN, FINISHED, PREPARING
}