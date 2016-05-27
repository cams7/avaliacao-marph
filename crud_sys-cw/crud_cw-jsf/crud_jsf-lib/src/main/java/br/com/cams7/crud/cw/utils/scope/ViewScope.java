/**
 * 
 */
package br.com.cams7.crud.cw.utils.scope;

import java.util.Map;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * @author cesar
 *
 */
public class ViewScope implements Scope {
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.Scope#get(java.lang.String,
	 * org.springframework.beans.factory.ObjectFactory)
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

		if (viewMap.containsKey(name))
			return viewMap.get(name);

		Object object = objectFactory.getObject();
		viewMap.put(name, object);
		return object;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.config.Scope#remove(java.lang.String)
	 */
	@Override
	public Object remove(String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
	}

	@Override
	public String getConversationId() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.config.Scope#
	 * registerDestructionCallback(java.lang.String, java.lang.Runnable)
	 */
	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// Not supported
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.config.Scope#resolveContextualObject(
	 * java.lang.String)
	 */
	@Override
	public Object resolveContextualObject(String key) {
		return null;
	}
}
