/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.com.cams7.app.utils.SortOrder;
import br.com.cams7.as.repository.AbstractRepository;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.entity.EnderecoEntity_;
import br.com.cams7.marph.entity.PessoaEntity;

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

	/**
	 * @param root
	 * @return
	 */
	private Join<EnderecoEntity, PessoaEntity> getJoin(Root<EnderecoEntity> root) {
		Join<EnderecoEntity, PessoaEntity> join = root.join(EnderecoEntity_.pessoa);
		return join;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(int, short,
	 * java.lang.String, br.com.cams7.app.utils.SortOrder, java.util.Map,
	 * java.lang.String[])
	 */
	@Override
	public List<EnderecoEntity> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<EnderecoEntity> cq = cb.createQuery(getEntityType());

		Root<EnderecoEntity> from = cq.from(getEntityType());
		Join<EnderecoEntity, PessoaEntity> join = getJoin(from);

		TypedQuery<EnderecoEntity> tq = getFiltroPaginacaoOrdenacao(cb, cq, join, pageFirst, pageSize, sortField,
				sortOrder, filters, globalFilters);
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
		Join<EnderecoEntity, PessoaEntity> join = getJoin(from);

		cq = (CriteriaQuery<Long>) getFiltro(cb, cq, join, filters, globalFilters);
		int count = getCount(cb, cq, from);

		return count;
	}

}
