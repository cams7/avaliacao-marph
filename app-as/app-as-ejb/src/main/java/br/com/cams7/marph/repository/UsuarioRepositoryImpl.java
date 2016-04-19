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
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.cams7.app.SearchParams;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.as.repository.AbstractRepository#search(java.lang.Integer,
	 * java.lang.Short, java.lang.String, br.com.cams7.app.utils.SortOrder,
	 * java.util.Map, java.lang.String[])
	 */
	@Override
	public List<UsuarioEntity> search(SearchParams params) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<UsuarioEntity> cq = cb.createQuery(getEntityType());

		Root<UsuarioEntity> from = cq.from(getEntityType());
		@SuppressWarnings("unchecked")
		Join<UsuarioEntity, PessoaEntity> join = (Join<UsuarioEntity, PessoaEntity>) from.fetch(UsuarioEntity_.pessoa,
				JoinType.LEFT);

		TypedQuery<UsuarioEntity> tq = getFiltroPaginacaoOrdenacao(cb, cq, join, params);
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
		Join<UsuarioEntity, PessoaEntity> join = from.join(UsuarioEntity_.pessoa, JoinType.LEFT);

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

		Root<UsuarioEntity> from = cq.from(getEntityType());
		Join<UsuarioEntity, PessoaEntity> join = from.join(UsuarioEntity_.pessoa, JoinType.LEFT);

		int count = getCount(cb, cq, join);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.marph.repository.UsuarioRepository#
	 * loginFoiCadastradoAnteriormente(java.lang.String)
	 */
	@Override
	public boolean loginFoiCadastradoAnteriormente(String login) {
		Query query = getEntityManager().createNamedQuery("Usuario.buscaQtdCadastradoPeloLogin");
		query.setParameter("login", login);

		Long count = (Long) query.getSingleResult();
		if (count.equals(0l))
			return false;

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.marph.repository.UsuarioRepository#buscaLoginPeloId(java.
	 * lang.Long)
	 */
	@Override
	public String buscaLoginPeloId(Long id) {
		Query query = getEntityManager().createNamedQuery("Usuario.buscaLoginPeloId");
		query.setParameter("id", id);

		return (String) query.getSingleResult();
	}

}
