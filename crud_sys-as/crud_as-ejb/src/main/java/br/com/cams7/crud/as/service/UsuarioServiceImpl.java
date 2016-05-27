/**
 * 
 */
package br.com.cams7.crud.as.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.crud.service.AbstractService;
import br.com.cams7.crud.service.UsuarioService;
import br.com.cams7.crud.entity.UsuarioEntity;
import br.com.cams7.crud.repository.UsuarioRepository;

/**
 * @author cesar
 *
 */
@Stateless
@Local(UsuarioService.class)
public class UsuarioServiceImpl extends AbstractService<UsuarioRepository, UsuarioEntity> implements UsuarioService {

	@EJB
	private UsuarioRepository repository;

	public UsuarioServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.UsuarioRepository#getTodosDadosPessoais()
	 */
	@Override
	public List<UsuarioEntity> getTodosDadosPessoais() {
		return getRepository().getTodosDadosPessoais();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.UsuarioRepository#
	 * isLoginCadastradoAnteriormente(java.lang.String)
	 */
	@Override
	public boolean isLoginCadastradoAnteriormente(String login) {
		return getRepository().isLoginCadastradoAnteriormente(login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.UsuarioRepository#getLoginPeloId(java.lang.
	 * Long)
	 */
	@Override
	public String getLoginPeloId(Long id) {
		return getRepository().getLoginPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.service.UsuarioService#isLoginValido(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public boolean isLoginValido(Long usuarioId, String login) {
		if (usuarioId != null) {
			String usuarioLogin = getLoginPeloId(usuarioId);
			if (login.equals(usuarioLogin))
				return true;
		}

		boolean loginCadastrado = isLoginCadastradoAnteriormente(login);
		if (loginCadastrado)
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.service.AbstractService#getRepository()
	 */
	@Override
	protected UsuarioRepository getRepository() {
		return repository;
	}

}
