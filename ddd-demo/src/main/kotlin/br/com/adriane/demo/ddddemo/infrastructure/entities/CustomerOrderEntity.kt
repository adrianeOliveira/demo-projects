package br.com.adriane.demo.ddddemo.infrastructure.entities

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "customer_order")
class CustomerOrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderId: Int,
    var address: String,
    var totalPrice: Double,
    var paymentMethod: String,
    @OneToMany(mappedBy = "customerOrderId")
    var orderItems: MutableList<OrderItemEntity>
)

@Entity
@Table(name = "order_item")
class OrderItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderItemId: Int,
    var productId: Int,
    var quantity: Int,
    var unitPrice: Double,
    var customerOrderId: Int
)