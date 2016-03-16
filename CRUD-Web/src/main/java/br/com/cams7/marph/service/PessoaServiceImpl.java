/**
 * 
 */
package br.com.cams7.marph.service;

import org.springframework.stereotype.Service;

import br.com.cams7.app.AbstractService;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.repository.PessoaRepository;

/**
 * @author cesar
 *
 */
@Service
public class PessoaServiceImpl extends AbstractService<PessoaRepository, PessoaEntity> implements PessoaService {

	public PessoaServiceImpl() {
		super();
	}

}
