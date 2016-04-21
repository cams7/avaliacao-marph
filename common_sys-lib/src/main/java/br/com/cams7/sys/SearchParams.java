/**
 * 
 */
package br.com.cams7.sys;

import java.util.Map;

import br.com.cams7.sys.utils.AppHelper;

/**
 * @author cesar
 *
 */
public class SearchParams {

	/**
	 * Indice
	 */
	private Integer pageFirst;
	/**
	 * Total de linhas
	 */
	private Short pageSize;
	/**
	 * Nome do atributo da entidade
	 */
	private String sortField;
	/**
	 * Tipo de ordenação
	 */
	private SortOrder sortOrder;
	/**
	 * Filtros
	 */
	private Map<String, Object> filters;
	/**
	 * Nomes dos atributos da entidade
	 */
	private String[] globalFilters;

	public SearchParams() {
		super();
	}

	public SearchParams(Integer pageFirst, Short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {

		this();

		this.pageFirst = pageFirst;
		this.pageSize = pageSize;
		this.sortField = sortField;
		this.sortOrder = sortOrder;
		this.filters = filters;
		this.globalFilters = globalFilters;
	}

	@Override
	public String toString() {
		return String.format("%s{pageFirst:%s, pageSize:%s, sortField:%s, sortOrder:%s, filters:%s, globalFilters:%s}",
				this.getClass().getSimpleName(), getPageFirst(), getPageSize(), getSortField(), getSortOrder(),
				getFilters(), AppHelper.getString(getGlobalFilters()));
	}

	public Integer getPageFirst() {
		return pageFirst;
	}

	public void setPageFirst(Integer pageFirst) {
		this.pageFirst = pageFirst;
	}

	public Short getPageSize() {
		return pageSize;
	}

	public void setPageSize(Short pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	public String[] getGlobalFilters() {
		return globalFilters;
	}

	public void setGlobalFilters(String... globalFilters) {
		this.globalFilters = globalFilters;
	}

	public enum SortOrder {
		ASCENDING, DESCENDING, UNSORTED;
	}

}
