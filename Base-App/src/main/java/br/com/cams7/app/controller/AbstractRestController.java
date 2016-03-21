/**
 * 
 */
package br.com.cams7.app.controller;

import java.util.List;
import java.util.logging.Level;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cams7.app.entity.AbstractEntity;
import br.com.cams7.app.service.BaseService;
import br.com.cams7.utils.AppException;

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
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<E>> getEntities() {
		List<E> entities = getService().buscaTodos();
		if (entities.isEmpty())
			return new ResponseEntity<List<E>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<E>>(entities, HttpStatus.OK);
	}

	/**
	 * Busca apenas uma entidade cadastrada
	 * 
	 * @param id
	 *            - Id da entidade
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<E> getEntity(@PathVariable("id") Long id) {
		E entity = getService().buscaPorId(id);
		if (entity == null)
			return new ResponseEntity<E>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<E>(entity, HttpStatus.OK);
	}

	/**
	 * Cadastra uma nova entidade
	 * 
	 * @param entity
	 *            - Entidade
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> addEntity(@RequestBody E entity, UriComponentsBuilder ucBuilder) {

		// if (service.isUserExist(user))
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		getService().salva(entity);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Remove a entidade cadastrada
	 * 
	 * @param id
	 *            - Id da entidade
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<E> removeEntity(@PathVariable("id") Long id) {
		E entity = getService().buscaPorId(id);
		if (entity == null)
			return new ResponseEntity<E>(HttpStatus.NOT_FOUND);

		getService().remove(id);
		return new ResponseEntity<E>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Remove as entidades cadastradas
	 * 
	 * @param ids
	 *            - Ids das entidades
	 * @return
	 */
	@RequestMapping(value = "/ids/{ids}", method = RequestMethod.DELETE)
	public ResponseEntity<E> removeEntities(@PathVariable("ids") List<Long> ids) {

		getService().remove(ids);

		return new ResponseEntity<E>(HttpStatus.NO_CONTENT);
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

		ResponseEntity<?> response = new ResponseEntity<>(getHeaders(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
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
