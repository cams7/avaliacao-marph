/**
 * 
 */
package br.com.cams7.marph.service;

import br.com.cams7.app.service.BaseService;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.repository.UsuarioRepository;

/**
 * @author cesar
 *
 */
public interface UsuarioService extends BaseService<UsuarioEntity>, UsuarioRepository {
	
	/**
	 * Vefifica se o login informado é valido
	 * 
	 * @param usuarioId
	 *            Id do usuario
	 * @param login
	 *            Login informado
	 * @return
	 */
	boolean loginValido(Long usuarioId, String login);
}
