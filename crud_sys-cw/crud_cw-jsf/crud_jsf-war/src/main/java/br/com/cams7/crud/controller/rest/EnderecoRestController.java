/**
 * 
 */
package br.com.cams7.crud.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.crud.entity.EnderecoEntity;
import br.com.cams7.crud.service.EnderecoService;
import br.com.cams7.cw.controller.rest.AbstractRestController;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoRestController extends AbstractRestController<EnderecoService, EnderecoEntity> {

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>EnderecoService</code>.
	 */
	@Autowired
	private EnderecoService service;

	public EnderecoRestController() {
		super();
	}

	@RequestMapping(method = POST)
	@Override
	public ResponseEntity<EnderecoEntity> addEntity(@RequestBody EnderecoEntity entity) {
		return super.addEntity(entity);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@Override
	public ResponseEntity<EnderecoEntity> updateEntity(@PathVariable("id") Long id,
			@RequestBody EnderecoEntity entity) {
		return super.updateEntity(id, entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected EnderecoService getService() {
		return service;
	}

}
