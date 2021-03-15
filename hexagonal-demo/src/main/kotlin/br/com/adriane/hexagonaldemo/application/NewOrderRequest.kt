package br.com.adriane.hexagonaldemo.application

data class NewOrderRequest(
    val productId: Int,
    val unitPrice: Double,
    val quantity: Int
)
