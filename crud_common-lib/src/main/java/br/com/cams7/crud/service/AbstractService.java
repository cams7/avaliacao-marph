/**
 * 
 */
package br.com.cams7.crud.service;

import java.util.List;
import java.util.Map;

import br.com.cams7.sys.AbstractBase;
import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.repository.BaseRepository;
import br.com.cams7.sys.service.BaseService;

/**
 * Classe comum as classes Services
 * 
 * @author cesar
 *
 */
public abstract class AbstractService<R extends BaseRepository<E>, E extends AbstractEntity> extends AbstractBase<E>
		implements BaseService<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	public AbstractService() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#salva(br.com.cams7.app.entity.
	 * AbstractEntity)
	 */
	// @Transactional
	@Override
	public void save(E entity) {
		getRepository().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#atualiza(br.com.cams7.app.
	 * entity.AbstractEntity)
	 */
	// @Transactional
	@Override
	public void update(E entity) {
		getRepository().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.lang.Long)
	 */
	// @Transactional
	@Override
	public boolean remove(Long id) {
		return getRepository().remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.util.List)
	 */
	// @Transactional
	@Override
	public int remove(List<Long> ids) {
		return getRepository().remove(ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#buscaTodos()
	 */
	// @Transactional(readOnly = true)
	@Override
	public List<E> getAll() {
		return getRepository().getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#buscaPorId(java.lang.Long)
	 */
	// @Transactional(readOnly = true)
	@Override
	public E getEntityById(Long id) {
		return getRepository().getEntityById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(br.com.cams7.app.
	 * SearchParams)
	 */
	// @Transactional(readOnly = true)
	@Override
	public List<E> search(SearchParams params) {
		return getRepository().search(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#getTotalElements(java.util.
	 * Map, java.lang.String[])
	 */
	// @Transactional(readOnly = true)
	@Override
	public int getTotalElements(Map<String, Object> filters, String... globalFilters) {
		return getRepository().getTotalElements(filters, globalFilters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#count()
	 */
	// @Transactional(readOnly = true)
	@Override
	public int count() {
		return getRepository().count();
	}

	/**
	 * @return Repository
	 */
	protected abstract R getRepository();

//	public abstract void setRepository(R repository);

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.AbstractBase#getEntityArgumentNumber()
	 */
	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

}
