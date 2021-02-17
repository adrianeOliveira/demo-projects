package br.com.adriane.demo.ddddemo.domain

interface CustomerOrderRepository {

    fun createCustomerOrder(customerOrder: CustomerOrder) : Int

    fun findCustomerOrder(id: Int): CustomerOrder?

    fun updateCustomerOrder(orderId: Int, customerOrder: CustomerOrder)
}