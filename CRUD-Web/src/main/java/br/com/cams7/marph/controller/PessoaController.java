/**
 * 
 */
package br.com.cams7.marph.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cams7.app.AbstractRestController;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.service.PessoaService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PessoaController extends AbstractRestController<PessoaService, PessoaEntity> {

	public PessoaController() {
		super();
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
		PessoaEntity pessoaCadastrada = getService().buscaPorId(id);

		if (pessoaCadastrada == null)
			return new ResponseEntity<PessoaEntity>(HttpStatus.NOT_FOUND);

		pessoaCadastrada.setNome(pessoa.getNome());
		pessoaCadastrada.setCpf(pessoa.getCpf());
		pessoaCadastrada.setNascimento(pessoa.getNascimento());

		getService().atualiza(pessoaCadastrada);
		return new ResponseEntity<PessoaEntity>(pessoaCadastrada, HttpStatus.OK);
	}

}
