/**
 * 
 */
package br.com.cams7.marph.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.as.service.AbstractService;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.repository.EnderecoRepository;

/**
 * @author cesar
 *
 */
@Stateless
@Local(EnderecoService.class)
public class EnderecoServiceImpl extends AbstractService<EnderecoRepository, EnderecoEntity>
		implements EnderecoService {

	public EnderecoServiceImpl() {
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
	protected void setRepository(EnderecoRepository repository) {
		super.setRepository(repository);
	}

}
