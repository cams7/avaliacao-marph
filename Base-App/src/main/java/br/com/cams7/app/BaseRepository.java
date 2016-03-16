/**
 * 
 */
package br.com.cams7.app;

import java.util.List;

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
}
