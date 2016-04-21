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
	 * Retorna todos os objetos que são instâncias da classe "PessoaEntity" e
	 * que não estão realacionados a nenhum usuário, filtrando-os pelo nome da
	 * pessoa
	 * 
	 * @param nome
	 * @return Pessoas
	 */
	List<PessoaEntity> buscaPessoasSemUsuarioPeloNome(String nome);

	/**
	 * Retorna os objetos que são instâncias da classe "PessoaEntity",
	 * filtrando-os pelo nome da pessoa
	 * 
	 * @param nome
	 * @return Pessoas
	 */
	List<PessoaEntity> buscaPeloNome(String nome);
	
	/**
	 * Verica se o CPF foi cadastrado anteriormente
	 * 
	 * @param cpf
	 *            CPF
	 * @return
	 */
	boolean cpfFoiCadastradoAnteriormente(String cpf);

	/**
	 * Retorna o cpf cadastrado
	 * 
	 * @param id
	 *            Id da pessoa
	 * @return
	 */
	String buscaCpfPeloId(Long id);

}
