/**
 * 
 */
package br.com.cams7.crud.repository;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.entity.EnderecoEntity_;
import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.repository.AbstractRepository;

/**
 * @author cesar
 *
 */
@Stateless
@Local(EnderecoRepository.class)
public class EnderecoRepositoryImpl extends AbstractRepository<EnderecoEntity> implements EnderecoRepository {

	public EnderecoRepositoryImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.as.repository.AbstractRepository#search(java.lang.Integer,
	 * java.lang.Short, java.lang.String, br.com.cams7.app.utils.SortOrder,
	 * java.util.Map, java.lang.String[])
	 */
	@Override
	public List<EnderecoEntity> search(SearchParams params) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EnderecoEntity> cq = cb.createQuery(getEntityType());

		Root<EnderecoEntity> from = cq.from(getEntityType());
		@SuppressWarnings("unchecked")
		Join<EnderecoEntity, PessoaEntity> join = (Join<EnderecoEntity, PessoaEntity>) from
				.fetch(EnderecoEntity_.pessoa, JoinType.LEFT);

		TypedQuery<EnderecoEntity> tq = getFiltroPaginacaoOrdenacao(cb, cq, join, params);
		List<EnderecoEntity> entities = tq.getResultList();
		return entities;
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

		Root<EnderecoEntity> from = cq.from(getEntityType());
		Join<EnderecoEntity, PessoaEntity> join = from.join(EnderecoEntity_.pessoa, JoinType.LEFT);

		cq = (CriteriaQuery<Long>) getFiltro(cb, cq, join, filters, globalFilters);
		int count = getCount(cb, cq, join);

		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.as.repository.AbstractRepository#count()
	 */
	@Override
	public int count() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);

		Root<EnderecoEntity> from = cq.from(getEntityType());
		Join<EnderecoEntity, PessoaEntity> join = from.join(EnderecoEntity_.pessoa, JoinType.LEFT);

		int count = getCount(cb, cq, join);

		return count;
	}

}
