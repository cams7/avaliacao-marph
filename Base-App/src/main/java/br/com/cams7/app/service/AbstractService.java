/**
 * 
 */
package br.com.cams7.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.entity.SortOrder;
import br.com.cams7.app.repository.BaseRepository;

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
		getRepository().salva(entity);
	}

	@Transactional
	@Override
	public void atualiza(E entity) {
		getRepository().atualiza(entity);
	}

	@Transactional
	@Override
	public void remove(Long id) {
		getRepository().remove(id);
	}

	@Transactional
	@Override
	public void remove(List<Long> ids) {
		getRepository().remove(ids);
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> buscaTodos() {
		return getRepository().buscaTodos();
	}

	@Transactional(readOnly = true)
	@Override
	public E buscaPorId(Long id) {
		return getRepository().buscaPorId(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		return getRepository().search(pageFirst, pageSize, sortField, sortOrder, filters);
	}

	@Transactional(readOnly = true)
	@Override
	public int count() {
		return getRepository().count();
	}

	protected R getRepository() {
		return repository;
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

}
