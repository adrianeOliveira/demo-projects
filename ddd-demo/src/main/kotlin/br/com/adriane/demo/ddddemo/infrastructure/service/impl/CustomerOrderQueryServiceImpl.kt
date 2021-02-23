package br.com.adriane.demo.ddddemo.infrastructure.service.impl

import br.com.adriane.demo.ddddemo.infrastructure.mappers.CustomerOrderMapper
import br.com.adriane.demo.ddddemo.infrastructure.repository.JpaCustomerOrderRepository
import br.com.adriane.demo.ddddemo.infrastructure.service.CustomerOrderQueryService
import br.com.adriane.demo.ddddemo.web.models.CustomerOrderResponse
import org.springframework.data.repository.findByIdOrNull


class CustomerOrderQueryServiceImpl(
    private val repository: JpaCustomerOrderRepository
) :
    CustomerOrderQueryService {

    override fun findCustomerOrderById(orderId: Int): CustomerOrderResponse? {
        val orderEntity = repository.findByIdOrNull(orderId) ?: return null

        return CustomerOrderMapper.customerOrderEntityToResponse(orderEntity)
    }
}