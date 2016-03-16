/**
 * 
 */
package br.com.cams7.app;

import org.springframework.beans.factory.annotation.Autowired;

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
