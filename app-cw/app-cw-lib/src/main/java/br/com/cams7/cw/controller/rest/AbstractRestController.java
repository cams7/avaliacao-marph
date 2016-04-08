/**
 * 
 */
package br.com.cams7.cw.controller.rest;

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

import org.hibernate.HibernateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
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
	public ResponseEntity<E> addEntity(E entity, UriComponentsBuilder ucBuilder) {
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

	/**
	 * Remove as entidades cadastradas
	 * 
	 * @Exemplo Executado no Poster (plug-in do Firefox):
	 * @URL: http://localhost:8080/avaliacao_marph/req/pessoa/ids/1,2,3
	 * 
	 * @param ids
	 *            - Ids das entidades
	 * @return
	 */
	@RequestMapping(value = "/ids/{ids}", method = DELETE)
	public ResponseEntity<Map<String, Integer>> removeEntities(@PathVariable("ids") List<Long> ids) {
		int count = getService().remove(ids);

		Map<String, Integer> message = new HashMap<>();
		message.put("total", count);

		return new ResponseEntity<Map<String, Integer>>(message, OK);
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
