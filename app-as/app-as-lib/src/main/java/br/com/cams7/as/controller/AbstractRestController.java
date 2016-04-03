/**
 * 
 */
package br.com.cams7.as.controller;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import br.com.cams7.app.controller.AbstractController;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.app.utils.AppException;
import br.com.cams7.app.utils.AppHelper;

/**
 * @author cesar
 *
 */
public abstract class AbstractRestController<S extends BaseService<E>, E extends AbstractEntity>
		extends AbstractController<S, E> {

	public AbstractRestController() {
		super();
	}

	/**
	 * Busca todas as entidades cadastradas
	 * 
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa
	 * 
	 * @return
	 */
	@GET
	public Response getEntities() {
		List<E> entities = getService().buscaTodos();

		ResponseBuilder builder;
		if (entities.isEmpty())
			builder = Response.status(NO_CONTENT);
		else
			builder = Response.status(OK).entity(entities);

		return builder.build();
	}

	/**
	 * Busca apenas uma entidade cadastrada
	 * 
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa/1
	 * 
	 * @param id
	 *            - Id da entidade
	 * @return
	 */
	@GET
	@Path("/{id}")
	public Response getEntity(@PathParam("id") Long id) {
		E entity = getService().buscaPeloId(id);

		ResponseBuilder builder;
		if (entity == null)
			builder = Response.status(NOT_FOUND);
		else
			builder = Response.status(OK).entity(entity);

		return builder.build();
	}

	/**
	 * Cadastra uma nova entidade
	 * 
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa
	 * 
	 * @param entity
	 *            - Entidade
	 * @param ucBuilder
	 * @return
	 */
	@POST
	public Response addEntity(E entity) {

		ResponseBuilder builder;

		if (/* service.isUserExist(user) */1 == 2)
			builder = Response.status(CONFLICT);
		else {
			getService().salva(entity);
			builder = Response.status(CREATED);
		}

		return builder.build();
	}

	/**
	 * Altera os dados da entidade cadastrada
	 * 
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa/1
	 * 
	 * @param id
	 *            - Id da entidade
	 * @param entity
	 *            - Entidade
	 * @return
	 */
	@PUT
	@Path("/{id}")
	public Response updateEntity(@PathParam("id") Long id, E entity) {
		entity.setId(id);
		E changedEntity = getService().buscaPeloId(id);

		ResponseBuilder builder;
		if (changedEntity == null)
			builder = Response.status(NOT_FOUND);
		else
			try {
				AppHelper.changeValues(changedEntity, entity);
				getService().atualiza(changedEntity);
				builder = Response.status(OK).entity(changedEntity);
			} catch (AppException e) {
				builder = Response.status(INTERNAL_SERVER_ERROR);
			}

		return builder.build();
	}

	/**
	 * Remove a entidade cadastrada
	 * 
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa/1
	 * 
	 * @param id
	 *            - Id da entidade
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	public Response removeEntity(@PathParam("id") Long id) {
		ResponseBuilder builder;
		if (getService().remove(id))
			builder = Response.status(OK);
		else
			builder = Response.status(NOT_FOUND);

		return builder.build();
	}

	/**
	 * Remove as entidades cadastradas
	 * 
	 * URL de exemplo:
	 * http://localhost:8080/avaliacao_marph/req/pessoa?id=1&id=2&id=3
	 * 
	 * @param ids
	 *            - Ids das entidades
	 * @return
	 */
	@DELETE
	public Response removeEntities(@QueryParam("id") List<Long> ids) {
		int count = getService().remove(ids);

		ResponseBuilder builder;
		if (count > 0) {
			Map<String, Integer> message = new HashMap<>();
			message.put("total", count);
			builder = Response.status(OK).entity(message);
		} else
			builder = Response.status(NOT_FOUND);

		return builder.build();
	}

}
