/**
 * 
 */
package br.com.cams7.marph.repository;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.AbstractRepository;
import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
@Repository
public class UsuarioRepositoryImpl extends AbstractRepository<UsuarioEntity> implements UsuarioRepository {

	public UsuarioRepositoryImpl() {
		super();
	}

}
