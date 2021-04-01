package br.com.adriane.hexagonaldemo.domain

interface OrderRepository {
    fun saveNewOrder(order: Order)
}