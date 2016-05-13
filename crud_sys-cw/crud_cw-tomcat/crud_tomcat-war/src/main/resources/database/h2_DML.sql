--Insere dados pessoais
insert into pessoa(nome, cpf, nascimento) values ('Antonio Ferreira Silva', '40162439717', parsedatetime('1983-04-10', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Maria Antonieta de Almeida', '90763518646', parsedatetime('1984-07-06', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Fernando José Alves', '37678869282', parsedatetime('1986-02-21', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Francisco Oliveira', '27855291202', parsedatetime('1991-06-21', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Xavier da Silva Gomes', '80565450778', parsedatetime('1981-06-02', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Bruno Mariano Alves', '51245121642', parsedatetime('1950-12-10', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Carlos Henrique de Oliveira', '24531410742', parsedatetime('2012-08-18', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Davi Morais', '24756267122', parsedatetime('1978-04-30', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Ana Aparecida Alves', '23376534128', parsedatetime('1988-01-16', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Eugenio Alves Silva', '10552514640', parsedatetime('1960-12-02', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Fernando Antonio de Oliveira', '71788975383', parsedatetime('2006-12-13', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Carlos Alberto Silva', '86427122444', parsedatetime('1980-11-25', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Ana Maria Alves', '61384962646', parsedatetime('1980-07-15', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Bruno Pereira Goncalves', '85834813348', parsedatetime('1980-11-25', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Wilson Gomes Farias', '43988387673', parsedatetime('1990-03-23', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Jose Alberto Alves', '16598642914', parsedatetime('1985-10-22', 'yyyy-MM-dd'));
insert into pessoa(nome, cpf, nascimento) values ('Maria Evangelina Silva', '37720353293', parsedatetime('1975-09-20', 'yyyy-MM-dd'));

--Insere dados de usuario
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(1,'admin1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE-SECRETARIO-ADMINISTRADOR');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(2,'sec1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE-SECRETARIO');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(3,'admin2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE-ADMINISTRADOR');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(4,'sec2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'CLIENTE-SECRETARIO');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(5,'user1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'CLIENTE');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(6,'sec3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'SECRETARIO');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(7,'admin3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'ADMINISTRADOR');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(8,'admin4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'CLIENTE-SECRETARIO-ADMINISTRADOR');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(9,'admin5','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0, 'ADMINISTRADOR');
insert into usuario(id_pessoa,login,senha,status,autorizacoes) values(10,'user2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1, 'CLIENTE');

--Insere dados de endereco
insert into endereco(id_pessoa,rua,bairro,telefone) values(1,'Rua Numero Um', 'Bairro Numero Um','3136010101');
insert into endereco(id_pessoa,rua,bairro,telefone) values(2,'Rua Numero Dois', 'Bairro Numero Dois','3136010102');
insert into endereco(id_pessoa,rua,bairro,telefone) values(3,'Rua Numero Tres', 'Bairro Numero Tres','3136010103');
insert into endereco(id_pessoa,rua,bairro,telefone) values(4,'Rua Numero Um', 'Bairro Numero Um','3136010104');
insert into endereco(id_pessoa,rua,bairro,telefone) values(5,'Rua Numero Quatro', 'Bairro Numero Quatro','3136010105');
insert into endereco(id_pessoa,rua,bairro,telefone) values(6,'Rua Numero Dois', 'Bairro Numero Dois','3136010102');
insert into endereco(id_pessoa,rua,bairro,telefone) values(1,'Rua Numero Cinco', 'Bairro Numero Cinco','3136010106');
insert into endereco(id_pessoa,rua,bairro,telefone) values(2,'Rua Numero Seis', 'Bairro Numero Seis','3136010107');
insert into endereco(id_pessoa,rua,bairro,telefone) values(1,'Rua Numero Tres', 'Bairro Numero Um','3136010108');