![Logo of the project](https://metrics.dropwizard.io/4.0.0/_static/metrics-logo.png)

[![Build Status](https://travis-ci.org/condessalovelace/refmetrics.svg?branch=master)](https://travis-ci.org/condessalovelace/refmetrics) ![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=br.com.condessalovelace%3Arefmetrics&metric=alert_status)

# refmetrics
> Projeto de referência para o uso do Metrics

Esse projeto ilustra como deve ser feito o uso do [Metrics](https://metrics.dropwizard.io/4.0.0/) para instrumentar aplicações Java através de casos de teste no JUnit. Esses testes deverão ser usados como base para implementar a instrumentação nas classes de uma aplicação Java.

## Começando

Para rodar o projeto, instalar os seguintes programas:

- [JDK 1.8](http://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html): Para executar o projeto Java
- [Maven (>= 3.3.9)](https://maven.apache.org/download.cgi): Necessário para realizar o build do projeto Java
- [Eclipse](http://www.eclipse.org/downloads/): Para o desenvolvimento do projeto

### Configuração inicial

Para que o arquivo `log4j.xml` seja utilizado pela aplicação, é necessário remover a exclusão do mesmo do `Build path` da aplicação. Isso pode ser feito selecionado com o botão direito o projeto e acessando `Build Path > Configure Build Path` e mudar em `src/test/resources` o campo `Excluded` para `(None)`.

## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```shell
cd "diretório de sua preferência"
git clone https://github.com/condessalovelace/refmetrics.git
```

### Construção

Para construir o projeto com o Maven, executar os comando abaixo:

```shell
mvn clean install
```

O comando irá baixar todas as dependências do projeto e criar um diretório `target` com os artefatos construídos, que incluem o arquivo `jar` do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

## Features

O projeto ilustra o uso das seguintes features do Metrics:

- [Meter](https://metrics.dropwizard.io/4.0.0/manual/core.html#meters)
- [Gauge](https://metrics.dropwizard.io/4.0.0/manual/core.html#gauges)
- [Histogram](https://metrics.dropwizard.io/4.0.0/manual/core.html#histograms)
- [Timer](https://metrics.dropwizard.io/4.0.0/manual/core.html#timers)
- [HealthCheck](https://metrics.dropwizard.io/4.0.0/manual/healthchecks.html)
- [JVM Instrumentation](https://metrics.dropwizard.io/4.0.0/manual/jvm.html)

## Configuração

Para executar o projeto, é necessário utilizar o Eclipse, para que o mesmo identifique as dependências necessárias para a execução no repositório `.m2` do Maven. Uma vez importado o projeto, será criado um arquivo `.classpath` que irá informar qual a classe principal para a execução.

## Contribuições


Contribuições são sempre bem-vindas! Para contribuir lembre-se sempre de adicionar testes unitários para as novas classes com a devida documentação. Assim será possível explorar outras funcionalidades do Metrics.

## Links

- [Metrics](https://metrics.dropwizard.io/4.0.0/)
- [Spring-metrics](https://github.com/ryantenney/metrics-spring)

## Licença

Não possui.
