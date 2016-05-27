/**
 * 
 */
package br.com.cams7.crud.cw.controller.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.crud.cw.controller.rest.AbstractRestController;
import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.service.PessoaService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/pessoa", produces = APPLICATION_JSON_VALUE)
public class PessoaRestController extends AbstractRestController<PessoaService, PessoaEntity> {

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>PessoaService</code>.
	 */
	@Autowired
	private PessoaService service;

	public PessoaRestController() {
		super();
	}

	@RequestMapping(method = POST)
	@Override
	public ResponseEntity<PessoaEntity> addEntity(@RequestBody PessoaEntity entity) {
		return super.addEntity(entity);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@Override
	public ResponseEntity<PessoaEntity> updateEntity(@PathVariable("id") Long id, @RequestBody PessoaEntity entity) {
		return super.updateEntity(id, entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected PessoaService getService() {
		return service;
	}

}
