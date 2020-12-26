# jax-rs-demo

Estudo sobre jax-rs com Rest Assured. 

Problemas:
 - conflito de versões do rest assured com os starters do spring boot foram resolvidos importando 3 modulos do rest assured
```
java.lang.NoClassDefFoundError: io/restassured/path/json/mapper/factory/JsonbObjectMapperFactory
```
 
 - Foi necessário _forçar_ a vesão do groovy 3.0.2 para resolver o seguinte erro:
 ```
 java.lang.AbstractMethodError: Receiver class io.restassured.internal.RequestSpecificationImpl does not define or inherit an implementation of the resolved method 'abstract java.lang.Object invokeMethod(java.lang.String, java.lang.Object)' of interface groovy.lang.GroovyObject.
```
