/**
 * 
 */
package br.com.cams7.app.repository;

import java.util.List;
import java.util.Map;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.entity.SortOrder;

/**
 * @author cesar
 *
 */
public interface BaseRepository<E extends AbstractEntity> {
	void salva(E entity);

	void atualiza(E entity);

	void remove(Long id);

	void remove(List<Long> ids);

	List<E> buscaTodos();

	E buscaPorId(Long id);

	List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters);

	// int getTotalElements(int pageFirst, short pageSize, String sortField,
	// SortOrder sortOrder, Map<String, Object> filters);

	int count();
}
