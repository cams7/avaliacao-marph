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

import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.service.EnderecoService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	public EnderecoController() {
		super();
	}

	/**
	 * Busca todos os enderecos cadastrados
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EnderecoEntity>> getEnderecos() {
		List<EnderecoEntity> enderecos = service.buscaTodos();
		if (enderecos.isEmpty())
			return new ResponseEntity<List<EnderecoEntity>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<EnderecoEntity>>(enderecos, HttpStatus.OK);
	}

	/**
	 * Busca apenas um endereco cadastrado
	 * 
	 * @param id
	 *            - Id do endereco
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EnderecoEntity> getEndereco(@PathVariable("id") Long id) {
		EnderecoEntity endereco = service.buscaPorId(id);
		if (endereco == null)
			return new ResponseEntity<EnderecoEntity>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<EnderecoEntity>(endereco, HttpStatus.OK);
	}
	
	/**
	 * Cadastra um novo endereco
	 * 
	 * @param endereco
	 *            - Entidade endereco
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaEndereco(@RequestBody EnderecoEntity endereco, UriComponentsBuilder ucBuilder) {

		// if (service.isUserExist(user))
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		service.salva(endereco);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/endereco/{id}").buildAndExpand(endereco.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Altera os dados do endereco cadastrado
	 * 
	 * @param id
	 *            - Id do endereco
	 * @param endereco
	 *            - Entidade endereco
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<EnderecoEntity> atualizaEndereco(@PathVariable("id") Long id, @RequestBody EnderecoEntity endereco) {
		EnderecoEntity enderecoCadastrado = service.buscaPorId(id);

		if (enderecoCadastrado == null)
			return new ResponseEntity<EnderecoEntity>(HttpStatus.NOT_FOUND);

		enderecoCadastrado.setRua(endereco.getRua());
		enderecoCadastrado.setBairro(endereco.getBairro());
		enderecoCadastrado.setTelefone(endereco.getTelefone());
		
		service.atualiza(enderecoCadastrado);
		return new ResponseEntity<EnderecoEntity>(enderecoCadastrado, HttpStatus.OK);
	}

	/**
	 * Remove o endereco cadastrado
	 * 
	 * @param id
	 *            - Id do endereco
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EnderecoEntity> removeEndereco(@PathVariable("id") Long id) {
		EnderecoEntity endereco = service.buscaPorId(id);
		if (endereco == null)
			return new ResponseEntity<EnderecoEntity>(HttpStatus.NOT_FOUND);

		service.remove(id);
		return new ResponseEntity<EnderecoEntity>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/ids/{ids}", method = RequestMethod.DELETE)
	public ResponseEntity<EnderecoEntity> removeEnderecos(@PathVariable("ids") List<Long> ids) {

		service.remove(ids);

		return new ResponseEntity<EnderecoEntity>(HttpStatus.NO_CONTENT);
	}

}
