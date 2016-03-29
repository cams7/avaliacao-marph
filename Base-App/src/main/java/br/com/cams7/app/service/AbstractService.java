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
import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.utils.SortOrder;

/**
 * Classe comum as classes Services
 * 
 * @author cesar
 *
 */
public abstract class AbstractService<R extends BaseRepository<E>, E extends AbstractEntity> extends AbstractBase<E>
		implements BaseService<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Repository</code>.
	 */
	@Autowired
	private R repository;

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
	@Transactional
	@Override
	public void salva(E entity) {
		getRepository().salva(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#atualiza(br.com.cams7.app.
	 * entity.AbstractEntity)
	 */
	@Transactional
	@Override
	public void atualiza(E entity) {
		getRepository().atualiza(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.lang.Long)
	 */
	@Transactional
	@Override
	public void remove(Long id) {
		getRepository().remove(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#remove(java.util.List)
	 */
	@Transactional
	@Override
	public void remove(List<Long> ids) {
		getRepository().remove(ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#buscaTodos()
	 */
	@Transactional(readOnly = true)
	@Override
	public List<E> buscaTodos() {
		return getRepository().buscaTodos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#buscaPorId(java.lang.Long)
	 */
	@Transactional(readOnly = true)
	@Override
	public E buscaPeloId(Long id) {
		return getRepository().buscaPeloId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#search(int, short,
	 * java.lang.String, br.com.cams7.utils.SortOrder, java.util.Map)
	 */
	@Transactional(readOnly = true)
	@Override
	public List<E> search(int pageFirst, short pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, String... globalFilters) {
		return getRepository().search(pageFirst, pageSize, sortField, sortOrder, filters, globalFilters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.cams7.app.repository.BaseRepository#getTotalElements(java.util.
	 * Map, java.lang.String[])
	 */
	@Transactional(readOnly = true)
	@Override
	public int getTotalElements(Map<String, Object> filters, String... globalFilters) {
		return getRepository().getTotalElements(filters, globalFilters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.repository.BaseRepository#count()
	 */
	@Transactional(readOnly = true)
	@Override
	public int count() {
		return getRepository().count();
	}

	/**
	 * @return Repository
	 */
	protected R getRepository() {
		return repository;
	}

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
