/**
 * 
 */
package br.com.cams7.marph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cams7.marph.entity.UsuarioEntity;
import br.com.cams7.marph.service.UsuarioService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	public UsuarioController() {
		super();
	}

	/**
	 * Busca todos os usuarios cadastrados
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioEntity>> getUsuarios() {
		List<UsuarioEntity> usuarios = service.buscaTodos();
		if (usuarios.isEmpty())
			return new ResponseEntity<List<UsuarioEntity>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<UsuarioEntity>>(usuarios, HttpStatus.OK);
	}

	/**
	 * Busca apenas um usuario cadastrado
	 * 
	 * @param id
	 *            - Id do usuario
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioEntity> getUsuario(@PathVariable("id") Long id) {
		UsuarioEntity usuario = service.buscaPorId(id);
		if (usuario == null)
			return new ResponseEntity<UsuarioEntity>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<UsuarioEntity>(usuario, HttpStatus.OK);
	}

	/**
	 * Cadastra um novo usuario
	 * 
	 * @param usuario
	 *            - Entidade usuario
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaUsuario(@RequestBody UsuarioEntity usuario, UriComponentsBuilder ucBuilder) {

		// if (service.isUserExist(user))
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		service.salva(usuario);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
		UsuarioEntity usuarioCadastrado = service.buscaPorId(id);

		if (usuarioCadastrado == null)
			return new ResponseEntity<UsuarioEntity>(HttpStatus.NOT_FOUND);

		usuarioCadastrado.setLogin(usuario.getLogin());
		usuarioCadastrado.setSenha(usuario.getSenha());
		usuarioCadastrado.setHabilitado(usuario.getHabilitado());

		service.atualiza(usuarioCadastrado);
		return new ResponseEntity<UsuarioEntity>(usuarioCadastrado, HttpStatus.OK);
	}

	/**
	 * Remove o usuario cadastrado
	 * 
	 * @param id
	 *            - Id do usuario
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UsuarioEntity> removeUsuario(@PathVariable("id") Long id) {
		UsuarioEntity usuario = service.buscaPorId(id);
		if (usuario == null)
			return new ResponseEntity<UsuarioEntity>(HttpStatus.NOT_FOUND);

		service.remove(id);
		return new ResponseEntity<UsuarioEntity>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/ids/{ids}", method = RequestMethod.DELETE)
	public ResponseEntity<UsuarioEntity> removeUsuarios(@PathVariable("ids") List<Long> ids) {

		service.remove(ids);

		return new ResponseEntity<UsuarioEntity>(HttpStatus.NO_CONTENT);
	}

}
