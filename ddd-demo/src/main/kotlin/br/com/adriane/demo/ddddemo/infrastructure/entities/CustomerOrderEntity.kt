package br.com.adriane.demo.ddddemo.infrastructure.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Table(name = "customer_order")
data class CustomerOrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Int,
    val address: String,
    val totalPrice: Double,
    val paymentMethod: String,
    @OneToMany(mappedBy = "customerOrderId")
    val orderItems: MutableList<OrderItemEntity>
)

@Entity
@Table(name = "order_item")
data class OrderItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderItemId: Int,
    val productId: Int,
    val quantity: Int,
    val unitPrice: Double,
    @ManyToOne(targetEntity = CustomerOrderEntity::class)
    @JoinColumn(name = "customer_order_id")
    val customerOrderId: Int
)