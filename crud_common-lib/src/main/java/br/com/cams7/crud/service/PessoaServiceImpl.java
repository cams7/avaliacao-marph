/**
 * 
 */
package br.com.cams7.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.repository.PessoaRepository;

/**
 * @author cesar
 *
 */
@Service
public class PessoaServiceImpl extends AbstractService<PessoaRepository, PessoaEntity> implements PessoaService {

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Repository</code>.
	 */
	@Autowired
	private PessoaRepository repository;

	public PessoaServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.PessoaRepository#
	 * buscaPessoasSemUsuarioPeloNome(java.lang.String)
	 */
	// @Transactional(readOnly = true)
	@Override
	public List<PessoaEntity> getPessoasSemUsuarioPeloNome(String nome) {
		return getRepository().getPessoasSemUsuarioPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.PessoaRepository#buscaPeloNome(java.lang.
	 * String)
	 */
	// @Transactional(readOnly = true)
	@Override
	public List<PessoaEntity> getPessoaPeloNome(String nome) {
		return getRepository().getPessoaPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.PessoaRepository#
	 * cpfFoiCadastradoAnteriormente(java.lang.String)
	 */
	// @Transactional(readOnly = true)
	@Override
	public boolean isCpfCadastradoAnteriormente(String cpf) {
		return getRepository().isCpfCadastradoAnteriormente(cpf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.PessoaRepository#buscaCpfPeloId(java.lang.
	 * Long)
	 */
	// @Transactional(readOnly = true)
	@Override
	public String getCpfPeloId(Long id) {
		return getRepository().getCpfPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.service.PessoaService#cpfValido(java.lang.Long,
	 * java.lang.String)
	 */
	// @Transactional(readOnly = true)
	@Override
	public boolean isCpfValido(Long pessoaId, String cpf) {
		if (pessoaId != null) {
			String pessoaCpf = getCpfPeloId(pessoaId);
			if (cpf.equals(pessoaCpf))
				return true;
		}

		boolean cpfCadastrado = isCpfCadastradoAnteriormente(cpf);
		if (cpfCadastrado)
			return false;

		return true;
	}

	@Override
	protected PessoaRepository getRepository() {
		return repository;
	}

//	@Override
//	public void setRepository(PessoaRepository repository) {
//		this.repository = repository;
//	}

}
