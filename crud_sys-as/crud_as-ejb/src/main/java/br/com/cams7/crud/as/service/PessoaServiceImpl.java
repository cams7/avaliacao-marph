/**
 * 
 */
package br.com.cams7.crud.as.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.repository.PessoaRepository;
import br.com.cams7.crud.service.AbstractService;
import br.com.cams7.crud.service.PessoaService;

/**
 * @author cesar
 *
 */
@Stateless
@Local(PessoaService.class)
public class PessoaServiceImpl extends AbstractService<PessoaRepository, PessoaEntity> implements PessoaService {

	@EJB
	private PessoaRepository repository;

	public PessoaServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.PessoaRepository#
	 * getPessoasSemUsuarioPeloNome(java.lang.String)
	 */
	@Override
	public List<PessoaEntity> getPessoasSemUsuarioPeloNome(String nome) {
		return getRepository().getPessoasSemUsuarioPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.PessoaRepository#getPessoaPeloNome(java.lang
	 * .String)
	 */
	@Override
	public List<PessoaEntity> getPessoaPeloNome(String nome) {
		return getRepository().getPessoaPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.repository.PessoaRepository#
	 * isCpfCadastradoAnteriormente(java.lang.String)
	 */
	@Override
	public boolean isCpfCadastradoAnteriormente(String cpf) {
		return getRepository().isCpfCadastradoAnteriormente(cpf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.crud.repository.PessoaRepository#getCpfPeloId(java.lang.
	 * Long)
	 */
	@Override
	public String getCpfPeloId(Long id) {
		return getRepository().getCpfPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.service.PessoaService#isCpfValido(java.lang.Long,
	 * java.lang.String)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.crud.service.AbstractService#getRepository()
	 */
	@Override
	protected PessoaRepository getRepository() {
		return repository;
	}

}
