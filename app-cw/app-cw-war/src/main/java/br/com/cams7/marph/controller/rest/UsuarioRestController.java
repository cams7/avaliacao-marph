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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cams7.cw.controller.AbstractRestController;
import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.service.UsuarioService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioRestController extends AbstractRestController<UsuarioService, UsuarioEntity> {

	public UsuarioRestController() {
		super();
	}

	/*
	 * Utiliza a injeção de dependência do <code>Spring Framework</code> para
	 * resolver a instância do <code>UsuarioService</code>.
	 * 
	 * @see
	 * br.com.cams7.cw.controller.AbstractController#setService(br.com.cams7.app
	 * .service.BaseService)
	 */
	@Autowired
	@Override
	protected void setService(UsuarioService service) {
		super.setService(service);
	}

	@RequestMapping(method = POST)
	@Override
	public ResponseEntity<Void> addEntity(@RequestBody UsuarioEntity entity, UriComponentsBuilder ucBuilder) {
		return super.addEntity(entity, ucBuilder);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@Override
	public ResponseEntity<UsuarioEntity> updateEntity(@PathVariable("id") Long id, @RequestBody UsuarioEntity entity) {
		return super.updateEntity(id, entity);
	}

}
