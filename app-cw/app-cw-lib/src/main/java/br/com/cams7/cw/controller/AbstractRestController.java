/**
 * 
 */
package br.com.cams7.cw.controller;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

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
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa
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
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa/1
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
	 * URL de exemplo: http://localhost:8080/avaliacao_marph/req/pessoa
	 * 
	 * @param entity
	 *            - Entidade
	 * @param ucBuilder
	 * @return
	 */
	public ResponseEntity<Void> addEntity(E entity, UriComponentsBuilder ucBuilder) {

		if (/* service.isUserExist(user) */1 == 2)
			return new ResponseEntity<Void>(CONFLICT);

		getService().salva(entity);

		return new ResponseEntity<Void>(CREATED);
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
	public ResponseEntity<E> updateEntity(Long id, E entity) {
		entity.setId(id);
		E changedEntity = getService().buscaPeloId(id);

		if (changedEntity == null)
			return new ResponseEntity<E>(NOT_FOUND);

		try {
			AppHelper.changeValues(changedEntity, entity);
			getService().atualiza(changedEntity);
			return new ResponseEntity<E>(changedEntity, OK);
		} catch (AppException e) {
		}

		return new ResponseEntity<E>(INTERNAL_SERVER_ERROR);
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
	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<E> removeEntity(@PathVariable("id") Long id) {
		if (getService().remove(id))
			new ResponseEntity<E>(OK);

		return new ResponseEntity<E>(NOT_FOUND);
	}

	/**
	 * Remove as entidades cadastradas
	 * 
	 * URL de exemplo:
	 * http://localhost:8080/avaliacao_marph/req/pessoa/ids/1,2,3
	 * 
	 * @param ids
	 *            - Ids das entidades
	 * @return
	 */
	@RequestMapping(value = "/ids/{ids}", method = DELETE)
	public ResponseEntity<Map<String, Integer>> removeEntities(@PathVariable("ids") List<Long> ids) {
		int count = getService().remove(ids);
		if (count > 0) {
			Map<String, Integer> message = new HashMap<>();
			message.put("total", count);
			new ResponseEntity<Map<String, Integer>>(message, OK);
		}

		return new ResponseEntity<Map<String, Integer>>(NOT_FOUND);
	}

	/**
	 * Manipula a Excecao
	 * 
	 * @param exception
	 *            AppException
	 * @return
	 */
	@ExceptionHandler({ AppException.class })
	public @ResponseBody ResponseEntity<?> handleException(AppException exception) {
		String errorMessage = exception.getMessage();

		ResponseEntity<?> response = new ResponseEntity<>(getHeaders(errorMessage), INTERNAL_SERVER_ERROR);
		getLog().log(Level.SEVERE, errorMessage, exception);

		return response;
	}

	/**
	 * Inclui o parametro "errorMessage" no cabecario
	 * 
	 * @param message
	 *            Messagem de erro
	 * @return
	 */
	private HttpHeaders getHeaders(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("errorMessage", message);
		return headers;
	}

}
