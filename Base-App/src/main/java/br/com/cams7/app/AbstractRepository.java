/**
 * 
 */
package br.com.cams7.app;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cesar
 *
 */
public abstract class AbstractRepository<E extends AbstractEntity> extends AbstractBase<E>
		implements BaseRepository<E> {

	@Autowired
	private SessionFactory sessionFactory;

	public AbstractRepository() {
		super();
	}

	@Override
	public void salva(E entity) {
		getCurrentSession().save(entity);
	}

	@Override
	public void atualiza(E entity) {
		getCurrentSession().update(entity);
	}

	private void remove(E entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void remove(Long id) {
		E entity = buscaPorId(id);
		if (entity != null)
			remove(entity);
	}

	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids)
			remove(id);
	}

	@Override
	public List<E> buscaTodos() {
		@SuppressWarnings("unchecked")
		List<E> entities = getCurrentSession().createCriteria(getEntityType()).list();
		return entities;
	}

	@Override
	public E buscaPorId(Long id) {
		@SuppressWarnings("unchecked")
		E entity = (E) getCurrentSession().get(getEntityType(), id);
		return entity;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
