/**
 * 
 */
package br.com.cams7.marph.service;

import br.com.cams7.app.service.BaseService;
import br.com.cams7.marph.entity.PessoaEntity;
import br.com.cams7.marph.repository.PessoaRepository;

/**
 * @author cesar
 *
 */
public interface PessoaService extends BaseService<PessoaEntity>, PessoaRepository {

	/**
	 * Vefifica se o CPF informado é valido
	 * 
	 * @param pessoaId
	 *            Id da pessoa
	 * @param cpf
	 *            CPF informado
	 * @return
	 */
	boolean cpfValido(Long pessoaId, String cpf);
}
