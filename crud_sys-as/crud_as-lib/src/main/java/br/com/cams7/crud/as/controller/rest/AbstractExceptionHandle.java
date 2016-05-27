/**
 * 
 */
package br.com.cams7.crud.as.controller.rest;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author cesar
 *
 */
public abstract class AbstractExceptionHandle<E extends RuntimeException> implements ExceptionMapper<E> {

	protected Map<String, String> getMessage(String exceptionMessage) {
		Map<String, String> message = new HashMap<>();
		message.put("errorMessage", exceptionMessage);
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(E ex) {
		return Response.status(INTERNAL_SERVER_ERROR).entity(getMessage(ex.getMessage())).build();
	}

}
