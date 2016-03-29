/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * DAO de usuario
 * 
 * @author cesar
 *
 */
public interface UsuarioRepository extends BaseRepository<UsuarioEntity> {

	/**
	 * Retorna todos os objetos que são instâncias da classe "UsuarioEntity" e
	 * que estão relacionados a uma pessoa
	 * 
	 * @return Usuarios
	 */
	List<UsuarioEntity> buscaTodosDadosPessoais();
}
