# junit-demo

*Esse é apenas um resumo/anotações elaborados com base na documentação oficial para fins de estudo*

### JUnit 4

#### Como escrever um teste

#### Rule

#### Setup e Tears Down

####  Asserts

### JUnit 5

JUnit 5 é versão onde foi feita uma restruturação na implementação/arquitetura da biblioteta JUnit, que resultou em vários módulos e APIs para desenvolvimento e execução de testes, assim como implementação de novas bilbiotecas com base no JUnit. Essas módulos estão presentes em 3 sub-projetos: JUnit Platform, JUnit Jupiter e JUnit Vintage.

![](/home/adriane/dev/repositorios/demo projects/junit-demo/junit5.png)

**JUnit Platform** é a base para execução de testes na JVM e define uma API para desenvolvimento desses teste e de frameworks baseados nessa nova versão. Além disso também possui um *console launcher* para executar em linha de comando e fornece compatibilidade com testes e frameworks que utilizam a versão 4. 

**Junit Jupiter** possui uma API para desenvolver testes e bibliotecas baseadas no JUnit 5.

![](/home/adriane/dev/repositorios/demo projects/junit-demo/junit5-jupiter.png)

**JUnit Vintage** fornece compatibilidade com testes e ferramentas baseadas nas versões anterios 4 e 3.



#### Escrever o teste 

Escrever um teste com *junit5* em sua forma mais simples consite em: uma classe com um método anotaco com `@Test`. A classe em questão não pode ser abstrata e deve ter apenas um construtor, enquanto o método em questão não precisa ser definido como plublico mas é necessário ser anotado com @Test ou qualoquer outa anotação que extenda `@Test`. Há também métodos relacionados ao ciclo de vida do teste, esses tem a anotação `@Before/After` ou `@BeforeAll/AfterAll`.

Essas e outras ferramentas para o desenvolvimento do teste acima estão presentes no **JUnit Jupiter**, nele vc enconta as anotações e interfaces para criar seus testes ou biblioteca baseada no *junit5*. Nele estão, por exemplo, a API de assertions e as principais anotações (incluindo o cicli de vida do teste).

Importante ressaltar que essa versão do *junit5* permite o uso das expressões lambdas disponíveis a partir do *java8*. Por exemplo:

```java
@Test
void testOnlyOnDeveloperWorkstation() {
	assumeTrue("DEV".equals(System.getenv("ENV")),
    	() -> "Aborting test: not on developer workstation");
}
```

(código copiado da [documentação oficial](https://junit.org/junit5/docs/current/user-guide/))

#### Nested test class

Como o nome bem sugere, `@Nested` class é uma anotação utilizada para aninhar e definir a hierarquia de classes dentro de uma classe de teste, isto é, auxilia na estrutura de classes e cenários relacionados e ajuda a manter o código legível e limpo.

Para aplicar essa anotação, geralmente ocorre em classes de teste cujo o seu tamanho é grande e possui vários cenários relacionado a uma classe ou camada da sua aplicação. Por exemplo, dada a seguinte classe, UserService, elá é responsável pela camada de négocio referente ao usuário do sistema, nesse casso os cenários podem ser listados por classe ou por método.

Um boa dica para auxiliar na escrita e manutenção de um teste utilizando essa anotação é fazendo o uso de `@DisplayName`, assim você define de forma clara os cenários e sua finalidade.

#### Extensão e extenções comuns

#### Setup e Tears down

#### Teste parametrizado

O junit5 oferece um meio de executar mais de uma vez um mesmo teste mas com parâmetros diferentes para então evitar código repitido. Para esse tipo de cenário é preciso utilizar a anotação `@ParameterizedTest` e fornecer pelo menos um parâmetro para ser consumido pelo teste através da anotação `@ValueSource` .

Para verificar valores vazios ou nulos, é possível realizar o teste fazendo o usa de `@NullSource` e `@EmptySource`, há tb `@NullAndEmptySOurce`, uma alternativa quado for necessário o uso das duas anotações anteriores juntas.

Dentre várias formas de declarar os tipos de argumentos a serem consumidos pelo teste parametrizado é possível fazer o uso combinado desses *sources* como, por exemplo, um test que checa o parametro nulo e com variações de strings:

```java
@ParameterizedTest
@NullSource
@ValueSource(strings = { " ", "   ", "\t", "\n" })
void nullEmptyAndBlankStrings(String text) {
    assertTrue(text == null || text.trim().isEmpty());
}
```

(código copiado da [documentação oficial](https://junit.org/junit5/docs/current/user-guide/))

#### Resolver parametros

#### Assertions



***Diferença entre as versões;***

**Referências:**

JUnit 5 User Guide. Disponível em: https://junit.org/junit5/docs/current/user-guide
Mkyong. JUnit 5 Nested Tests. Disponível em: https://mkyong.com/junit5/junit-5-nested-test-examples

