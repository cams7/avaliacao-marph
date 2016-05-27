/**
 * 
 */
package br.com.cams7.crud.service;

import br.com.cams7.crud.entity.UsuarioEntity;
import br.com.cams7.crud.repository.UsuarioRepository;
import br.com.cams7.sys.service.BaseService;

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
	boolean isLoginValido(Long usuarioId, String login);
}
