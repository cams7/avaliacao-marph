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
	 * Busca todos os usuario que estao relacionados a uma pessoa
	 * 
	 * @return Usuarios
	 */
	List<UsuarioEntity> buscaTodosDadosPessoais();
}
