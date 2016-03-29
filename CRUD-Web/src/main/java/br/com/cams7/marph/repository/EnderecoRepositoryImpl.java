/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import br.com.cams7.app.repository.AbstractRepository;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.utils.SortOrder;

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
	 * @see br.com.cams7.app.repository.AbstractRepository#search(int, short,
	 * java.lang.String, br.com.cams7.utils.SortOrder, java.util.Map,
	 * java.lang.String[])
	 */
	@Override
	public List<EnderecoEntity> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		Criteria select = getSelect();
		setFiltroPaginacaoOrdenacao(select, pageFirst, pageSize, sortField, sortOrder, filters, globalFilters);

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

		int total = getTotalElements(select);

		return total;
	}

}
