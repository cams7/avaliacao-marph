/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.SearchParams;
import br.com.cams7.cw.repository.AbstractRepository;
import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
@Repository
public class UsuarioRepositoryImpl extends AbstractRepository<UsuarioEntity> implements UsuarioRepository {

	public UsuarioRepositoryImpl() {
		super();
	}

	/**
	 * @return
	 */
	private Criteria getSelect() {
		Criteria select = getCurrentSession().createCriteria(getEntityType());
		select.createAlias("pessoa", "pessoa", JoinType.LEFT_OUTER_JOIN);
		return select;
	}

	/*
	 * Filtra, pagina e ordena os objetos que são instâncias da classe
	 * "UsuarioEntity"
	 * 
	 * @see
	 * br.com.cams7.cw.repository.AbstractRepository#search(br.com.cams7.app.
	 * SearchParams)
	 */
	@Override
	public List<UsuarioEntity> search(SearchParams params) {
		Criteria select = getSelect();
		setFiltroPaginacaoOrdenacao(select, params);

		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = select.list();

		return usuarios;
	}

	/*
	 * Retorna o numero total de instâncias da classe "UsuarioEntity". Essa
	 * pesquisa é feita com auxilio de filtros
	 * 
	 * @see
	 * br.com.cams7.app.repository.AbstractRepository#getTotalElements(java.util
	 * .Map, java.lang.String[])
	 */
	@Override
	public int getTotalElements(Map<String, Object> filters, String... globalFilters) {
		Criteria select = getSelect();
		setFiltro(select, filters, globalFilters);

		int total = getCount(select);

		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.cw.repository.AbstractRepository#count()
	 */
	@Override
	public int count() {
		Criteria select = getSelect();

		int count = getCount(select);

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
		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = getCurrentSession().getNamedQuery("Usuario.buscaTodosDadosPessoais").list();
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
		Query query = getCurrentSession().getNamedQuery("Usuario.buscaQtdCadastradoPeloLogin");
		query.setParameter("login", login);

		Long count = (Long) query.uniqueResult();
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
		Query query = getCurrentSession().getNamedQuery("Usuario.buscaLoginPeloId");
		query.setParameter("id", id);

		return (String) query.uniqueResult();
	}

}
