/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
public interface UsuarioService {
	void salva(UsuarioEntity usuario);

	void atualiza(UsuarioEntity usuario);

	void remove(Long id);

	void remove(List<Long> ids);

	List<UsuarioEntity> buscaTodos();

	UsuarioEntity buscaPorId(Long id);
}
