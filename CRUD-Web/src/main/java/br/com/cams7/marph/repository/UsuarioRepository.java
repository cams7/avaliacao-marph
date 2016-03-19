/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
public interface UsuarioRepository extends BaseRepository<UsuarioEntity> {
	List<UsuarioEntity> buscaTodosDadosPessoais();
}
