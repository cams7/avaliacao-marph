# AVALIAÇÃO DESENVOLVEDOR JAVA
# O sistema "Exemplo CRUD" foi desenvolvido e testado no Java7 e Tomcat7. Esse e um sistema web que ultiliza, principalmente, as tecnologias Hibernate4, Spring4, Jsf2 e Primefaces5. O Maven e utilizado para automatizar a compilação de todo o código da aplicação.
# As 3 tabelas do banco de dados são populadas na inicialização da aplicação e os dados são salvos em memória através do Hsqldb que e o banco padrão, mas também é possível utilizar o Mysql, para isso basta alterar o arquivo database.properties que está localizado no diretório 'crud_sys/CRUD-Web/src/main/resources/database'. 

# Para rodar a aplicação siga as instruções abaixo:
- Instale o Git, caso ainda não o tenha instalado.
- Para baixar o projeto, no CONSOLE do Linux ou no CMD do Windows, execute o comando "git clone https://github.com/cams7/crud_sys.git".
- Baixe o Tomcat7, caso ainda não o tenha baixado.
- Inclua o usuário e senha "tomcat" no servidor Tomcat7. Para isso, inclua a tag "\<user username="tomcat" password="tomcat" roles="manager-script"/\>" dentro da tag "\<tomcat-users\>" no arquivo 'tomcat-users.xml'.
- Inicialize o Tomcat7.
- Instale o Maven, caso ainda não o tenha instalado.
- Atraves do CONSOLE do Linux ou o CMD do Windows, digite o comando "cd crud_sys" no diretório onde foi clonado o projeto.
- Para compilar o projeto e baixar as dependências, execute o comando "mvn clean install".
- Com o Tomcat7 inicializado, digite o comando "cd CRUD-Web" e dentro desse diretório execute o comando "mvn tomcat7:deploy". Caso ocorra algum erro, verifique se o Tomcat7 foi inicializado corretamente ou abra o arquivo "crud_sys/CRUD-Web/pom.xml" e depois movimente o cursor ate a linha "193" então altere, se necessário, o valor da tag "url" para o endereço correto do Tomcat7.  
- Copie o endereço "http://localhost:8080/crud_sys" no navegador e teste a aplicação. Se o host e ou a porta do Tomcat7 forem diferentes, ocorrera um erro, e então será necessário informar endereço correto do Tomcat7.  
- Apos o teste finalizado, execute o comando "mvn tomcat7:undeploy" dentro do diretório 'CRUD-Web' e pare o Tomcat7.
- Outra possibilidade e importar o modulo Maven para dentro do Eclipse.
