/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.as.service.AbstractService;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.repository.UsuarioRepository;

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
	 * br.com.cams7.marph.repository.UsuarioRepository#buscaTodosDadosPessoais()
	 */
	@Override
	public List<UsuarioEntity> buscaTodosDadosPessoais() {
		return getRepository().buscaTodosDadosPessoais();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.repository.UsuarioRepository#
	 * loginFoiCadastradoAnteriormente(java.lang.String)
	 */
	@Override
	public boolean loginFoiCadastradoAnteriormente(String login) {
		return getRepository().loginFoiCadastradoAnteriormente(login);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.repository.UsuarioRepository#buscaLoginPeloId(java.
	 * lang.Long)
	 */
	@Override
	public String buscaLoginPeloId(Long id) {
		return getRepository().buscaLoginPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.service.UsuarioService#loginValido(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public boolean loginValido(Long usuarioId, String login) {
		if (usuarioId != null) {
			String usuarioLogin = buscaLoginPeloId(usuarioId);
			if (login.equals(usuarioLogin))
				return true;
		}

		boolean loginCadastrado = loginFoiCadastradoAnteriormente(login);
		if (loginCadastrado)
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.as.service.AbstractService#getRepository()
	 */
	@Override
	protected UsuarioRepository getRepository() {
		return repository;
	}

}
