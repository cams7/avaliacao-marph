/**
 * 
 */
package br.com.cams7.crud.service;

import org.springframework.stereotype.Service;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.repository.EnderecoRepository;
import br.com.cams7.crud.service.EnderecoService;
import br.com.cams7.cw.service.AbstractService;

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
