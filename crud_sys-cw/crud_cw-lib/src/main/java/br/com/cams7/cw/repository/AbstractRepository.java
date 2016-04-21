/**
 * 
 */
package br.com.cams7.cw.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cams7.sys.AbstractBase;
import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.repository.BaseRepository;
import br.com.cams7.sys.utils.AppHelper;

/**
 * Classe comum as classes Repositories
 * 
 * @author cesar
 *
 */
public abstract class AbstractRepository<E extends AbstractEntity> extends AbstractBase<E>
		implements BaseRepository<E> {

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>SessionFactory</code>.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.lang.Long)
	 */
	@Override
	public boolean remove(Long id) {
		E entity = AppHelper.getNewEntity(getEntityType());
		entity.setId(id);

		getCurrentSession().delete(entity);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.util.List)
	 */
	@Override
	public int remove(List<Long> ids) {
		int count = 0;
		for (Long id : ids)
			if (remove(id))
				count++;

		return count;
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
	public E buscaPeloId(Long id) {
		@SuppressWarnings("unchecked")
		E entity = (E) getCurrentSession().get(getEntityType(), id);
		return entity;
	}

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	private Criterion getExpression(String fieldName, Object fieldValue) {
		Object value = AppHelper.getFieldValue(getEntityType(), fieldName, fieldValue);

		if (value == null)
			return null;

		Class<?> fieldType = value.getClass();

		Criterion expression;

		if (AppHelper.isBoolean(fieldType))
			// Criando uma restrição com eq()
			expression = Restrictions.eq(fieldName, value);
		else if (AppHelper.isNumber(fieldType))
			// Criando uma restrição com ilike()
			expression = Restrictions.ilike(fieldName, String.valueOf(value), MatchMode.ANYWHERE);
		else if (AppHelper.isDate(fieldType))
			// Criando uma restrição com eq()
			expression = Restrictions.eq(fieldName, value);
		else if (AppHelper.isEnum(fieldType))
			// Criando uma restrição com eq()
			expression = Restrictions.eq(fieldName, value);
		else
			// Criando uma restrição com ilike()
			expression = Restrictions.ilike(fieldName, String.valueOf(value), MatchMode.ANYWHERE);

		return expression;

	}

	/**
	 * Filtra
	 * 
	 * @param select
	 *            Criteria
	 * @param filters
	 *            Filtros
	 * @param globalFilters
	 *            Nomes dos atributos da entidade
	 */
	protected void setFiltro(Criteria select, Map<String, Object> filters, String... globalFilters) {
		if (filters != null && !filters.isEmpty()) {
			boolean containsKeyGlobalFilter = false;

			final String GLOBALFILTER_KEY = "globalFilter";

			Conjunction and = Restrictions.conjunction();
			for (Map.Entry<String, Object> filter : filters.entrySet()) {
				if (GLOBALFILTER_KEY.equals(filter.getKey())) {
					containsKeyGlobalFilter = true;
					continue;
				}

				Criterion expression = getExpression(filter.getKey(), filter.getValue());
				if (expression != null)
					and.add(expression);
			}
			select.add(and);

			if (containsKeyGlobalFilter && globalFilters.length > 0) {
				Disjunction or = Restrictions.disjunction();
				for (String globalFilter : globalFilters) {
					Criterion expression = getExpression(globalFilter, filters.get(GLOBALFILTER_KEY));
					if (expression != null)
						or.add(expression);
				}
				select.add(or);
			}
		}
	}

	/**
	 * Filtra, pagina e ordena
	 * 
	 * @param select
	 *            Criteria
	 * @param params
	 *            parâmetros
	 */
	protected void setFiltroPaginacaoOrdenacao(Criteria select, SearchParams params) {

		setFiltro(select, params.getFilters(), params.getGlobalFilters());

		if (params.getPageFirst() != null)
			select.setFirstResult(params.getPageFirst());

		if (params.getPageSize() != null)
			select.setMaxResults(params.getPageSize());

		if (params.getSortField() != null && params.getSortOrder() != null) {
			Order order;

			switch (params.getSortOrder()) {
			case ASCENDING:
				order = Order.asc(params.getSortField());
				break;
			case DESCENDING:
				order = Order.desc(params.getSortField());
				break;

			default:
				order = Order.asc("id");
				break;
			}

			select.addOrder(order);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(br.com.cams7.app.
	 * SearchParams)
	 */
	@Override
	public List<E> search(SearchParams params) {
		Criteria select = getCurrentSession().createCriteria(getEntityType());
		setFiltroPaginacaoOrdenacao(select, params);

		@SuppressWarnings("unchecked")
		List<E> entities = select.list();

		return entities;
	}

	/**
	 * Retorna o numero total de instâncias de "AbstractEntity". Essa pesquisa é
	 * feita com auxilio de filtros
	 * 
	 * @param select
	 * @return
	 */
	protected int getCount(Criteria select) {
		select.setProjection(Projections.rowCount());

		Long count = (Long) select.uniqueResult();
		return count.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#getTotalElements(java.util.
	 * Map, java.lang.String[])
	 */
	@Override
	public int getTotalElements(Map<String, Object> filters, String... globalFilters) {
		Criteria select = getCurrentSession().createCriteria(getEntityType());
		setFiltro(select, filters, globalFilters);

		int count = getCount(select);

		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#count()
	 */
	@Override
	public int count() {
		Criteria select = getCurrentSession().createCriteria(getEntityType());

		int count = getCount(select);

		return count;
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
