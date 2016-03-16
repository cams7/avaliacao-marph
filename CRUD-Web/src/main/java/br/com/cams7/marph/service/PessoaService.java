/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import br.com.cams7.marph.entity.PessoaEntity;

/**
 * @author cesar
 *
 */
public interface PessoaService {
	void salva(PessoaEntity pessoa);

	void atualiza(PessoaEntity pessoa);

	void remove(Long id);

	void remove(List<Long> ids);

	List<PessoaEntity> buscaTodos();

	PessoaEntity buscaPorId(Long id);
}
