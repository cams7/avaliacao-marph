/**
 * 
 */
package br.com.cams7.app;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.utils.AppHelper;

/**
 * Classe comum a Repository, Service e Controller
 * 
 * @author cesar
 */
public abstract class AbstractBase<E extends AbstractEntity> {

	private final byte ENTITY_ARGUMENT_NUMBER = 0;

	private Logger log;
	private Class<E> entityType;

	public AbstractBase() {
		super();
	}

	@SuppressWarnings("unchecked")
	@PostConstruct
	protected void initialize() {
		log = Logger.getLogger(this.getClass().getName());
		entityType = (Class<E>) AppHelper.getType(this.getClass(), getEntityArgumentNumber());
	}

	/**
	 * Log da aplicacao
	 * 
	 * @return Logger
	 */
	protected Logger getLog() {
		return log;
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
		return entityType;
	}

}
