/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.List;

import br.com.cams7.marph.entity.EnderecoEntity;

/**
 * @author cesar
 *
 */
public interface EnderecoService {
	void salva(EnderecoEntity endereco);

	void atualiza(EnderecoEntity endereco);

	void remove(Long id);

	void remove(List<Long> ids);

	List<EnderecoEntity> buscaTodos();

	EnderecoEntity buscaPorId(Long id);
}
