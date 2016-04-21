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

import br.com.cams7.sys.SearchParams;
import br.com.cams7.sys.controller.AbstractController;
import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.service.BaseService;
import br.com.cams7.sys.utils.AppException;
import br.com.cams7.sys.utils.AppHelper;
import br.com.cams7.sys.utils.URIHelper;

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
	 * @URL: http://localhost:8080/crud_sys/req/pessoa
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
	 * @URL: http://localhost:8080/crud_sys/req/pessoa/1
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
	 * @URL: http://localhost:8080/crud_sys/req/pessoa
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
	 * @URL: http://localhost:8080/crud_sys/req/pessoa/1
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
	 * @URL: http://localhost:8080/crud_sys/req/pessoa/38
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
	 * @URL: http://localhost:8080/crud_sys/req/pessoa?id=37&id=38&id=39
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
	 * Filtra, pagina e ordena os objetos que são instâncias de "AbstractEntity"
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/crud_sys/req/pessoa/search?page_first=
	 *       0&page_size=15&sort_field=nascimento&sort_order=DESCENDING&
	 *       filter_field=nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
	 * 
	 * @param allUri
	 * @return
	 */
	@RequestMapping(value = "/search", method = GET)
	public ResponseEntity<Object> search(HttpServletRequest request) {
		SearchParams params = URIHelper.getParams(getEntityType(), request.getParameterMap());
		List<E> entities = getService().search(params);

		if (entities.isEmpty())
			return new ResponseEntity<Object>(NO_CONTENT);

		return new ResponseEntity<Object>(entities, OK);
	}

	/**
	 * Retorna o número total de instâncias de "AbstractEntity". Essa pesquisa é
	 * feita com auxílio de filtros
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/crud_sys/req/pessoa/count?filter_field
	 *       =nome&filter_field=cpf&globalFilter=m&nome=a&cpf=6
	 * 
	 * @param allUri
	 * @return
	 */
	@RequestMapping(value = "/count", method = GET)
	public ResponseEntity<Map<String, ?>> count(HttpServletRequest request) {
		SearchParams params = URIHelper.getParams(getEntityType(), request.getParameterMap());

		int count;
		if (params.getGlobalFilters() == null && params.getFilters().isEmpty())
			count = getService().count();
		else
			count = getService().getTotalElements(params.getFilters(), params.getGlobalFilters());

		return new ResponseEntity<Map<String, ?>>(getCount(count), OK);
	}

	/**
	 * @param exceptionMessage
	 *            Mensagem de exceção
	 * @return
	 */
	private Map<String, String> getMessage(String exceptionMessage) {
		Map<String, String> message = new HashMap<>();
		message.put("errorMessage", exceptionMessage);
		return message;
	}

	/**
	 * Manipula a Excecao
	 * 
	 * @param ex
	 *            RuntimeException
	 * @return
	 */
	@ExceptionHandler({ AppException.class, HibernateException.class, DataIntegrityViolationException.class,
			HibernateOptimisticLockingFailureException.class })
	public @ResponseBody ResponseEntity<Map<String, ?>> handleException(RuntimeException ex) {
		if (ex instanceof AppException) {
			AppException exception = (AppException) ex;
			Map<String, String> messages = exception.getMessages();
			if (messages != null)
				return new ResponseEntity<Map<String, ?>>(messages, BAD_REQUEST);

			getLog().log(Level.WARNING, exception.getMessage(), exception);

			return new ResponseEntity<Map<String, ?>>(getMessage(exception.getMessage()), BAD_REQUEST);
		}

		getLog().log(Level.SEVERE, ex.getMessage(), ex);

		return new ResponseEntity<Map<String, ?>>(getMessage(ex.getMessage()), INTERNAL_SERVER_ERROR);
	}

}
