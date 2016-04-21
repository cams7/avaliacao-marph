/**
 * 
 */
package br.com.cams7.marph.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author cesar
 *
 */
@StaticMetamodel(UsuarioEntity.class)
public class UsuarioEntity_ {
	public static volatile SingularAttribute<UsuarioEntity, Long> id;
	public static volatile SingularAttribute<UsuarioEntity, String> login;
	public static volatile SingularAttribute<UsuarioEntity, String> senha;
	public static volatile SingularAttribute<UsuarioEntity, Boolean> habilitado;
	public static volatile SingularAttribute<UsuarioEntity, String> stringAutorizacoes;
	public static volatile SingularAttribute<UsuarioEntity, PessoaEntity> pessoa;
}
