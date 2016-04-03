/**
 * 
 */
package br.com.cams7.cw.repository;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

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

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.app.utils.AppException;
import br.com.cams7.app.utils.AppHelper;
import br.com.cams7.app.utils.SortOrder;

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
	public boolean remove(Long id) {
		E entity = buscaPeloId(id);
		if (entity != null) {
			remove(entity);
			return true;
		}
		return false;
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
	 * Converte o valor para o tipo correto
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	private Object getFieldValue(String fieldName, Object fieldValue) {
		Object value = null;
		try {
			value = AppHelper.getFieldValue(getEntityType(), fieldName, fieldValue);
		} catch (AppException e) {
			getLog().log(Level.WARNING, e.getMessage());
		}
		return value;
	}

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	private Criterion getExpression(String fieldName, Object fieldValue) {
		Object value = getFieldValue(fieldName, fieldValue);

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
		if (filters != null) {
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
	 * @param pageFirst
	 *            Indice
	 * @param pageSize
	 *            Total de linhas
	 * @param sortField
	 *            Nome do atributo da entidade
	 * @param sortOrder
	 *            Tipo de ordenacao
	 * @param filters
	 *            Filtros
	 * @param globalFilters
	 *            Nomes dos atributos da entidade
	 */
	protected void setFiltroPaginacaoOrdenacao(Criteria select, Integer pageFirst, Short pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters, String... globalFilters) {

		setFiltro(select, filters, globalFilters);

		if (pageFirst != null && pageSize != null) {
			select.setFirstResult(pageFirst);
			select.setMaxResults(pageSize);
		}

		if (sortField != null && sortOrder != null) {
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
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(int, short,
	 * java.lang.String, br.com.cams7.utils.SortOrder, java.util.Map,
	 * java.lang.String[])
	 */
	@Override
	public List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		Criteria select = getCurrentSession().createCriteria(getEntityType());
		setFiltroPaginacaoOrdenacao(select, pageFirst, pageSize, sortField, sortOrder, filters, globalFilters);

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
