/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.repository.EnderecoRepository;

/**
 * @author cesar
 *
 */
@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public EnderecoServiceImpl() {
		super();
	}

	@Transactional
	@Override
	public void salva(EnderecoEntity endereco) {
		repository.salva(endereco);
	}

	@Transactional
	@Override
	public void atualiza(EnderecoEntity endereco) {
		repository.atualiza(endereco);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		repository.remove(id);
	}

	@Transactional
	@Override
	public void remove(List<Long> ids) {
		repository.remove(ids);
	}

	@Transactional(readOnly = true)
	@Override
	public List<EnderecoEntity> buscaTodos() {
		return repository.buscaTodos();
	}

	@Transactional(readOnly = true)
	@Override
	public EnderecoEntity buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

}
