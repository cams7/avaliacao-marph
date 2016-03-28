/**
 * 
 */
package br.com.cams7.app.repository;

import java.util.List;
import java.util.Map;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.utils.SortOrder;

/**
 * Interface comum as classes Repository
 * 
 * @author cesar
 *
 */
public interface BaseRepository<E extends AbstractEntity> {

	/**
	 * Salva dos dados de uma nova entidade no banco de dados
	 * 
	 * @param entity
	 *            Entidade
	 */
	void salva(E entity);

	/**
	 * Atualiza os dados da entidade no banco de dados
	 * 
	 * @param entity
	 *            Entidade
	 */
	void atualiza(E entity);

	/**
	 * Remove a entidade do banco de dados
	 * 
	 * @param id
	 *            Id da entidade
	 */
	void remove(Long id);

	/**
	 * Remove as entidades do banco de dados
	 * 
	 * @param ids
	 *            Ids das entidades
	 */
	void remove(List<Long> ids);

	/**
	 * Busca todas as entidades no banco de dados
	 * 
	 * @return Entidades
	 */
	List<E> buscaTodos();

	/**
	 * Busca a entidade pelo id no banco de dados
	 * 
	 * @param id
	 *            Id da entidade
	 * @return Entidade
	 */
	E buscaPeloId(Long id);

	/**
	 * Busca, pagina e ordena os dados das entidades
	 * 
	 * @param pageFirst
	 *            Indice
	 * @param pageSize
	 *            Total de linhas
	 * @param sortField
	 *            Nome do campo
	 * @param sortOrder
	 *            Tipo de ordenacao
	 * @param filters
	 *            Filtros
	 * @return Entidades
	 */
	List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

	/**
	 * Numero total de entidades
	 * 
	 * @return
	 */
	int count();
}
