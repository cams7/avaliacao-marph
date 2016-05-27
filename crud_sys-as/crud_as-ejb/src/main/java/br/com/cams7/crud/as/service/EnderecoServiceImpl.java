/**
 * 
 */
package br.com.cams7.crud.as.service;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.crud.service.AbstractService;
import br.com.cams7.crud.service.EnderecoService;
import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.repository.EnderecoRepository;

/**
 * @author cesar
 *
 */
@Stateless
@Local(EnderecoService.class)
public class EnderecoServiceImpl extends AbstractService<EnderecoRepository, EnderecoEntity>
		implements EnderecoService {

	@EJB
	private EnderecoRepository repository;

	public EnderecoServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.as.service.AbstractService#getRepository()
	 */
	@Override
	protected EnderecoRepository getRepository() {
		return repository;
	}

}
