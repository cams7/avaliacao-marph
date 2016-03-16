/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.repository.PessoaRepository;

/**
 * @author cesar
 *
 */
@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository repository;

	public PessoaServiceImpl() {
		super();
	}

	@Transactional
	@Override
	public void salva(PessoaEntity pessoa) {
		repository.salva(pessoa);
	}

	@Transactional
	@Override
	public void atualiza(PessoaEntity pessoa) {
		repository.atualiza(pessoa);
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
	public List<PessoaEntity> buscaTodos() {
		return repository.buscaTodos();
	}

	@Transactional(readOnly = true)
	@Override
	public PessoaEntity buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

}
