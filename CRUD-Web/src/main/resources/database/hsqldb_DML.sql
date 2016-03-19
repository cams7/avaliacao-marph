--Insere dados pessoais
insert into pessoa(nome, cpf, nascimento) values ('Antonio Ferreira Silva', '01201201200', DATE '1983-4-10');
insert into pessoa(nome, cpf, nascimento) values ('Maria Antonieta de Almeida', '01201201201', DATE '1984-7-6');
insert into pessoa(nome, cpf, nascimento) values ('Fernando José Alves', '01201201202', DATE '1986-2-21');
insert into pessoa(nome, cpf, nascimento) values ('Francisco Oliveira', '01201201203', DATE '1991-6-21');
insert into pessoa(nome, cpf, nascimento) values ('Xavier da Silva Gomes', '01201201204', DATE '1981-6-2');
insert into pessoa(nome, cpf, nascimento) values ('Bruno Mariano Alves', '01201201205', DATE '1950-12-10');
insert into pessoa(nome, cpf, nascimento) values ('Carlos Henrique de Oliveira', '01201201206', DATE '2012-8-18');
insert into pessoa(nome, cpf, nascimento) values ('Davi Morais', '01201201207', DATE '1978-4-30');
insert into pessoa(nome, cpf, nascimento) values ('Ana Aparecida Alves', '01201201208', DATE '1988-1-16');
insert into pessoa(nome, cpf, nascimento) values ('Eugênio Alves Silva', '01201201209', DATE '1960-12-2');
insert into pessoa(nome, cpf, nascimento) values ('Fernando Antônio de Oliveira', '01201201210', DATE '2006-12-13');
insert into pessoa(nome, cpf, nascimento) values ('Carlos Alberto Silva', '01201201211', DATE '1980-11-25');

---Insere dados de usuario
insert into usuario(id_pessoa,login,senha,status) values(1,'admin1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
insert into usuario(id_pessoa,login,senha,status) values(2,'user1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
insert into usuario(id_pessoa,login,senha,status) values(3,'admin2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
insert into usuario(id_pessoa,login,senha,status) values(4,'admin3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
insert into usuario(id_pessoa,login,senha,status) values(5,'user2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
insert into usuario(id_pessoa,login,senha,status) values(6,'user3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
insert into usuario(id_pessoa,login,senha,status) values(7,'admin4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
insert into usuario(id_pessoa,login,senha,status) values(8,'admin5','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
insert into usuario(id_pessoa,login,senha,status) values(9,'admin6','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
insert into usuario(id_pessoa,login,senha,status) values(10,'user4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);

insert into usuario_autorizacao(id_usuario,autorizacao) values(1,0);
insert into usuario_autorizacao(id_usuario,autorizacao) values(1,1);
insert into usuario_autorizacao(id_usuario,autorizacao) values(1,2);
insert into usuario_autorizacao(id_usuario,autorizacao) values(2,0);
insert into usuario_autorizacao(id_usuario,autorizacao) values(2,1);
insert into usuario_autorizacao(id_usuario,autorizacao) values(3,0);
insert into usuario_autorizacao(id_usuario,autorizacao) values(3,2);
insert into usuario_autorizacao(id_usuario,autorizacao) values(4,1);
insert into usuario_autorizacao(id_usuario,autorizacao) values(4,2);
insert into usuario_autorizacao(id_usuario,autorizacao) values(5,0);
insert into usuario_autorizacao(id_usuario,autorizacao) values(6,1);
insert into usuario_autorizacao(id_usuario,autorizacao) values(7,2);
insert into usuario_autorizacao(id_usuario,autorizacao) values(8,0);
insert into usuario_autorizacao(id_usuario,autorizacao) values(8,1);
insert into usuario_autorizacao(id_usuario,autorizacao) values(8,2);
insert into usuario_autorizacao(id_usuario,autorizacao) values(9,2);
insert into usuario_autorizacao(id_usuario,autorizacao) values(10,0);
