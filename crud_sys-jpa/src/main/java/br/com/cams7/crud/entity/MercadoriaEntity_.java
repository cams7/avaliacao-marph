package br.com.cams7.crud.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MercadoriaEntity.class)
public class MercadoriaEntity_ {
	public static volatile SingularAttribute<MercadoriaEntity, Long> id;
	public static volatile SingularAttribute<MercadoriaEntity, String> nome;
	public static volatile SingularAttribute<MercadoriaEntity, String> descricao;
	public static volatile SingularAttribute<MercadoriaEntity, Integer> quantidade;
	public static volatile SingularAttribute<MercadoriaEntity, Float> preco;
}
