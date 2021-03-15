package br.com.adriane.hexagonaldemo.domain

class Order(
    val status: Status
) {
    var items = mutableListOf<Item>()
    var totalPrice = 0.0
    private set

    fun addItem(productId: Int, price: Double, quantity: Int) {
        items.add(Item(productId, price, quantity))
        totalPrice += (price * quantity)
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

data class Item(
    val productId: Int,
    val unitPrice: Double,
    val quantity: Int
)

enum class Status {
    OPEN, FINISHED, PREPARING
}