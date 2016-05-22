/**
 * 
 */
package br.com.cams7.crud.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.cw.repository.AbstractRepository;
import br.com.cams7.sys.SearchParams;

/**
 * @author cesar
 *
 */
@Repository
public class EnderecoRepositoryImpl extends AbstractRepository<EnderecoEntity> implements EnderecoRepository {

	public EnderecoRepositoryImpl() {
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
	 * "EnderecoEntity"
	 * 
	 * @see
	 * br.com.cams7.cw.repository.AbstractRepository#search(br.com.cams7.app.
	 * SearchParams)
	 */
	@Override
	public List<EnderecoEntity> search(SearchParams params) {
		Criteria select = getSelect();
		setFiltroPaginacaoOrdenacao(select, params);

		@SuppressWarnings("unchecked")
		List<EnderecoEntity> enderecos = select.list();

		return enderecos;
	}

	/*
	 * Retorna o numero total de instâncias da classe "EnderecoEntity". Essa
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

}