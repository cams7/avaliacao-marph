/**
 * 
 */
package br.com.cams7.crud.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.repository.EnderecoRepository;

/**
 * @author cesar
 *
 */
@Service
public class EnderecoServiceImpl extends AbstractService<EnderecoRepository, EnderecoEntity>
		implements EnderecoService {

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Repository</code>.
	 */
	@Autowired
	private EnderecoRepository repository;

	public EnderecoServiceImpl() {
		super();
	}

	@Override
	protected EnderecoRepository getRepository() {
		return repository;
	}

//	@Override
//	public void setRepository(EnderecoRepository repository) {
//		this.repository = repository;
//	}

}
