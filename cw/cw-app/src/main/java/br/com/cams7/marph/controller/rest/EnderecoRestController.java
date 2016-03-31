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

import br.com.cams7.app.controller.AbstractRestController;
import br.com.cams7.marph.entity.EnderecoEntity;
import br.com.cams7.marph.service.EnderecoService;

/**
 * @author cesar
 *
 */
@RestController
@RequestMapping(value = "/endereco", produces = MediaType.APPLICATION_JSON_VALUE)
public class EnderecoRestController extends AbstractRestController<EnderecoService, EnderecoEntity> {

	public EnderecoRestController() {
		super();
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
	public ResponseEntity<EnderecoEntity> atualizaEndereco(@PathVariable("id") Long id,
			@RequestBody EnderecoEntity endereco) {
		EnderecoEntity enderecoCadastrado = getService().buscaPeloId(id);

		if (enderecoCadastrado == null)
			return new ResponseEntity<EnderecoEntity>(HttpStatus.NOT_FOUND);

		enderecoCadastrado.setRua(endereco.getRua());
		enderecoCadastrado.setBairro(endereco.getBairro());
		enderecoCadastrado.setTelefone(endereco.getTelefone());

		getService().atualiza(enderecoCadastrado);
		return new ResponseEntity<EnderecoEntity>(enderecoCadastrado, HttpStatus.OK);
	}

}
