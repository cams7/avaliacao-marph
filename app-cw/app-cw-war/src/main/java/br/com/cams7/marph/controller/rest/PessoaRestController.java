/**
 * 
 */
package br.com.cams7.marph.controller.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.cw.controller.rest.AbstractRestController;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/pessoa", produces = APPLICATION_JSON_VALUE)
public class PessoaRestController extends AbstractRestController<PessoaService, PessoaEntity> {

	public PessoaRestController() {
		super();
	}

	/*
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>PessoaService</code>.
	 * 
	 * @see
	 * br.com.cams7.cw.controller.AbstractController#setService(br.com.cams7.app
	 * .service.BaseService)
	 */
	@Autowired
	@Override
	protected void setService(PessoaService service) {
		super.setService(service);
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

}
