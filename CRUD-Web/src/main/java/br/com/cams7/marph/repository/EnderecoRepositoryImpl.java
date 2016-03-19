/**
 * 
 */
package br.com.cams7.marph.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.repository.AbstractRepository;
import br.com.cams7.marph.entity.EnderecoEntity;

/**
 * @author cesar
 *
 */
@Repository
public class EnderecoRepositoryImpl extends AbstractRepository<EnderecoEntity> implements EnderecoRepository {

	public EnderecoRepositoryImpl() {
		super();
	}

}
