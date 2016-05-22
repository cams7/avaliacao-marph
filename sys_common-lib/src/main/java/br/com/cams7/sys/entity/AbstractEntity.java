/**
 * 
 */
package br.com.cams7.sys.entity;

import java.io.Serializable;

/**
 * Classe comum a todas entidades
 * 
 * @author cesar
 *
 */
public abstract class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public AbstractEntity() {
		super();
	}

	/**
	 * @param id
	 */
	public AbstractEntity(Long id) {
		this();

		setId(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%s{id:%s}", this.getClass().getSimpleName(), getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object entity) {

		if (null == entity) {
			return false;
		}

		if (this == entity) {
			return true;
		}

		if (!getClass().equals(entity.getClass())) {
			return false;
		}

		AbstractEntity e = (AbstractEntity) entity;

		return null == this.getId() ? false : this.getId().equals(e.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}

	/**
	 * Id da entidade
	 * 
	 * @return Long
	 */
	public abstract Long getId();

	/**
	 * @param id
	 *            da entitade
	 */
	public abstract void setId(Long id);

}
