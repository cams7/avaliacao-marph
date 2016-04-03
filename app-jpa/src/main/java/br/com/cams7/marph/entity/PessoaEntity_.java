/**
 * 
 */
package br.com.cams7.marph.entity;

import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author cesar
 *
 */
@StaticMetamodel(PessoaEntity.class)
public class PessoaEntity_ {
	public static volatile SingularAttribute<PessoaEntity, Long> id;
	public static volatile SingularAttribute<PessoaEntity, String> nome;
	public static volatile SingularAttribute<PessoaEntity, String> cpf;
	public static volatile SingularAttribute<PessoaEntity, Date> nascimento;
	public static volatile ListAttribute<PessoaEntity, EnderecoEntity> enderecos;
}
