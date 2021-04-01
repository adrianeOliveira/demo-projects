package br.com.adriane.hexagonaldemo.infrastructure

import br.com.adriane.hexagonaldemo.domain.Item
import br.com.adriane.hexagonaldemo.domain.Order
import br.com.adriane.hexagonaldemo.infrastructure.entities.ItemEntity
import br.com.adriane.hexagonaldemo.infrastructure.entities.OrderEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "string",
    uses = [ItemMapper::class],
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface OrderMapper {
    fun fromDomainToEntity(order: Order): OrderEntity
}

@Mapper(
    componentModel = "string",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
interface ItemMapper {
    fun fromDomainToEntity(item: Item): ItemEntity
}
