/**
 * 
 */
package br.com.cams7.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author cesar
 *
 */
public abstract class AbstractService<R extends BaseRepository<E>, E extends AbstractEntity> extends AbstractBase<E>
		implements BaseService<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	@Autowired
	private R repository;

	public AbstractService() {
		super();
	}

	@Transactional
	@Override
	public void salva(E entity) {
		repository.salva(entity);
	}

	@Transactional
	@Override
	public void atualiza(E entity) {
		repository.atualiza(entity);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		repository.remove(id);
	}

	@Transactional
	@Override
	public void remove(List<Long> ids) {
		repository.remove(ids);
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> buscaTodos() {
		return repository.buscaTodos();
	}

	@Transactional(readOnly = true)
	@Override
	public E buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

}
