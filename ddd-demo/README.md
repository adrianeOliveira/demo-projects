# ddd-demo

Projeto para estudo da aplicação do DDD com CQRS e arquitetura hexagonal. As tecnologias principais são: Spring Boot, Spring Data e Data REST com base de dados H2.

A Aplicação consiste em um genérico de Ordem com itens, cada item com um produto.  
A criação de produtos é através de um crud simples via api restful com uso do Spring Data REST, enquanto que para criar uma ordem e adicionar itens nela se faz o uso de comandos.

CRUD de Produtos:
```
curl -X GET http://localhost:8080/roducts
curl -X POST -H "Content-Type:application/json" -d '{"name":"Produto 1", "active":"true"}' http://localhost:8080/products
curl -X GET http://localhost:8080/roducts/1
curl -X PUT -H "Content-Type:application/json" -d '{"name":"Produto 1", "active":"false"}' http://localhost:8080/products/1
curl -X DELETE http://localhost:8080/products/1
```
