# kotlin-kafka

## Disparo de mensagens

### Produtor Kafka
Por padrão a aplicação publica mensagens no tópico _topic-kotlin-app_ via api restfull. Para mensagens com uso do Apache Avro, o tópico utilizado é *topic-kotlin-avro*. 

### Consumidor Kafka
O Consumidor padrão da aplicação se conecta no broker con o groupid *kotlin-group*, enquanto que o group padrão para o tópico *topic-kotlin-avro* é *kotlin-avro-group*.

### API RESTful
Para disparo das mensagens:
```sh
curl -X GET localhost:8080/kafka
```

Para disparo das mensagens com uso do Apache Avro:
```sh
curl -X GET localhost:8080/kafka/contato
```

Foram mantidas as AutoConfigurações do springboot para conectar com o broker do kafka, este definido no arquivo properties.
