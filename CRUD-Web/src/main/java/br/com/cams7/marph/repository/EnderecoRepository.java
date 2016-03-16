/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import br.com.cams7.marph.entity.EnderecoEntity;

/**
 * @author cesar
 *
 */
public interface EnderecoRepository {
	void salva(EnderecoEntity endereco);

	void atualiza(EnderecoEntity endereco);

	void remove(Long id);

	void remove(List<Long> ids);

	List<EnderecoEntity> buscaTodos();

	EnderecoEntity buscaPorId(Long id);
}
