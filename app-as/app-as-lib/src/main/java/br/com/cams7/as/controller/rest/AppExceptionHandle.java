/**
 * 
 */
package br.com.cams7.as.controller.rest;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import br.com.cams7.app.utils.AppException;

/**
 * @author cesar
 *
 */
@Provider
public class AppExceptionHandle extends AbstractExceptionHandle<AppException> {

	@Override
	public Response toResponse(AppException ex) {
		ResponseBuilder builder;

		Map<String, String> messages = ex.getMessages();
		if (messages != null)
			builder = Response.status(BAD_REQUEST).entity(messages);
		else
			builder = Response.status(BAD_REQUEST).entity(getMessage(ex.getMessage()));

		return builder.build();
	}
}
