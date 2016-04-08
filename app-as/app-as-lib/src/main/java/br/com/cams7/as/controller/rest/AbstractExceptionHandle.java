/**
 * 
 */
package br.com.cams7.as.controller.rest;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author cesar
 *
 */
public abstract class AbstractExceptionHandle<E extends RuntimeException> implements ExceptionMapper<E> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(E exception) {
		Map<String, String> message = new HashMap<>();
		message.put("errorMessage", exception.getMessage());
		return Response.status(BAD_REQUEST).entity(message).build();
	}

}
