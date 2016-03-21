/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import br.com.cams7.app.repository.BaseRepository;
import br.com.cams7.marph.entity.PessoaEntity;

/**
 * DAO de pessoa
 * 
 * @author cesar
 *
 */
public interface PessoaRepository extends BaseRepository<PessoaEntity> {

	/**
	 * Busca pelo nome todas as pessoas que nao estao realacionadas a nenhum
	 * usuario
	 * 
	 * @param nome
	 * @return Pessoas
	 */
	List<PessoaEntity> buscaPessoasSemUsuarioPeloNome(String nome);

	/**
	 * Busca pelo nome todas as pessoas
	 * 
	 * @param nome
	 * @return Pessoas
	 */
	List<PessoaEntity> buscaPeloNome(String nome);

}
