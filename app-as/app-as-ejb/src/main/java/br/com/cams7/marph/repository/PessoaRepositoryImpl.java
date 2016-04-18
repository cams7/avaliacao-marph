/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.cams7.as.repository.AbstractRepository;
import br.com.cams7.marph.entity.PessoaEntity;

/**
 * @author cesar
 *
 */
@Stateless
@Local(PessoaRepository.class)
public class PessoaRepositoryImpl extends AbstractRepository<PessoaEntity> implements PessoaRepository {

	public PessoaRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.repository.PessoaRepository#
	 * buscaPessoasSemUsuarioPeloNome(java.lang.String)
	 */
	@Override
	public List<PessoaEntity> buscaPessoasSemUsuarioPeloNome(String nome) {
		Query query = getEntityManager().createNamedQuery("Pessoa.buscaPessoasSemUsuarioPeloNome");
		query.setParameter("nome", "%" + nome.toLowerCase() + "%");
		query.setMaxResults(5);
		@SuppressWarnings("unchecked")
		List<PessoaEntity> pessoas = query.getResultList();
		return pessoas;
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
		Query query = getEntityManager().createNamedQuery("Pessoa.buscaPeloNome");
		query.setParameter("nome", "%" + nome.toLowerCase() + "%");
		query.setMaxResults(5);
		@SuppressWarnings("unchecked")
		List<PessoaEntity> pessoas = query.getResultList();
		return pessoas;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.repository.PessoaRepository#
	 * cpfFoiCadastradoAnteriormente(java.lang.String)
	 */
	@Override
	public boolean cpfFoiCadastradoAnteriormente(String cpf) {
		Query query = getEntityManager().createNamedQuery("Pessoa.buscaQtdCadastradaPeloCPF");
		query.setParameter("cpf", cpf);

		Long count = (Long) query.getSingleResult();
		if (count.equals(0l))
			return false;

		return true;
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
		Query query = getEntityManager().createNamedQuery("Pessoa.buscaCpfPeloId");
		query.setParameter("id", id);

		return (String) query.getSingleResult();
	}

}
