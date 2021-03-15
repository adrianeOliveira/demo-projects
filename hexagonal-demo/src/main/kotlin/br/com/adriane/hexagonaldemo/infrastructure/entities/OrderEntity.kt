package br.com.adriane.hexagonaldemo.infrastructure.entities

data class OrderEntity(
    var id: Int? = null,
    var status: String,
    var totalPrice: Double,
    var items: MutableList<ItemEntity>
)

data class ItemEntity(
    var id: Int? = null,
    var quantity: Int,
    var productId: Int,
    var unitPrice: Double
)
