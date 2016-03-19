/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.cams7.app.repository.AbstractRepository;
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

	@Override
	public List<UsuarioEntity> buscaTodosDadosPessoais() {
		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = getCurrentSession().getNamedQuery("Usuario.buscaTodosDadosPessoais").list();
		return usuarios;
	}

}
