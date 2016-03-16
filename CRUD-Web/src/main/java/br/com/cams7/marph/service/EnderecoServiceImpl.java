/**
 * 
 */
package br.com.cams7.marph.service;

import org.springframework.stereotype.Service;

import br.com.cams7.app.AbstractService;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.repository.EnderecoRepository;

/**
 * @author cesar
 *
 */
@Service
public class EnderecoServiceImpl extends AbstractService<EnderecoRepository, EnderecoEntity>
		implements EnderecoService {

	public EnderecoServiceImpl() {
		super();
	}

}
