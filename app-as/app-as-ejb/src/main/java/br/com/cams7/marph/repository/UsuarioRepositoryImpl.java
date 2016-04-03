/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.com.cams7.app.utils.SortOrder;
import br.com.cams7.as.repository.AbstractRepository;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.entity.UsuarioEntity_;

/**
 * @author cesar
 *
 */
@Stateless
@Local(UsuarioRepository.class)
public class UsuarioRepositoryImpl extends AbstractRepository<UsuarioEntity> implements UsuarioRepository {

	public UsuarioRepositoryImpl() {
		super();
	}

	/**
	 * @param root
	 * @return
	 */
	private Join<UsuarioEntity, PessoaEntity> getJoin(Root<UsuarioEntity> root) {
		Join<UsuarioEntity, PessoaEntity> join = root.join(UsuarioEntity_.pessoa);
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
	public List<UsuarioEntity> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UsuarioEntity> cq = cb.createQuery(getEntityType());

		Root<UsuarioEntity> from = cq.from(getEntityType());
		Join<UsuarioEntity, PessoaEntity> join = getJoin(from);

		TypedQuery<UsuarioEntity> tq = getFiltroPaginacaoOrdenacao(cb, cq, join, pageFirst, pageSize, sortField,
				sortOrder, filters, globalFilters);
		List<UsuarioEntity> entities = tq.getResultList();
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

		Root<UsuarioEntity> from = cq.from(getEntityType());
		Join<UsuarioEntity, PessoaEntity> join = getJoin(from);

		cq = (CriteriaQuery<Long>) getFiltro(cb, cq, join, filters, globalFilters);
		int count = getCount(cb, cq, from);

		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.repository.UsuarioRepository#buscaTodosDadosPessoais()
	 */
	@Override
	public List<UsuarioEntity> buscaTodosDadosPessoais() {
		Query query = getEntityManager().createNamedQuery("Usuario.buscaTodosDadosPessoais");
		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = query.getResultList();
		return usuarios;
	}

}
