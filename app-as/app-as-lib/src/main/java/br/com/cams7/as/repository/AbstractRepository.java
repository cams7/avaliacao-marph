package br.com.cams7.as.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.SortOrder;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.app.utils.AppHelper;

/**
 * Classe comum as classes Repositories
 * 
 * @author cesar
 *
 * @param <E>
 */
public abstract class AbstractRepository<E extends AbstractEntity> extends AbstractBase<E>
		implements BaseRepository<E> {

	@PersistenceContext(unitName = "appUnit")
	private EntityManager entityManager;

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
		getEntityManager().persist(entity);
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
		entity = getEntityManager().merge(entity);
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
			getEntityManager().remove(entity);
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
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> from = cq.from(getEntityType());
		cq.select(from);

		TypedQuery<E> tq = getEntityManager().createQuery(cq);
		List<E> entities = tq.getResultList();
		return entities;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#buscaPeloId(java.lang.Long)
	 */
	@Override
	public E buscaPeloId(Long id) {
		E entity = getEntityManager().find(getEntityType(), id);
		return entity;
	}

	/**
	 * @param from
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Root<E> getRoot(From<?, ?> from) {
		if (from instanceof Root<?>)
			return (Root<E>) from;

		from = ((Join<?, ?>) from).getParent();
		return getRoot(from);
	}

	/**
	 * @param from
	 * @param parentName
	 * @return
	 */
	private From<?, ?> getFrom(From<?, ?> from, Class<E> entityType) {
		if (from.getJavaType().equals(entityType) || from instanceof Root<?>)
			return from;

		from = ((Join<?, ?>) from).getParent();
		return getFrom(from, entityType);
	}

	/**
	 * @param from
	 * @param parentName
	 * @param attributeName
	 * @return
	 */
	private AttributeFrom getFrom(From<?, ?> from, String attributeName) {
		Class<E> entityType = AppHelper.getFieldTypes(getEntityType(), attributeName).getEntityType();

		attributeName = attributeName.substring(attributeName.lastIndexOf(".") + 1);
		from = getFrom(from, entityType);

		return new AttributeFrom(attributeName, from);
	}

	/**
	 * @param cb
	 * @param from
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	private Predicate getExpression(CriteriaBuilder cb, From<?, ?> from, String fieldName, Object fieldValue) {
		Object value = AppHelper.getFieldValue(getEntityType(), fieldName, fieldValue);

		if (value == null)
			return null;

		Class<?> fieldType = value.getClass();

		AttributeFrom attributeFrom = getFrom(from, fieldName);
		fieldName = attributeFrom.attributeName;
		from = attributeFrom.from;

		Predicate predicate;

		if (AppHelper.isBoolean(fieldType))
			if (((Boolean) value).booleanValue())
				predicate = cb.isTrue(from.<Boolean> get(fieldName));
			else
				predicate = cb.isFalse(from.<Boolean> get(fieldName));
		else if (AppHelper.isNumber(fieldType))
			predicate = cb.like(cb.lower(from.<String> get(fieldName)),
					"%" + String.valueOf(value).toLowerCase() + "%");
		else if (AppHelper.isDate(fieldType))
			predicate = cb.equal(from.get(fieldName), value);
		else if (AppHelper.isEnum(fieldType))
			predicate = cb.equal(from.get(fieldName), value);
		else
			predicate = cb.like(cb.lower(from.<String> get(fieldName)),
					"%" + String.valueOf(value).toLowerCase() + "%");

		return predicate;
	}

	/**
	 * Filtra
	 * 
	 * @param cb
	 * @param cq
	 * @param from
	 * @param filters
	 * @param globalFilters
	 * @return
	 */
	protected CriteriaQuery<?> getFiltro(CriteriaBuilder cb, CriteriaQuery<?> cq, From<?, ?> from,
			Map<String, Object> filters, String... globalFilters) {
		if (filters != null && !filters.isEmpty()) {
			boolean containsKeyGlobalFilter = false;

			final String GLOBALFILTER_KEY = "globalFilter";

			Set<Predicate> predicates = new HashSet<>();
			Set<Predicate> and = new HashSet<>();
			for (Entry<String, Object> filter : filters.entrySet()) {
				if (GLOBALFILTER_KEY.equals(filter.getKey())) {
					containsKeyGlobalFilter = true;
					continue;
				}

				Predicate expression = getExpression(cb, from, filter.getKey(), filter.getValue());
				if (expression != null)
					and.add(expression);
			}

			if (!and.isEmpty())
				predicates.add(cb.and(and.toArray(new Predicate[] {})));

			if (containsKeyGlobalFilter && globalFilters.length > 0) {
				Set<Predicate> or = new HashSet<>();
				for (String globalFilter : globalFilters) {
					Predicate expression = getExpression(cb, from, globalFilter, filters.get(GLOBALFILTER_KEY));
					if (expression != null)
						or.add(expression);
				}
				if (!or.isEmpty())
					predicates.add(cb.or(or.toArray(new Predicate[] {})));
			}
			if (!predicates.isEmpty())
				cq = cq.where(predicates.toArray(new Predicate[] {}));
		}
		return cq;
	}

	/**
	 * Filtra, pagina e ordena
	 * 
	 * @param cb
	 *            CriteriaBuilder
	 * @param cq
	 *            CriteriaQuery<E>
	 * @param from
	 *            From<?, ?>
	 * @param pageFirst
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @param filters
	 * @param globalFilters
	 * @return TypedQuery<E>
	 */
	@SuppressWarnings("unchecked")
	protected TypedQuery<E> getFiltroPaginacaoOrdenacao(CriteriaBuilder cb, CriteriaQuery<E> cq, From<?, ?> from,
			Integer pageFirst, Short pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters,
			String... globalFilters) {
		cq = (CriteriaQuery<E>) getFiltro(cb, cq, from, filters, globalFilters);

		if (sortField != null && sortOrder != null) {
			AttributeFrom attributeFrom = getFrom(from, sortField);
			sortField = attributeFrom.attributeName;
			From<?, ?> sortFrom = attributeFrom.from;

			Order order;

			switch (sortOrder) {
			case ASCENDING:
				order = cb.asc(sortFrom.get(sortField));
				break;
			case DESCENDING:
				order = cb.desc(sortFrom.get(sortField));
				break;

			default:
				order = cb.asc(sortFrom.get("id"));
				break;
			}
			cq = cq.orderBy(order);
		}

		Root<E> root = getRoot(from);
		cq.select(root);

		TypedQuery<E> tq = getEntityManager().createQuery(cq);
		if (pageFirst != null)
			tq.setFirstResult(pageFirst);

		if (pageSize != null)
			tq.setMaxResults(pageSize);

		return tq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(int, short,
	 * java.lang.String, br.com.cams7.app.utils.SortOrder, java.util.Map,
	 * java.lang.String[])
	 */
	@Override
	public List<E> search(Integer pageFirst, Short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityType());

		Root<E> from = cq.from(getEntityType());

		TypedQuery<E> tq = getFiltroPaginacaoOrdenacao(cb, cq, from, pageFirst, pageSize, sortField, sortOrder, filters,
				globalFilters);
		List<E> entities = tq.getResultList();
		return entities;
	}

	/**
	 * Retorna o numero total de instâncias de "AbstractEntity". Essa pesquisa é
	 * feita com auxilio de filtros
	 * 
	 * @param select
	 * @return
	 */
	protected int getCount(CriteriaBuilder cb, CriteriaQuery<Long> cq, From<?, ?> from) {
		Root<E> root = getRoot(from);
		Expression<Long> count = cb.count(root);
		cq.select(count);

		Long countValue = getEntityManager().createQuery(cq).getSingleResult();
		return countValue.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#getTotalElements(java.util.
	 * Map, java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getTotalElements(Map<String, Object> filters, String... globalFilters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<E> from = cq.from(getEntityType());
		cq = (CriteriaQuery<Long>) getFiltro(cb, cq, from, filters, globalFilters);
		int count = getCount(cb, cq, from);

		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#count()
	 */
	@Override
	public int count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<E> from = cq.from(getEntityType());
		int count = getCount(cb, cq, from);

		return count;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private class AttributeFrom {
		private String attributeName;
		private From<?, ?> from;

		private AttributeFrom(String attributeName, From<?, ?> from) {
			super();
			this.attributeName = attributeName;
			this.from = from;
		}
	}

}