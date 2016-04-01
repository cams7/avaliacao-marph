/**
 * 
 */
package br.com.cams7.marph.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * Altera os dados do usuario cadastrado
	 * 
	 * @param id
	 *            - Id do usuario
	 * @param usuario
	 *            - Entidade usuario
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UsuarioEntity> atualizaUsuario(@PathVariable("id") Long id,
			@RequestBody UsuarioEntity usuario) {
		UsuarioEntity usuarioCadastrado = getService().buscaPeloId(id);

		if (usuarioCadastrado == null)
			return new ResponseEntity<UsuarioEntity>(HttpStatus.NOT_FOUND);

		usuarioCadastrado.setLogin(usuario.getLogin());
		usuarioCadastrado.setSenha(usuario.getSenha());
		usuarioCadastrado.setHabilitado(usuario.getHabilitado());

		getService().atualiza(usuarioCadastrado);
		return new ResponseEntity<UsuarioEntity>(usuarioCadastrado, HttpStatus.OK);
	}

}
