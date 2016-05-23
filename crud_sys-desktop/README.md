Exemplo de uma Aplicação Desktop
========================
* Autor: César Magalhães
* Tecnologias: JPA 2.1, Hibernate 5
* Banco de dados: Firebird 1.5.6, HSQLDB
* Resumo: Aplicação Desktop
* Linguagem: Java
* Fonte: <https://github.com/cams7/desktop-app>

Sistema requerido
-------------------

* JDK 1.8
* Maven 3.3.9
* Firebird 1.5.6

Para rodar o programa
-------------------

_1. No diretório onde o projeto foi baixado, execute as linhas de comando abaixo:_

	mvn clean install
	mvn exec:java
	
_2. No diretório onde o projeto foi baixado, execute as linhas de comando abaixo:_

	mvn clean install -Plib
	java -jar target/desktop-app.jar
