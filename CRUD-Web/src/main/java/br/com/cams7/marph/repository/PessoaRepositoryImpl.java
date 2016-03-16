/**
 * 
 */
package br.com.cams7.marph.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.AbstractRepository;
import br.com.cams7.marph.entity.PessoaEntity;

/**
 * @author cesar
 *
 */
@Repository
public class PessoaRepositoryImpl extends AbstractRepository<PessoaEntity> implements PessoaRepository {

	public PessoaRepositoryImpl() {
		super();
	}

}
