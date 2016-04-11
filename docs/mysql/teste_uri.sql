#http://localhost:8080/avaliacao_marph/req/pessoa
select p.* from pessoa p;

#http://localhost:8080/avaliacao_marph/req/pessoa/1
select p.* from pessoa p where p.id_pessoa=1;

#http://localhost:8080/avaliacao_marph/req/pessoa/search?page_size=5
select p.* from pessoa p limit 5;

#http://localhost:8080/avaliacao_marph/req/pessoa/search?page_size=10&sort_field=nome&sort_order=ASCENDING
select p.* from pessoa p order by p.nome asc limit 10;

#http://localhost:8080/avaliacao_marph/req/pessoa/search?page_first=10&page_size=5&sort_field=nome&sort_order=DESCENDING
select p.* from pessoa p order by p.nome desc limit 10,5;

#http://localhost:8080/avaliacao_marph/req/pessoa/search?page_first=9&page_size=3&sort_field=nascimento&sort_order=ASCENDING&filter_field=nome&filter_field=cpf&globalFilter=a
select p.* from pessoa p where (lower(p.nome) like '%a%' or lower(p.cpf) like '%a%') order by p.nascimento asc limit 9, 3;

#http://localhost:8080/avaliacao_marph/req/pessoa/search?page_first=5&page_size=5&sort_field=nome&sort_order=DESCENDING&nome=m&cpf=6
select p.* from pessoa p where (lower(p.cpf) like '%6%') and (lower(p.nome) like '%m%') order by p.nome desc limit 5, 5;

#http://localhost:8080/avaliacao_marph/req/pessoa/search?page_first=0&page_size=15&sort_field=nascimento&sort_order=DESCENDING&filter_field=nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
select p.* from pessoa p where (lower(p.cpf) like '%6%') and (lower(p.nome) like '%a%') and (lower(p.nome) like '%m%' or lower(p.cpf) like '%m%') order by p.nascimento desc limit 0, 15;

#http://localhost:8080/avaliacao_marph/req/pessoa/count
select count(p.id_pessoa) from pessoa p;

#http://localhost:8080/avaliacao_marph/req/pessoa/count?filter_field=nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
select count(p.id_pessoa) from pessoa p where (lower(p.cpf) like '%6%') and (lower(p.nome) like '%a%') and (lower(p.nome) like '%m%' or lower(p.cpf) like '%m%');

#http://localhost:8080/avaliacao_marph/req/usuario
select u.* from usuario u;

#http://localhost:8080/avaliacao_marph/req/usuario/1
select u.* from usuario u where u.id_usuario=1;

#http://localhost:8080/avaliacao_marph/req/usuario/search?page_size=10
select * from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa limit 10;

#http://localhost:8080/avaliacao_marph/req/usuario/search?page_size=6&sort_field=pessoa.nome&sort_order=ASCENDING
select * from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa order by p.nome asc limit 6;

#http://localhost:8080/avaliacao_marph/req/usuario/search?page_first=6&page_size=3&sort_field=login&sort_order=DESCENDING
select * from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa order by u.login desc limit 6, 3;

#http://localhost:8080/avaliacao_marph/req/usuario/search?page_first=5&page_size=5&sort_field=pessoa.nascimento&sort_order=ASCENDING&filter_field=pessoa.nome&filter_field=login&globalFilter=a
select * from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa where lower(u.login) like '%a%' or lower(p.nome) like '%a%' order by p.nascimento asc limit 5, 5;

#http://localhost:8080/avaliacao_marph/req/usuario/search?page_first=0&page_size=10&sort_field=pessoa.nome&sort_order=DESCENDING&login=a&habilitado=true
select * from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa where u.status=1 and (lower(u.login) like '%a%') order by p.nome desc limit 0,10;

#http://localhost:8080/avaliacao_marph/req/usuario/search?page_first=0&page_size=15&sort_field=pessoa.nascimento&sort_order=DESCENDING&filter_field=pessoa.nome&filter_field=login&globalFilter=a&pessoa.nome=m&login=s&habilitado=false
select * from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa where u.status=0 and (lower(u.login) like '%s%') and (lower(p.nome) like '%m%') and (lower(u.login) like '%a%' or lower(p.nome) like '%a%') order by p.nascimento desc limit 0,15;

#http://localhost:8080/avaliacao_marph/req/usuario/count
select count(u.id_usuario) from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa;

#http://localhost:8080/avaliacao_marph/req/usuario/count?filter_field=pessoa.nome&filter_field=login&globalFilter=a&pessoa.nome=m&login=s
select count(u.id_usuario) from usuario u left outer join pessoa p on u.id_pessoa=p.id_pessoa where (lower(p.nome) like '%m%') and (lower(u.login) like '%s%') and (lower(u.login) like '%a%' or lower(p.nome) like '%a%');

#http://localhost:8080/avaliacao_marph/req/endereco
select e.* from endereco e;

#http://localhost:8080/avaliacao_marph/req/endereco/1
select e.* from endereco e where e.id_endereco=1;

#http://localhost:8080/avaliacao_marph/req/endereco/search?page_size=5
select * from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa limit 5;

#http://localhost:8080/avaliacao_marph/req/endereco/search?page_size=10&sort_field=pessoa.nome&sort_order=ASCENDING
select * from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa order by p.nome asc limit 10;

#http://localhost:8080/avaliacao_marph/req/endereco/search?page_first=0&page_size=15&sort_field=bairro&sort_order=DESCENDING
select * from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa order by e.bairro desc limit 0, 15;

#http://localhost:8080/avaliacao_marph/req/endereco/search?page_first=5&page_size=5&sort_field=pessoa.nascimento&sort_order=ASCENDING&filter_field=pessoa.nome&filter_field=bairro&filter_field=rua&globalFilter=a
select * from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa where lower(e.rua) like '%a%' or lower(e.bairro) like '%a%' or lower(p.nome) like '%a%' order by p.nascimento asc limit 5, 5;

#http://localhost:8080/avaliacao_marph/req/endereco/search?page_first=0&page_size=10&sort_field=pessoa.nome&sort_order=DESCENDING&rua=Rua&bairro=Bairro&telefone=6
select * from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa where (lower(e.telefone) like '%6%') and (lower(e.rua) like '%Rua%') and (lower(e.bairro) like '%Bairro%') order by p.nome desc limit 0,10;

#http://localhost:8080/avaliacao_marph/req/endereco/search?page_first=0&page_size=15&sort_field=pessoa.nascimento&sort_order=DESCENDING&filter_field=pessoa.nome&filter_field=rua&globalFilter=a&bairro=Bairro&telefone=0&pessoa.cpf=6
select * from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa where (lower(p.cpf) like '%6%') and (lower(e.bairro) like '%Bairro%') and (lower(e.telefone) like '%0%') and (lower(p.nome) like '%a%' or lower(e.rua) like '%a%') order by p.nascimento desc limit 0,15;

#http://localhost:8080/avaliacao_marph/req/endereco/count
select count(e.id_endereco) from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa;

#http://localhost:8080/avaliacao_marph/req/endereco/count?filter_field=pessoa.nome&filter_field=bairro&globalFilter=a&telefone=0&pessoa.cpf=6
select count(e.id_endereco) as col_0_0_ from endereco e left outer join pessoa p on e.id_pessoa=p.id_pessoa where (lower(p.nome) like '%a%' or lower(e.bairro) like '%a%') and (lower(p.cpf) like '%6%') and (lower(e.telefone) like '%0%');