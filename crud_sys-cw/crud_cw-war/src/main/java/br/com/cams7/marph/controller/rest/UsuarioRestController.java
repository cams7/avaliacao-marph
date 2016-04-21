/**
 * 
 */
package br.com.cams7.marph.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.cw.controller.rest.AbstractRestController;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.service.UsuarioService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioRestController extends AbstractRestController<UsuarioService, UsuarioEntity> {

	/**
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>UsuarioService</code>.
	 */
	@Autowired
	private UsuarioService service;

	public UsuarioRestController() {
		super();
	}

	@RequestMapping(method = POST)
	@Override
	public ResponseEntity<UsuarioEntity> addEntity(@RequestBody UsuarioEntity entity) {
		return super.addEntity(entity);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@Override
	public ResponseEntity<UsuarioEntity> updateEntity(@PathVariable("id") Long id, @RequestBody UsuarioEntity entity) {
		return super.updateEntity(id, entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.cams7.app.controller.AbstractController#getService()
	 */
	@Override
	protected UsuarioService getService() {
		return service;
	}

}
