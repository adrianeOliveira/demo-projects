package br.com.adriane.demo.ddddemo.infrastructure.repository

import br.com.adriane.demo.ddddemo.infrastructure.entities.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "products")
interface JpaProductRepository : JpaRepository<ProductEntity, Int>