/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cams7.marph.entity.EnderecoEntity;

/**
 * @author cesar
 *
 */
@Repository
public class EnderecoRepositoryImpl implements EnderecoRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public EnderecoRepositoryImpl() {
		super();
	}

	@Override
	public void salva(EnderecoEntity endereco) {
		getCurrentSession().save(endereco);
	}

	@Override
	public void atualiza(EnderecoEntity endereco) {
		getCurrentSession().update(endereco);
	}

	private void remove(EnderecoEntity endereco) {
		getCurrentSession().delete(endereco);
	}

	@Override
	public void remove(Long id) {
		EnderecoEntity endereco = buscaPorId(id);
		if (endereco != null)
			remove(endereco);
	}

	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids)
			remove(id);
	}

	@Override
	public List<EnderecoEntity> buscaTodos() {
		@SuppressWarnings("unchecked")
		List<EnderecoEntity> enderecos = getCurrentSession().createCriteria(EnderecoEntity.class).list();
		return enderecos;
	}

	@Override
	public EnderecoEntity buscaPorId(Long id) {
		EnderecoEntity endereco = (EnderecoEntity) getCurrentSession().get(EnderecoEntity.class, id);
		return endereco;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
