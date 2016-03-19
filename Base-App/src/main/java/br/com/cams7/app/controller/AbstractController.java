/**
 * 
 */
package br.com.cams7.app.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cams7.app.AbstractBase;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;

/**
 * @author cesar
 *
 */
public abstract class AbstractController<S extends BaseService<E>, E extends AbstractEntity> extends AbstractBase<E> {

	private final byte ENTITY_ARGUMENT_NUMBER = 1;

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instancia do <code>Service/code>.
	 */
	@Autowired
	private S service;

	public AbstractController() {
		super();
	}

	protected S getService() {
		return service;
	}

	@Override
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

}
