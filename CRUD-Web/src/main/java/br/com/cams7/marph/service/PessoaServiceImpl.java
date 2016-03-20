/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.service.AbstractService;
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

	@Transactional(readOnly = true)
	@Override
	public List<PessoaEntity> buscaPorNome(String nome) {
		return getRepository().buscaPorNome(nome);
	}

}
