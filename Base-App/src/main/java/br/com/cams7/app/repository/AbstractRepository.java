/**
 * 
 */
package br.com.cams7.app.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.entity.SortOrder;

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

	protected Criteria getPaginacaoOrdenacao(int pageFirst, short pageSize, String sortField, SortOrder sortOrder) {
		Criteria select = getCurrentSession().createCriteria(getEntityType());

		select.setFirstResult(pageFirst);
		select.setMaxResults(pageSize);

		Order order;

		switch (sortOrder) {
		case ASCENDING:
			order = Order.asc(sortField);
			break;
		case DESCENDING:
			order = Order.desc(sortField);
			break;

		default:
			order = Order.asc("id");
			break;
		}

		select.addOrder(order);
		return select;
	}

	@Override
	public List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		Criteria select = getPaginacaoOrdenacao(pageFirst, pageSize, sortField, sortOrder);

		@SuppressWarnings("unchecked")
		List<E> entities = select.list();

		return entities;
	}

	@Override
	public int count() {
		Criteria select = getCurrentSession().createCriteria(getEntityType());
		select.setProjection(Projections.rowCount());
		Long count = (Long) select.uniqueResult();
		return count.intValue();
	}

	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
}
