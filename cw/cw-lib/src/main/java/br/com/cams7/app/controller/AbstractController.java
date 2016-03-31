/**
 * 
 */
package br.com.cams7.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

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

	/**
	 * Utiliza a injecao de dependencia do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Service</code>.
	 */
	@Autowired
	private S service;

	public AbstractController() {
		super();
	}

	/**
	 * @return Service
	 */
	protected S getService() {
		return service;
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
