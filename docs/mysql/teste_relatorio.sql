select p.nome, p.nascimento, p.cpf from pessoa p order by p.nome asc;
select p.nome as 'pessoa.nome', u.login, u.status as habilitado from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa;
select p.nome as 'pessoa.nome', e.bairro, e.rua, e.telefone from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa;