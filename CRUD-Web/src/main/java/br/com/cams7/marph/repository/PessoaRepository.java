/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.marph.entity.PessoaEntity;

/**
 * @author cesar
 *
 */
public interface PessoaRepository extends BaseRepository<PessoaEntity> {

	List<PessoaEntity> buscaPessoasSemUsuarioPeloNome(String nome);
	
	List<PessoaEntity> buscaPeloNome(String nome);

}
