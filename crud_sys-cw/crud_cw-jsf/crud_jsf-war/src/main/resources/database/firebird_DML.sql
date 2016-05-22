--Configuracao para o banco Firebird 1.5.6
--dialect=${hibernate.dialect}
--driverClassName=${datasource.driverClassName}
--url=${datasource.connection.url}
--username=${datasource.user-name}
--password=${datasource.password}
--dml=${hibernate.hbm2ddl.import_files}

--Insere dados pessoais
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Antonio Ferreira Silva', '40162439717', '1983-04-10');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Maria Antonieta de Almeida', '90763518646', '1984-07-06');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Fernando José Alves', '37678869282', '1986-02-21');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Francisco Oliveira', '27855291202', '1991-06-21');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Xavier da Silva Gomes', '80565450778', '1981-06-02');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Bruno Mariano Alves', '51245121642', '1950-12-10');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Carlos Henrique de Oliveira', '24531410742', '2012-08-18');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Davi Morais', '24756267122', '1978-04-30');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Ana Aparecida Alves', '23376534128', '1988-01-16');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Eugenio Alves Silva', '10552514640', '1960-12-02');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Fernando Antonio de Oliveira', '71788975383', '2006-12-13');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Carlos Alberto Silva', '86427122444', '1980-11-25');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Ana Maria Alves', '61384962646', '1980-07-15');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Bruno Pereira Goncalves', '85834813348', '1980-11-25');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Wilson Gomes Farias', '43988387673', '1990-03-23');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Jose Alberto Alves', '16598642914', '1985-10-22');
insert into pessoa(id_pessoa, nome, cpf, nascimento) values (GEN_ID(GEN_PESSOA_ID,1), 'Maria Evangelina Silva', '37720353293', '1975-09-20');

--Insere dados de usuario
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 1,'admin1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE-SECRETARIO-ADMINISTRADOR');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 2,'sec1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE-SECRETARIO');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 3,'admin2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE-ADMINISTRADOR');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 4,'sec2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'CLIENTE-SECRETARIO');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 5,'user1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'CLIENTE');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 6,'sec3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'SECRETARIO');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 7,'admin3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ADMINISTRADOR');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 8,'admin4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'CLIENTE-SECRETARIO-ADMINISTRADOR');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 9,'admin5','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'ADMINISTRADOR');
insert into usuario(id_usuario, id_pessoa, login, senha, status, autorizacoes) values(GEN_ID(GEN_USUARIO_ID,1), 10,'user2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE');

--Insere dados de endereco
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 1,'Rua Numero Um', 'Bairro Numero Um','3136010101');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 2,'Rua Numero Dois', 'Bairro Numero Dois','3136010102');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 3,'Rua Numero Tres', 'Bairro Numero Tres','3136010103');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 4,'Rua Numero Um', 'Bairro Numero Um','3136010104');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 5,'Rua Numero Quatro', 'Bairro Numero Quatro','3136010105');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 6,'Rua Numero Dois', 'Bairro Numero Dois','3136010102');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 1,'Rua Numero Cinco', 'Bairro Numero Cinco','3136010106');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 2,'Rua Numero Seis', 'Bairro Numero Seis','3136010107');
insert into endereco(id_endereco, id_pessoa, rua, bairro, telefone) values(GEN_ID(GEN_ENDERECO_ID,1), 1,'Rua Numero Tres', 'Bairro Numero Um','3136010108');
