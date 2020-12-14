# aop-demo
Programação orientada a aspecto (AOP) é um paradigma de para modularizar lógica de desenvolvimento que se repete no projeto (crosscutting concern), ou seja, aquela lógica que não está ligada ao negócio, mas que garante algumas funcionalidades da aplicação, que está presente em mais de um lugar do código seja concentrada em um lugar só e a partir dessa separação deve-se garantir que sempre que acontecer uma ação que necessite dessa lógica ela deverá ser invocada.

Os conceitos básicos sobre o AOP são:
 - Aspect: o principal conceito desse paradigma, é o lugar onde se encontra o crosscutting concern que foi identificado e modularizado;
 - Jointpoint: a lógica de negócio que será interceptada ao ser invocada no uso da aplicação;
 - Advice: ação a ser executada pelo aspect quando um joinpoint é interceptado;
 - Pointcut: expressão para identificar um ou mais jointpoints, todo advice está atrelado a um pointcut;

Quando um objeto é interceptado pelo aspect (que possui o advice com a ação e o pointcut que identifica o joinpoint), ele passa a ser chamado de target object ou adviced object.

O advice possui 5 tipos que são definidos pelo momento que ele será invocado na chamada do joinpoint:
 - Before advice: advice que é executado antes do jointpoint
 - After returning advice: advice que é executado depois que o joinpoint finaliza sua execução com sucesso
 - After throwing advice: advice que é executado depois que um jointpoint lança uma exceção
 - After (finally) advice: advice  que é executado depois da execução de um jointpoint (com ou sem erro)
 - Around advice: advice que "cerca" um jointpoint, ou seja, quando o jointpoint é invocado o advice que é executado no seu lugar, e o mesmo advice pode executar ou não o jointpoint. Isso permite que o advice tenha controle sobre o que chega e que volta do jointpoint além do comportamento do jointpoint.

Para construir as expressões no pointcut se usa o pointcut designator (PCD):
 - execution: pcd básico e mais utilizado que identifica quais métodos a serem interceptados
 - within: pcd para identificar métodos a partir de suas classes ou pacotes
 - this: pcd para identificar métodos onde a referência do bean (Spring AOP proxy) é do tipo especificado na expressão
 - target: pcd para identificar métodos do tipo (a classe) especificado na expressão
 - args: pcd para identificar métodos onde os parâmetros do mesmo são do tipo especificado na expressão e disponibiliza os argumentos do joinpoit para o advice
 - @annotation: pdc para identificar o método com a anotação especificada na expressão
 - @within: pcd para identificar métodos onde a classe ou pacote possui a anotação especificada na expressão
 - @target: pdc para identificar a classe a partir da anotação especificada na expressão
 - @args: pdc para identificar métodos com parâmetros que estão anotados com a anotação especificada na expressão
 - bean: pcd para identificar joinpoints a partir do nome de um ou mais beans (se usado wildcard/asterisco)

Escrever um bom pointcut é deixar ele mais específico possível, assim terá uma melhor desempenho ao procurar o joinpoint em questão. Cada PCD acima é usado para identificar qual o tipo do joinpoint que será interceptado, esses tipos são classificados em 3 grupos:
 - Kinded designator: seleciona um jointpoint em específico -> execution
 - Scoping designator: seleciona um grupo de jointpoints (provavelmente mais de um tipo) -> within
 - Contextual designator: seleciona os jointpoints a partir do seu contexto -> this, target e @annotation
Ou seja, para escrever um bom pointcut é recomendado utilizar, pelo menos, kinded e scoping.

Agora pro que interessa ... *AOP Proxy*!
Numa definição mais conceitual, AOP proxy é uma classe que implementa o contrato de um aspect de forma dinâmica, ou seja, na chamada de um jointpoint, o target object é encapsulado por um proxy que por sua vez possui a implementação de um aspect. No Spring AOP funcionada da seguinte forma:
- Spring AOP proxy tem esse nome pq a implementação do Spring para esse paradigma é baseado em proxy, ou seja, para toda chama de método, um proxy é gerado e o target object em questão é encapsulado no mesmo
- Spring AOP proxy suporta chamadas de método como sendo jointpoint
- Por default ele faz uso de JDK dynamic proxy para target object que implementa uma interface, mas quando não tem interface faz uso do CGLib proxy

Toda essa volta para enteder que o Spring faz uso do AOP proxy justamente para adicionar comportamento aos target objects conforme a anotação presente nele de forma DESACOPLADA, por exemplo, o método anotado com @Transactional é encapsulado por um proxy e esse por sua vez gerencia a transação na base de dados.
