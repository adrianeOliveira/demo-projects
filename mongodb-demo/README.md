# mongodb-demo

Demo com banco de dados MongoDB usando kafka consumir eventos to tipo json e salvar na collection person.

## Materias de apoio:
- [Mongo Compatibilidade Docker](https://www.mongodb.com/compatibility/docker)
- [Instalar MongoDB com Docker](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-community-with-docker/) (usei como exemplo o comando do link acima para executar a criação do banco seguindo esse tutorial)
- [MongoDB Get Started](https://www.mongodb.com/basics/get-started)
- [Mongo Compass](https://www.mongodb.com/products/tools/compass)
- [Kafka Spring properties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#messaging.kafka)
- [MongoDB Spring properties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#data.nosql.mongodb)

## Comandos usados para rodar aplicação local

### MongoDB
Criar imagem MongoDB no Docker:
```shell
sudo docker run --name mongodb -d -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=root mongodb/mongodb-community-server:latest
```

Criar usuário no Mongo para conectar aplicação com a base de dados usando Mongo Compass:
```shell
db.createUser({user:"mongoUser",pwd:"mongoUser@",roles:[{role:"readWrite",db:"mongodb-demo"}]})
```
Para conectar a aplicação com o banco não é permitido utilizar o usuário root.

### Kafka
Para criar container do Kafka foi utilizado o arquivo `/docker-compose.yml`:
```shell
sudo docker-compose up -d
```

Acessar container do Kafka no Docker:
```shell
docker exec -it kafka bash
```

Criar Producer Kafka para publicar eventos json no topico da aplicação:
```shell
kafka-console-producer --bootstrap-server localhost:29092 --topic person-topic
```

Payload do evento person para teste:
```json
{"firstName": "Maria", "lastName": "Silva"}
```

