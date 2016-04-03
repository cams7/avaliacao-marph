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

	public UsuarioServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.as.service.AbstractService#setRepository(br.com.cams7.app.
	 * repository.BaseRepository)
	 */
	@EJB
	@Override
	protected void setRepository(UsuarioRepository repository) {
		super.setRepository(repository);
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

}
