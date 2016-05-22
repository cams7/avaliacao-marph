Exemplo de uma Aplicação CRUD (create, read, update, delete)
========================
* Autor: César Magalhães
* Tecnologias: **JPA 2.1**, **Hibernate 5.1.0**, **JSF 2**, **Primefaces 5.3**, **EJB 3.1**, **Spring 4.2.6**, **Jasperreports 6.2.1**
* Banco de dados: **Firebird 1.5.6**, **PostgreSQL**, **H2**
* Resumo: Aplicação CRUD (create, read, update, delete)
* Linguagem: **Java 8**
* Fonte: <https://github.com/cams7/crud_sys>
* Site: <http://crud-cams7.rhcloud.com>

O que é o CRUDSys?
-------------------
O sistema **CRUDSys** foi desenvolvido e testado no **Wildfly 10** e **Tomcat 8**. Esse e um sistema web que utiliza, principalmente, as tecnologias **Hibernate**, **Spring** e **Primefaces**. O Maven é utilizado para automatizar a compilação de todos os códigos da aplicação.

Sistemas requeridos
-------------------
* [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html?ssSourceSiteId=otnpt)
* [Maven 3.3.9](https://maven.apache.org/download.cgi)
* [Wildfly 10.0.0.Final](http://wildfly.org/downloads/)
* [Tomcat 8.0.35](https://tomcat.apache.org/download-80.cgi) 
* [Firebird 1.5.6](http://www.firebirdsql.org/en/firebird-1-5/)
* [PostgreSQL](http://www.postgresql.org/download/)
* [Git](https://git-scm.com/downloads)

Para rodar o programa
-------------------
* Instale os seguintes programas: **JDK 8**, **Git**, **Firebird 1.5.6**, **PostgreSQL**, caso ainda não os tenha instalados.
* No **Firebird**, crie um banco o qual o nome seja **crud_sys**.
* No **Postgres**, crie um banco o qual o nome seja **crud_sys**.
* Configure a variável de ambiente do **Maven 3.3.9**, em seguida, reinicie o PC.
* Para baixar o projeto (**crud_sys**), execute a linha abaixo:

		git clone https://github.com/cams7/crud_sys.git
	
* Caso deseje rodar o **crud_sys** num [PAAS](https://pt.wikipedia.org/wiki/Plataforma_como_serviço), primeiro e necessário ter uma conta no **Github** e **Openshift**. No Github, faça um **Fork** desse projeto, em seguida, clone-o do seu repositório. No Openshift, crie uma aplicação **Wildfly 10**, e também um banco **PostgreSQL 9.2**, depois [sincronize-a com a aplicação do Github](https://developer.jboss.org/wiki/Enable-openshift-ciFullExampleUsingOpenshift-java-client), para isso, execute as linhas abaixo:

		git clone https://github.com/<SEU USUÁRIO DO GITHUB>/crud_sys.git
		cd crud_sys
		git remote add openshift -f <URL DO GIT DA APLICAÇÃO NO OPENSHIFT>
		git merge openshift/master -s recursive -X ours
	
* Obs.: Antes de executar a linha abaixo, algumas alterações no arquivo **pom.xml** terão que ser feitas, por isso, pule-a e siga as instruções abaixo.

		git push openshift HEAD
	
* Baixe o **Tomcat 8** e **Wildfly 10**, caso ainda não o tenha baixado.

* No **Tomcat 8**, inclua o usuário e senha **tomcat**. Para isso, inclua a tag *\<user username="tomcat" password="tomcat" roles="manager-script"/\>* dentro da tag *\<tomcat-users\>* no arquivo **tomcat-users.xml**.

* No **Wildfly 10**, [crie um Datasouce para o PostgreSQL](https://desenvolvo.wordpress.com/2012/06/21/configurando-ds-jbossas7/).
* No **Wildfly 10**, crie um Datasorce para o Firebird.

* Obs.: As configurações da conexão do banco de dados estão no arquivo **pom.xml** que esta na raiz do projeto. Antes de rodar a aplicação, altere essas configurações de acordo com o banco que esta sendo usado.

1. Inicialize o **Wildfly 10**, em seguida, no diretório onde o projeto foi baixado, execute as linhas abaixo:

		mvn clean install wildfly:deploy
	
2. Inicialize o **Tomcat 8**, em seguida, no diretório onde o projeto foi baixado, execute as linhas abaixo:
	
		cd crud_sys-cw/crud_cw-jsf/crud_jsf-war/
		mvn clean install tomcat7:deploy
	
* Obs.: No navegador, informe este endereço: <http://localhost:8080/crud_sys>, caso as portas, tanto do **Wildfly** quanto do **Tomcat**, sejam **8080**.
