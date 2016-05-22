/**
 * 
 */
package br.com.cams7.crud.service;

import br.com.cams7.crud.entity.PessoaEntity;
import br.com.cams7.crud.repository.PessoaRepository;
import br.com.cams7.sys.service.BaseService;

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
