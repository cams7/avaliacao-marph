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

import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController {

	@Autowired
	private PessoaService service;

	public PessoaController() {
		super();
	}

	/**
	 * Busca todas as pessoas cadastradas
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PessoaEntity>> getPessoas() {
		List<PessoaEntity> pessoas = service.buscaTodos();
		if (pessoas.isEmpty())
			return new ResponseEntity<List<PessoaEntity>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<PessoaEntity>>(pessoas, HttpStatus.OK);
	}

	/**
	 * Busca apenas uma pessoa cadastrada
	 * 
	 * @param id
	 *            - Id da pessoa
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PessoaEntity> getPessoa(@PathVariable("id") Long id) {
		PessoaEntity pessoa = service.buscaPorId(id);
		if (pessoa == null)
			return new ResponseEntity<PessoaEntity>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<PessoaEntity>(pessoa, HttpStatus.OK);
	}
	
	/**
	 * Cadastra uma nova pessoa
	 * 
	 * @param pessoa
	 *            - Entidade pessoa
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> adicionaPessoa(@RequestBody PessoaEntity pessoa, UriComponentsBuilder ucBuilder) {

		// if (service.isUserExist(user))
		// return new ResponseEntity<Void>(HttpStatus.CONFLICT);

		service.salva(pessoa);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	/**
	 * Altera os dados da pessoa cadastrada
	 * 
	 * @param id
	 *            - Id da pessoa
	 * @param pessoa
	 *            - Entidade pessoa
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<PessoaEntity> atualizaPessoa(@PathVariable("id") Long id, @RequestBody PessoaEntity pessoa) {
		PessoaEntity pessoaCadastrada = service.buscaPorId(id);

		if (pessoaCadastrada == null)
			return new ResponseEntity<PessoaEntity>(HttpStatus.NOT_FOUND);

		pessoaCadastrada.setNome(pessoa.getNome());
		pessoaCadastrada.setCpf(pessoa.getCpf());
		pessoaCadastrada.setNascimento(pessoa.getNascimento());
		
		service.atualiza(pessoaCadastrada);
		return new ResponseEntity<PessoaEntity>(pessoaCadastrada, HttpStatus.OK);
	}

	/**
	 * Remove a pessoa cadastrada
	 * 
	 * @param id
	 *            - Id da pessoa
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<PessoaEntity> removePessoa(@PathVariable("id") Long id) {
		PessoaEntity pessoa = service.buscaPorId(id);
		if (pessoa == null)
			return new ResponseEntity<PessoaEntity>(HttpStatus.NOT_FOUND);

		service.remove(id);
		return new ResponseEntity<PessoaEntity>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/ids/{ids}", method = RequestMethod.DELETE)
	public ResponseEntity<PessoaEntity> removePessoas(@PathVariable("ids") List<Long> ids) {

		service.remove(ids);

		return new ResponseEntity<PessoaEntity>(HttpStatus.NO_CONTENT);
	}

}
