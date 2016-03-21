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
import br.com.cams7.utils.SortOrder;

/**
 * Classe comum as classes Repositories
 * 
 * @author cesar
 *
 */
public abstract class AbstractRepository<E extends AbstractEntity> extends AbstractBase<E>
		implements BaseRepository<E> {

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>SessionFactory</code>.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	public AbstractRepository() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#salva(br.com.cams7.app.entity.
	 * AbstractEntity)
	 */
	@Override
	public void salva(E entity) {
		getCurrentSession().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#atualiza(br.com.cams7.app.
	 * entity.AbstractEntity)
	 */
	@Override
	public void atualiza(E entity) {
		getCurrentSession().update(entity);
	}

	/**
	 * Remove a entidade do banco de dados
	 * 
	 * @param entity
	 *            Entidade
	 */
	private void remove(E entity) {
		getCurrentSession().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		E entity = buscaPorId(id);
		if (entity != null)
			remove(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.util.List)
	 */
	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids)
			remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#buscaTodos()
	 */
	@Override
	public List<E> buscaTodos() {
		@SuppressWarnings("unchecked")
		List<E> entities = getCurrentSession().createCriteria(getEntityType()).list();
		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#buscaPorId(java.lang.Long)
	 */
	@Override
	public E buscaPorId(Long id) {
		@SuppressWarnings("unchecked")
		E entity = (E) getCurrentSession().get(getEntityType(), id);
		return entity;
	}

	/**
	 * Busca, pagina e ordena os dados das entidades
	 * 
	 * @param pageFirst
	 *            Indice
	 * @param pageSize
	 *            Total de linhas
	 * @param sortField
	 *            Nome do campo
	 * @param sortOrder
	 *            Tipo de ordenacao
	 * @return Criteria
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(int, short,
	 * java.lang.String, br.com.cams7.utils.SortOrder, java.util.Map)
	 */
	@Override
	public List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		Criteria select = getPaginacaoOrdenacao(pageFirst, pageSize, sortField, sortOrder);

		@SuppressWarnings("unchecked")
		List<E> entities = select.list();

		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#count()
	 */
	@Override
	public int count() {
		Criteria select = getCurrentSession().createCriteria(getEntityType());
		select.setProjection(Projections.rowCount());
		Long count = (Long) select.uniqueResult();
		return count.intValue();
	}

	/**
	 * @return Sessao do Hibernate
	 */
	protected SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	protected Session getCurrentSession() {
		return getSessionFactory().getCurrentSession();
	}
}
