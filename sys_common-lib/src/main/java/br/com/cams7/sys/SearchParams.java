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
	private Integer firstPage;
	/**
	 * Total de linhas
	 */
	private Short sizePage;
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

	public SearchParams(Integer firstPage, Short sizePage, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {

		this();

		this.firstPage = firstPage;
		this.sizePage = sizePage;
		this.sortField = sortField;
		this.sortOrder = sortOrder;
		this.filters = filters;
		this.globalFilters = globalFilters;
	}

	@Override
	public String toString() {
		return String.format("%s{firstPage:%s, sizePage:%s, sortField:%s, sortOrder:%s, filters:%s, globalFilters:%s}",
				this.getClass().getSimpleName(), getFirstPage(), getSizePage(), getSortField(), getSortOrder(),
				getFilters(), AppHelper.getString(getGlobalFilters()));
	}

	public Integer getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(Integer firstPage) {
		this.firstPage = firstPage;
	}

	public Short getSizePage() {
		return sizePage;
	}

	public void setSizePage(Short sizePage) {
		this.sizePage = sizePage;
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
