/**
 * 
 */
package br.com.cams7.crud.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.as.service.AbstractService;
import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.repository.PessoaRepository;

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
	 * @see
	 * br.com.cams7.as.service.AbstractService#salva(br.com.cams7.app.entity.
	 * AbstractEntity)
	 */
	@Override
	public void salva(PessoaEntity entity) {
		super.salva(entity);
	}

	@Override
	public void atualiza(PessoaEntity entity) {
		super.atualiza(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.repository.PessoaRepository#
	 * buscaPessoasSemUsuarioPeloNome(java.lang.String)
	 */
	@Override
	public List<PessoaEntity> buscaPessoasSemUsuarioPeloNome(String nome) {
		return getRepository().buscaPessoasSemUsuarioPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.repository.PessoaRepository#buscaPeloNome(java.lang.
	 * String)
	 */
	@Override
	public List<PessoaEntity> buscaPeloNome(String nome) {
		return getRepository().buscaPeloNome(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.repository.PessoaRepository#
	 * cpfFoiCadastradoAnteriormente(java.lang.String)
	 */
	@Override
	public boolean cpfFoiCadastradoAnteriormente(String cpf) {
		return getRepository().cpfFoiCadastradoAnteriormente(cpf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.repository.PessoaRepository#buscaCpfPeloId(java.lang.
	 * Long)
	 */
	@Override
	public String buscaCpfPeloId(Long id) {
		return getRepository().buscaCpfPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.service.PessoaService#cpfValido(java.lang.Long,
	 * java.lang.String)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.as.service.AbstractService#getRepository()
	 */
	@Override
	protected PessoaRepository getRepository() {
		return repository;
	}

}
