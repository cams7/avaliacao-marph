--Insere dados pessoais
insert into pessoa(nome, cpf, nascimento) values ('Antonio Ferreira Silva', '01201201200', '1983-4-10');
insert into pessoa(nome, cpf, nascimento) values ('Maria Antonieta de Almeida', '01201201201', '1984-7-6');
insert into pessoa(nome, cpf, nascimento) values ('Fernando José Alves', '01201201202', '1986-2-21');
insert into pessoa(nome, cpf, nascimento) values ('Francisco Oliveira', '01201201203', '1991-6-21');
insert into pessoa(nome, cpf, nascimento) values ('Xavier da Silva Gomes', '01201201204', '1981-6-2');
insert into pessoa(nome, cpf, nascimento) values ('Bruno Mariano Alves', '01201201205', '1950-12-10');
insert into pessoa(nome, cpf, nascimento) values ('Carlos Henrique de Oliveira', '01201201206', '2012-8-18');
insert into pessoa(nome, cpf, nascimento) values ('Davi Morais', '01201201207', '1978-4-30');
insert into pessoa(nome, cpf, nascimento) values ('Ana Aparecida Alves', '01201201208', '1988-1-16');
insert into pessoa(nome, cpf, nascimento) values ('Eugênio Alves Silva', '01201201209', '1960-12-2');
insert into pessoa(nome, cpf, nascimento) values ('Fernando Antônio de Oliveira', '01201201210', '2006-12-13');
insert into pessoa(nome, cpf, nascimento) values ('Carlos Alberto Silva', '01201201211', '1980-11-25');
insert into pessoa(nome, cpf, nascimento) values ('Ana Maria Alves', '01201201216', '1980-07-75');
insert into pessoa(nome, cpf, nascimento) values ('Bruno Pereira Goncalves', '01201201212', '1980-11-25');
insert into pessoa(nome, cpf, nascimento) values ('Wilson Gomes Farias', '01201201213', '1990-03-23');
insert into pessoa(nome, cpf, nascimento) values ('Jose Alberto Alves', '01201201214', '1985-10-22');
insert into pessoa(nome, cpf, nascimento) values ('Maria Evangelina Silva', '01201201215', '1975-09-20');

---Insere dados de usuario
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

--insert into usuario(id_pessoa,login,senha,status) values(1,'admin1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
--insert into usuario(id_pessoa,login,senha,status) values(2,'user1','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
--insert into usuario(id_pessoa,login,senha,status) values(3,'admin2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
--insert into usuario(id_pessoa,login,senha,status) values(4,'admin3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
--insert into usuario(id_pessoa,login,senha,status) values(5,'user2','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
--insert into usuario(id_pessoa,login,senha,status) values(6,'user3','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
--insert into usuario(id_pessoa,login,senha,status) values(7,'admin4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);
--insert into usuario(id_pessoa,login,senha,status) values(8,'admin5','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
--insert into usuario(id_pessoa,login,senha,status) values(9,'admin6','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 0);
--insert into usuario(id_pessoa,login,senha,status) values(10,'user4','$2a$10$bu2RRFWNW1K/erN7YPDc6uKO4nqEaclp5QOnFiDgNipyOmBYIQ0KS', 1);


--insert into usuario_autorizacao(id_usuario,autorizacao) values(1,0);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(1,1);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(1,2);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(2,0);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(2,1);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(3,0);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(3,2);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(4,1);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(4,2);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(5,0);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(6,1);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(7,2);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(8,0);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(8,1);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(8,2);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(9,2);
--insert into usuario_autorizacao(id_usuario,autorizacao) values(10,0);

--select u.id_usuario, p.nome,u.login, u.status, GROUP_CONCAT(a.autorizacao SEPARATOR ',') as autorizacoes from pessoa p join usuario u on p.id_pessoa=u.id_pessoa join usuario_autorizacao a on u.id_usuario=a.id_usuario group by u.id_usuario;
