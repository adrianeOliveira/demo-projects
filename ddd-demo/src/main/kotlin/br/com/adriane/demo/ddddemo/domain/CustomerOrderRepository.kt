package br.com.adriane.demo.ddddemo.domain

interface CustomerOrderRepository {

    fun findCustomerOrder(id: Int): CustomerOrder?

    fun saveCustomerOrder(orderId: Int, customerOrder: CustomerOrder)
}