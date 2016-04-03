package br.com.cams7.marph.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EnderecoEntity.class)
public class EnderecoEntity_ {
	public static volatile SingularAttribute<EnderecoEntity, Long> id;
	public static volatile SingularAttribute<EnderecoEntity, String> rua;
	public static volatile SingularAttribute<EnderecoEntity, String> bairro;
	public static volatile SingularAttribute<EnderecoEntity, String> telefone;
	public static volatile SingularAttribute<EnderecoEntity, PessoaEntity> pessoa;
}
