package br.com.adriane.demo.ddddemo.domain

class Order() {
    var orderId: Int? = null

    val items = mutableListOf<Item>()

    var totalPrice: Double = 0.00
        private set

    fun addItem(item: Item) {
        items.add(item)
        totalPrice += totalPrice
    }

    fun removeItem(item: Item) {
        items.remove(item)
        totalPrice -= item.price
    }

}

data class Item(
    val itemId: Int,
    val name: String,
    val price: Double
)