package br.com.adriane.demo.ddddemo.application.command

class OrderAddProductCommand(
    val orderId: Int,
    val productId: Int,
    val quantity: Int,
    val unitPrice: Double
)