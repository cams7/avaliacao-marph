/**
 * 
 */
package br.com.cams7.sys.controller;

import br.com.cams7.sys.AbstractBase;
import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.service.BaseService;

/**
 * Classe comum as classes Controllers
 * 
 * @author cesar
 *
 */
public abstract class AbstractController<S extends BaseService<E>, E extends AbstractEntity> extends AbstractBase<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	public AbstractController() {
		super();
	}

	/**
	 * @return the service
	 */
	protected abstract S getService();

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
