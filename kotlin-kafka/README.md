# kotlin-kafka

## Disparo de mensagens

### Produtor Kafka
Por padrão a aplicação publica mensagens no tópico _topic-kotlin-app_ via api restfull

### Consumidor Kafka
O Consumidor padrão da aplicação se conecta no broker con o groupid *kotlin-group*

### API RESTful
Para disparo das mensagens:
```sh
curl -X GET localhost:8080/kafka
```

Foram mantidas as AutoConfigurações do springboot para conectar com o broker do kafka, este definido no arquivo properties.
