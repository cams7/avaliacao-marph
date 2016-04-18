/**
 * 
 */
package br.com.cams7.as.controller.rest;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.NO_CONTENT;
import static javax.ws.rs.core.Response.Status.OK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.cams7.app.SortOrder;
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
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa
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
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/1
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
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa
	 * @Content Type: application/json
	 * @Content: {"nome":"Luiz Alberto da Silva","cpf":"82211304273"}
	 * 
	 * @param entity
	 *            - Entidade
	 * @param ucBuilder
	 * @return
	 */
	@POST
	public Response addEntity(E entity) {
		getService().salva(entity);
		ResponseBuilder builder = Response.status(CREATED).entity(entity);

		return builder.build();
	}

	/**
	 * Altera os dados da entidade cadastrada
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/1
	 * @Content Type: application/json
	 * @Content: {"nome":"Alfredo Alberto Almeida","cpf":"83605637051"}
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
		else {
			AppHelper.changeValues(changedEntity, entity);
			getService().atualiza(changedEntity);
			builder = Response.status(OK).entity(changedEntity);
		}

		return builder.build();
	}

	/**
	 * Remove a entidade cadastrada
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/38
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

	private Map<String, Integer> getCount(int count) {
		Map<String, Integer> message = new HashMap<>();
		message.put("count", count);
		return message;
	}

	/**
	 * Remove as entidades cadastradas
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa?id=37&id=38&id=39
	 * 
	 * @param ids
	 *            - Ids das entidades
	 * @return
	 */
	@DELETE
	public Response removeEntities(@QueryParam("id") List<Long> ids) {
		int count = getService().remove(ids);

		ResponseBuilder builder;
		if (count > 0)
			builder = Response.status(OK).entity(getCount(count));
		else
			builder = Response.status(NO_CONTENT);

		return builder.build();
	}

	/**
	 * @param messages
	 * @param paramName
	 * @param paramValues
	 * @return
	 */
	private boolean onlyOneParameter(Map<String, String> messages, String paramName, List<String> paramValues) {
		if (paramValues.size() > 1) {
			messages.put(paramName,
					String.format("URI inválida, porque o parâmetro foi passado %s vezes", paramValues.size()));
			return false;
		}
		return true;
	}

	/**
	 * @param messages
	 * @param paramName
	 */
	private void validParameter(Map<String, String> messages, String paramName) {
		messages.put(paramName, "URI inválida, porque o parâmetro não é válido");
	}

	/**
	 * Filtra, pagina e ordena os objetos que são instâncias de "AbstractEntity"
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/search?page_first=
	 *       0&page_size=15&sort_field=nascimento&sort_order=DESCENDING&
	 *       filter_field=nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
	 * 
	 * @param allUri
	 * @return
	 */
	@GET
	@Path("/search")
	public Response search(@Context UriInfo allUri) {
		Integer pageFirst = null;
		Short pageSize = null;
		String sortField = null;
		SortOrder sortOrder = null;
		String[] globalFilters = null;
		Map<String, Object> filters = new HashMap<>();

		Map<String, String> messages = new HashMap<>();

		final String PAGE_FIRST = "page_first";
		final String PAGE_SIZE = "page_size";
		final String SORT_FIELD = "sort_field";
		final String SORT_ORDER = "sort_order";
		final String FILTER_FIELD = "filter_field";
		final String GLOBAL_FILTER = "globalFilter";

		MultivaluedMap<String, String> allParams = allUri.getQueryParameters();

		for (Entry<String, List<String>> param : allParams.entrySet()) {
			String paramName = param.getKey();
			List<String> paramValues = param.getValue();

			switch (paramName) {
			case PAGE_FIRST:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						pageFirst = Integer.parseInt(paramValues.get(0));
					} catch (NumberFormatException e) {
						validParameter(messages, paramName);
					}
				break;
			case PAGE_SIZE:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						pageSize = Short.parseShort(paramValues.get(0));
					} catch (NumberFormatException e) {
						validParameter(messages, paramName);
					}
				break;
			case SORT_FIELD:
				if (onlyOneParameter(messages, paramName, paramValues))
					sortField = paramValues.get(0);
				break;
			case SORT_ORDER:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						sortOrder = SortOrder.valueOf(paramValues.get(0));
					} catch (IllegalArgumentException e) {
						validParameter(messages, paramName);
					}
				break;
			case FILTER_FIELD:
				globalFilters = paramValues.toArray(new String[] {});
				break;
			default:
				if (onlyOneParameter(messages, paramName, paramValues)) {
					Object value = paramValues.get(0);
					if (!GLOBAL_FILTER.equals(paramName))
						try {
							value = AppHelper.getFieldValue(getEntityType(), paramName, paramValues.get(0));
						} catch (AppException e) {
							validParameter(messages, paramName);
							break;
						}
					filters.put(paramName, value);
				}
				break;
			}
		}

		ResponseBuilder builder;

		if (messages.isEmpty()) {
			List<E> entities = getService().search(pageFirst, pageSize, sortField, sortOrder, filters, globalFilters);

			if (entities.isEmpty())
				builder = Response.status(NO_CONTENT);
			else
				builder = Response.status(OK).entity(entities);
		} else
			builder = Response.status(BAD_REQUEST).entity(messages);

		return builder.build();
	}

	/**
	 * Retorna o número total de instâncias de "AbstractEntity". Essa pesquisa é
	 * feita com auxílio de filtros
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/count?filter_field
	 *       =nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
	 * 
	 * @param allUri
	 * @return
	 */
	@GET
	@Path("/count")
	public Response count(@Context UriInfo allUri) {
		String[] globalFilters = null;
		Map<String, Object> filters = new HashMap<>();

		Map<String, String> messages = new HashMap<>();

		final String FILTER_FIELD = "filter_field";
		final String GLOBAL_FILTER = "globalFilter";

		MultivaluedMap<String, String> allParams = allUri.getQueryParameters();

		for (Entry<String, List<String>> param : allParams.entrySet()) {
			String paramName = param.getKey();
			List<String> paramValues = param.getValue();

			switch (paramName) {
			case FILTER_FIELD:
				globalFilters = paramValues.toArray(new String[] {});
				break;
			default:
				if (onlyOneParameter(messages, paramName, paramValues)) {
					Object value = paramValues.get(0);
					if (!GLOBAL_FILTER.equals(paramName))
						try {
							value = AppHelper.getFieldValue(getEntityType(), paramName, paramValues.get(0));
						} catch (AppException e) {
							validParameter(messages, paramName);
							break;
						}
					filters.put(paramName, value);
				}
				break;
			}
		}

		ResponseBuilder builder;

		if (messages.isEmpty()) {
			int count;
			if (globalFilters == null && filters.isEmpty())
				count = getService().count();
			else
				count = getService().getTotalElements(filters, globalFilters);

			builder = Response.status(OK).entity(getCount(count));
		} else
			builder = Response.status(BAD_REQUEST).entity(messages);

		return builder.build();
	}

}
