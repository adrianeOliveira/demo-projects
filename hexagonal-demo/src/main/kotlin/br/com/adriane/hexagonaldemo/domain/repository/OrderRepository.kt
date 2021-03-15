package br.com.adriane.hexagonaldemo.domain.repository

import br.com.adriane.hexagonaldemo.domain.Order

interface OrderRepository {

    fun saveNewOrder(order: Order)
}