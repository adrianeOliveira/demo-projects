package br.com.adriane.demo.ddddemo.infrastructure.entities

data class OrderEntity(val id: Int, val totalPrice: Double, val items: List<ItemEntity>)

data class ItemEntity(val id: Int, val name: String, val price: Double)