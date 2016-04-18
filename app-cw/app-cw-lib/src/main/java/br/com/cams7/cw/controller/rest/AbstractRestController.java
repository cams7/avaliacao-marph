/**
 * 
 */
package br.com.cams7.cw.controller.rest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cams7.app.SortOrder;
import br.com.cams7.app.controller.AbstractController;
import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.app.utils.AppException;
import br.com.cams7.app.utils.AppHelper;

/**
 * Classe comum as classes RestControllers
 * 
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
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa
	 * 
	 * @return
	 */
	@RequestMapping(method = GET)
	public ResponseEntity<List<E>> getEntities() {
		List<E> entities = getService().buscaTodos();
		if (entities.isEmpty())
			return new ResponseEntity<List<E>>(NO_CONTENT);

		return new ResponseEntity<List<E>>(entities, OK);
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
	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<E> getEntity(@PathVariable("id") Long id) {
		E entity = getService().buscaPeloId(id);
		if (entity == null)
			return new ResponseEntity<E>(NOT_FOUND);

		return new ResponseEntity<E>(entity, OK);
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
	public ResponseEntity<E> addEntity(E entity) {
		getService().salva(entity);

		return new ResponseEntity<E>(entity, CREATED);
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
	public ResponseEntity<E> updateEntity(Long id, E entity) {
		entity.setId(id);
		E changedEntity = getService().buscaPeloId(id);

		if (changedEntity == null)
			return new ResponseEntity<E>(NOT_FOUND);

		AppHelper.changeValues(changedEntity, entity);
		getService().atualiza(changedEntity);
		return new ResponseEntity<E>(changedEntity, OK);
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
	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<Void> removeEntity(@PathVariable("id") Long id) {
		getService().remove(id);

		return new ResponseEntity<Void>(OK);
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
	@RequestMapping(method = DELETE)
	public ResponseEntity<Map<String, Integer>> removeEntities(HttpServletRequest request) {
		Map<String, String[]> params = request.getParameterMap();
		List<Long> ids = new ArrayList<>();
		for (String id : params.get("id")) {
			try {
				ids.add(Long.parseLong(id));
			} catch (NumberFormatException e) {
				throw new AppException("URI inválida, porque o valor de algum parâmetro não é válido");
			}
		}

		int count = getService().remove(ids);
		return new ResponseEntity<Map<String, Integer>>(getCount(count), OK);
	}

	/**
	 * @param messages
	 * @param paramName
	 * @param paramValues
	 * @return
	 */
	private boolean onlyOneParameter(Map<String, String> messages, String paramName, String[] paramValues) {
		if (paramValues.length > 1) {
			messages.put(paramName,
					String.format("URI inválida, porque o parâmetro foi passado %s vezes", paramValues.length));
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
	@RequestMapping(value = "/search", method = GET)
	public ResponseEntity<Object> search(HttpServletRequest request) {
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

		Map<String, String[]> allParams = request.getParameterMap();

		for (Entry<String, String[]> param : allParams.entrySet()) {
			String paramName = param.getKey();
			String[] paramValues = param.getValue();

			switch (paramName) {
			case PAGE_FIRST:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						pageFirst = Integer.parseInt(paramValues[0]);
					} catch (NumberFormatException e) {
						validParameter(messages, paramName);
					}
				break;
			case PAGE_SIZE:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						pageSize = Short.parseShort(paramValues[0]);
					} catch (NumberFormatException e) {
						validParameter(messages, paramName);
					}
				break;
			case SORT_FIELD:
				if (onlyOneParameter(messages, paramName, paramValues))
					sortField = paramValues[0];
				break;
			case SORT_ORDER:
				if (onlyOneParameter(messages, paramName, paramValues))
					try {
						sortOrder = SortOrder.valueOf(paramValues[0]);
					} catch (IllegalArgumentException e) {
						validParameter(messages, paramName);
					}
				break;
			case FILTER_FIELD:
				globalFilters = paramValues;
				break;
			default:
				if (onlyOneParameter(messages, paramName, paramValues)) {
					Object value = paramValues[0];
					if (!GLOBAL_FILTER.equals(paramName))
						try {
							value = AppHelper.getFieldValue(getEntityType(), paramName, paramValues[0]);
						} catch (AppException e) {
							validParameter(messages, paramName);
							break;
						}
					filters.put(paramName, value);
				}
				break;
			}
		}

		if (!messages.isEmpty())
			return new ResponseEntity<Object>(messages, BAD_REQUEST);

		List<E> entities = getService().search(pageFirst, pageSize, sortField, sortOrder, filters, globalFilters);

		if (entities.isEmpty())
			return new ResponseEntity<Object>(NO_CONTENT);

		return new ResponseEntity<Object>(entities, OK);
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
	@RequestMapping(value = "/count", method = GET)
	public ResponseEntity<Map<String, ?>> count(HttpServletRequest request) {
		String[] globalFilters = null;
		Map<String, Object> filters = new HashMap<>();

		Map<String, String> messages = new HashMap<>();

		final String FILTER_FIELD = "filter_field";
		final String GLOBAL_FILTER = "globalFilter";

		Map<String, String[]> allParams = request.getParameterMap();

		for (Entry<String, String[]> param : allParams.entrySet()) {
			String paramName = param.getKey();
			String[] paramValues = param.getValue();

			switch (paramName) {
			case FILTER_FIELD:
				globalFilters = paramValues;
				break;
			default:
				if (onlyOneParameter(messages, paramName, paramValues)) {
					Object value = paramValues[0];
					if (!GLOBAL_FILTER.equals(paramName))
						try {
							value = AppHelper.getFieldValue(getEntityType(), paramName, paramValues[0]);
						} catch (AppException e) {
							validParameter(messages, paramName);
							break;
						}
					filters.put(paramName, value);
				}
				break;
			}
		}

		if (!messages.isEmpty())
			return new ResponseEntity<Map<String, ?>>(messages, BAD_REQUEST);

		int count;
		if (globalFilters == null && filters.isEmpty())
			count = getService().count();
		else
			count = getService().getTotalElements(filters, globalFilters);

		return new ResponseEntity<Map<String, ?>>(getCount(count), OK);
	}

	/**
	 * Manipula a Excecao
	 * 
	 * @param exception
	 *            RuntimeException
	 * @return
	 */
	@ExceptionHandler({ AppException.class, HibernateException.class, DataIntegrityViolationException.class,
			HibernateOptimisticLockingFailureException.class })
	public @ResponseBody ResponseEntity<Map<String, String>> handleException(RuntimeException exception) {
		String exceptionMessage = exception.getMessage();

		Map<String, String> message = new HashMap<>();
		message.put("errorMessage", exceptionMessage);

		ResponseEntity<Map<String, String>> response = new ResponseEntity<>(message, INTERNAL_SERVER_ERROR);
		getLog().log(Level.SEVERE, exceptionMessage, exception);

		return response;
	}

}
