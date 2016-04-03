/**
 * 
 */
package br.com.cams7.app.controller;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;

/**
 * Classe comum as classes Controllers
 * 
 * @author cesar
 *
 */
public abstract class AbstractController<S extends BaseService<E>, E extends AbstractEntity> extends AbstractBase<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	private S service;

	public AbstractController() {
		super();
	}

	/**
	 * @return the service
	 */
	protected S getService() {
		return service;
	}

	/**
	 * @param service
	 *            the service to set
	 */
	protected void setService(S service) {
		this.service = service;
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
