/**
 * 
 */
package br.com.cams7.app;

import java.util.logging.Logger;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.utils.AppHelper;

/**
 * Classe comum a Repository, Service e Controller
 * 
 * @author cesar
 */
public abstract class AbstractBase<E extends AbstractEntity> {

	private final byte ENTITY_ARGUMENT_NUMBER = 0;

	private final Logger LOG;
	private final Class<E> ENTITY_TYPE;

	@SuppressWarnings("unchecked")
	public AbstractBase() {
		super();

		LOG = Logger.getLogger(this.getClass().getName());
		ENTITY_TYPE = (Class<E>) AppHelper.getType(this.getClass(), getEntityArgumentNumber());
	}

	/**
	 * Log da aplicacao
	 * 
	 * @return Logger
	 */
	protected Logger getLog() {
		return LOG;
	}

	/**
	 * Indice do Template
	 * 
	 * @return byte
	 */
	protected byte getEntityArgumentNumber() {
		return ENTITY_ARGUMENT_NUMBER;
	}

	/**
	 * Tipo da entidade usada como Template
	 * 
	 * @return Tipo Entity
	 */
	protected Class<E> getEntityType() {
		return ENTITY_TYPE;
	}

}
