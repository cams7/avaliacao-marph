--Insere dados pessoais
LOCK TABLES `pessoa` WRITE;
insert into pessoa(nome, cpf, nascimento) values ('Antonio Ferreira Silva', '40162439717', '1983-4-10');
insert into pessoa(nome, cpf, nascimento) values ('Maria Antonieta de Almeida', '90763518646', '1984-7-6');
insert into pessoa(nome, cpf, nascimento) values ('Fernando José Alves', '37678869282', '1986-2-21');
insert into pessoa(nome, cpf, nascimento) values ('Francisco Oliveira', '27855291202', '1991-6-21');
insert into pessoa(nome, cpf, nascimento) values ('Xavier da Silva Gomes', '80565450778', '1981-6-2');
insert into pessoa(nome, cpf, nascimento) values ('Bruno Mariano Alves', '51245121642', '1950-12-10');
insert into pessoa(nome, cpf, nascimento) values ('Carlos Henrique de Oliveira', '24531410742', '2012-8-18');
insert into pessoa(nome, cpf, nascimento) values ('Davi Morais', '24756267122', '1978-4-30');
insert into pessoa(nome, cpf, nascimento) values ('Ana Aparecida Alves', '23376534128', '1988-1-16');
insert into pessoa(nome, cpf, nascimento) values ('Eugênio Alves Silva', '10552514640', '1960-12-2');
insert into pessoa(nome, cpf, nascimento) values ('Fernando Antônio de Oliveira', '71788975383', '2006-12-13');
insert into pessoa(nome, cpf, nascimento) values ('Carlos Alberto Silva', '86427122444', '1980-11-25');
insert into pessoa(nome, cpf, nascimento) values ('Ana Maria Alves', '61384962646', '1980-07-15');
insert into pessoa(nome, cpf, nascimento) values ('Bruno Pereira Goncalves', '85834813348', '1980-11-25');
insert into pessoa(nome, cpf, nascimento) values ('Wilson Gomes Farias', '43988387673', '1990-03-23');
insert into pessoa(nome, cpf, nascimento) values ('Jose Alberto Alves', '16598642914', '1985-10-22');
insert into pessoa(nome, cpf, nascimento) values ('Maria Evangelina Silva', '37720353293', '1975-09-20');
UNLOCK TABLES;

---Insere dados de usuario
LOCK TABLES `usuario` WRITE;
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(1,'admin1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ROLE_USER-ROLE_NEWUSER-ROLE_ADMIN');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(2,'user1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ROLE_USER-ROLE_NEWUSER');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(3,'admin2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ROLE_USER-ROLE_ADMIN');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(4,'admin3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'ROLE_USER-ROLE_NEWUSER');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(5,'user2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'ROLE_USER');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(6,'user3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ROLE_NEWUSER');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(7,'admin4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ROLE_ADMIN');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(8,'admin5','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'ROLE_USER-ROLE_NEWUSER-ROLE_ADMIN');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(9,'admin6','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'ROLE_ADMIN');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(10,'user4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ROLE_USER');
UNLOCK TABLES;

--Insere dados de endereco
LOCK TABLES `endereco` WRITE;
insert into endereco(id_pessoa,rua,bairro,telefone) values(1,'Rua Numero Um', 'Bairro Numero Um','3136010101');
insert into endereco(id_pessoa,rua,bairro,telefone) values(2,'Rua Numero Dois', 'Bairro Numero Dois','3136010102');
insert into endereco(id_pessoa,rua,bairro,telefone) values(3,'Rua Numero Tres', 'Bairro Numero Tres','3136010103');
insert into endereco(id_pessoa,rua,bairro,telefone) values(4,'Rua Numero Um', 'Bairro Numero Um','3136010104');
insert into endereco(id_pessoa,rua,bairro,telefone) values(5,'Rua Numero Quatro', 'Bairro Numero Quatro','3136010105');
insert into endereco(id_pessoa,rua,bairro,telefone) values(6,'Rua Numero Dois', 'Bairro Numero Dois','3136010102');
insert into endereco(id_pessoa,rua,bairro,telefone) values(1,'Rua Numero Cinco', 'Bairro Numero Cinco','3136010106');
insert into endereco(id_pessoa,rua,bairro,telefone) values(2,'Rua Numero Seis', 'Bairro Numero Seis','3136010107');
insert into endereco(id_pessoa,rua,bairro,telefone) values(1,'Rua Numero Tres', 'Bairro Numero Um','3136010108');
UNLOCK TABLES;