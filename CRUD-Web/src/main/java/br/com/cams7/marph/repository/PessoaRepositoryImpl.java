/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cams7.marph.entity.PessoaEntity;

/**
 * @author cesar
 *
 */
@Repository
public class PessoaRepositoryImpl implements PessoaRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public PessoaRepositoryImpl() {
		super();
	}

	@Override
	public void salva(PessoaEntity pessoa) {
		getCurrentSession().save(pessoa);
	}

	@Override
	public void atualiza(PessoaEntity pessoa) {
		getCurrentSession().update(pessoa);
	}

	private void remove(PessoaEntity pessoa) {
		getCurrentSession().delete(pessoa);
	}

	@Override
	public void remove(Long id) {
		PessoaEntity pessoa = buscaPorId(id);
		if (pessoa != null)
			remove(pessoa);
	}

	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids)
			remove(id);
	}

	@Override
	public List<PessoaEntity> buscaTodos() {
		@SuppressWarnings("unchecked")
		List<PessoaEntity> pessoas = getCurrentSession().createCriteria(PessoaEntity.class).list();
		return pessoas;
	}

	@Override
	public PessoaEntity buscaPorId(Long id) {
		PessoaEntity pessoa = (PessoaEntity) getCurrentSession().get(PessoaEntity.class, id);
		return pessoa;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
