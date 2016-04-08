/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.as.service.AbstractService;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.repository.PessoaRepository;

/**
 * @author cesar
 *
 */
@Stateless
@Local(PessoaService.class)
public class PessoaServiceImpl extends AbstractService<PessoaRepository, PessoaEntity> implements PessoaService {

	public PessoaServiceImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.as.service.AbstractService#setRepository(br.com.cams7.app.
	 * repository.BaseRepository)
	 */
	@EJB
	@Override
	protected void setRepository(PessoaRepository repository) {
		super.setRepository(repository);
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

}
