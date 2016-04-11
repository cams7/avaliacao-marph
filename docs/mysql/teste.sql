select count(*)from pessoa;
/*22*/
select p.* from pessoa p where (lower(p.cpf) like '%6%' and lower(p.nome) like '%l%') and (lower(p.nome) like '%m%' or lower(p.cpf) like '%m%') limit 3, 3;
/*
'13', '61384962646', 'Ana Maria Alves'
'15', '43988387673', 'Wilson Gomes Farias'
'19', '32868121020', 'Leandro Luzia de Magalhães'

*/
select distinct p.id_pessoa from pessoa p where (lower(p.cpf) like '%6%' and lower(p.nome) like '%l%') and (lower(p.nome) like '%m%' or lower(p.cpf) like '%m%') limit 3, 3;
/*13,15,19*/
select count(*) from pessoa p where (lower(p.cpf) like '%6%' and lower(p.nome) like '%l%') and (lower(p.nome) like '%m%' or lower(p.cpf) like '%m%') and p.id_pessoa in (13,15,19);
/*3*/
SELECT count(*) FROM (select distinct p1.id_pessoa from pessoa p1 where (lower(p1.cpf) like '%6%' and lower(p1.nome) like '%l%') and (lower(p1.nome) like '%m%' or lower(p1.cpf) like '%m%') limit 3, 3) as p2;
/*3*/


select * from pessoa;
select * from usuario;

select p.* from pessoa p where (lower(p.cpf) like '%6%') and (lower(p.nome) like '%a%') and (lower(p.nome) like '%m%' or lower(p.cpf) like '%m%') order by p.nascimento desc limit 0, 15;
