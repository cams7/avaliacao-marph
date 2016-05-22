/**
 * 
 */
package br.com.cams7.as.controller.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

/**
 * @author cesar
 *
 */
@Provider
@Produces(APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

	private ObjectMapper objectMapper = new ObjectMapper() {
		private static final long serialVersionUID = 1L;
		{
			registerModule(new Hibernate5Module());
		}
	};

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}

}
