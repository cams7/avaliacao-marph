/**
 * 
 */
package br.com.cams7.app.repository;

import java.util.List;
import java.util.Map;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.utils.SortOrder;

/**
 * Interface comum as classes Repository
 * 
 * @author cesar
 *
 */
public interface BaseRepository<E extends AbstractEntity> {

	/**
	 * Salva um objeto que é instância de "AbstractEntity"
	 * 
	 * @param entity
	 *            Entidade
	 */
	void salva(E entity);

	/**
	 * Atualiza um objeto que é instância de "AbstractEntity"
	 * 
	 * @param entity
	 *            Entidade
	 */
	void atualiza(E entity);

	/**
	 * Remove um objeto que é instância de "AbstractEntity", filtrando-o pelo id
	 * 
	 * @param id
	 *            Id da entidade
	 */
	void remove(Long id);

	/**
	 * Remove os objetos que são instâncias de "AbstractEntity", filtrando-os
	 * pelos ids
	 * 
	 * @param ids
	 *            Ids das entidades
	 */
	void remove(List<Long> ids);

	/**
	 * Retorna todos os objetos que são instâncias de "AbstractEntity"
	 * 
	 * @return Entidades
	 */
	List<E> buscaTodos();

	/**
	 * Retorna um objeto que é instância de "AbstractEntity", filtrando-o pelo
	 * id
	 * 
	 * @param id
	 *            Id da entidade
	 * @return Entidade
	 */
	E buscaPeloId(Long id);

	/**
	 * Filtra, pagina e ordena os objetos que são instâncias de "AbstractEntity"
	 * 
	 * @param pageFirst
	 *            Indice
	 * @param pageSize
	 *            Total de linhas
	 * @param sortField
	 *            Nome do atributo da entidade
	 * @param sortOrder
	 *            Tipo de ordenação
	 * @param filters
	 *            Filtros
	 * @param globalFilters
	 *            Nomes dos atributos da entidade
	 * @return Entidade
	 */
	List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters,
			String... globalFilters);

	/**
	 * Retorna o numero total de instâncias de "AbstractEntity". Essa pesquisa é
	 * feita com auxilio de filtros
	 * 
	 * @param filters
	 * @param globalFilters
	 * @return
	 */
	int getTotalElements(Map<String, Object> filters, String... globalFilters);

	/**
	 * Retorna o numero total de instâncias de "AbstractEntity"
	 * 
	 * @return
	 */
	int count();
}
