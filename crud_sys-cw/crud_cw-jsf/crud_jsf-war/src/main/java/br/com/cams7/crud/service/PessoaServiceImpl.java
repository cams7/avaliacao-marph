/**
 * 
 */
package br.com.cams7.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.repository.PessoaRepository;
import br.com.cams7.cw.service.AbstractService;

/**
 * @author cesar
 *
 */
@Service
public class PessoaServiceImpl extends AbstractService<PessoaRepository, PessoaEntity> implements PessoaService {

	public PessoaServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.PessoaRepository#
	 * buscaPessoasSemUsuarioPeloNome(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<PessoaEntity> buscaPessoasSemUsuarioPeloNome(String nome) {
		return getRepository().buscaPessoasSemUsuarioPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.PessoaRepository#buscaPeloNome(java.lang.
	 * String)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<PessoaEntity> buscaPeloNome(String nome) {
		return getRepository().buscaPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.PessoaRepository#
	 * cpfFoiCadastradoAnteriormente(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean cpfFoiCadastradoAnteriormente(String cpf) {
		return getRepository().cpfFoiCadastradoAnteriormente(cpf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.PessoaRepository#buscaCpfPeloId(java.lang.
	 * Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public String buscaCpfPeloId(Long id) {
		return getRepository().buscaCpfPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.service.PessoaService#cpfValido(java.lang.Long,
	 * java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public boolean cpfValido(Long pessoaId, String cpf) {
		if (pessoaId != null) {
			String pessoaCpf = buscaCpfPeloId(pessoaId);
			if (cpf.equals(pessoaCpf))
				return true;
		}

		boolean cpfCadastrado = cpfFoiCadastradoAnteriormente(cpf);
		if (cpfCadastrado)
			return false;

		return true;
	}

}
