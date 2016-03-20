/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
//import java.util.Set;

import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.marph.entity.UsuarioEntity;
//import br.com.cams7.marph.entity.UsuarioEntity.Autorizacao;

/**
 * @author cesar
 *
 */
public interface UsuarioRepository extends BaseRepository<UsuarioEntity> {
	List<UsuarioEntity> buscaTodosDadosPessoais();

	// Set<Autorizacao> buscaAutorizacoesPorId(Long id);
}
