/**
 * 
 */
package br.com.cams7.marph.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

	@Autowired
	private SessionFactory sessionFactory;

	public UsuarioRepositoryImpl() {
		super();
	}

	@Override
	public void salva(UsuarioEntity usuario) {
		getCurrentSession().save(usuario);
	}

	@Override
	public void atualiza(UsuarioEntity usuario) {
		getCurrentSession().update(usuario);
	}

	private void remove(UsuarioEntity usuario) {
		getCurrentSession().delete(usuario);
	}

	@Override
	public void remove(Long id) {
		UsuarioEntity usuario = buscaPorId(id);
		if (usuario != null)
			remove(usuario);
	}

	@Override
	public void remove(List<Long> ids) {
		for (Long id : ids)
			remove(id);
	}

	@Override
	public List<UsuarioEntity> buscaTodos() {
		@SuppressWarnings("unchecked")
		List<UsuarioEntity> usuarios = getCurrentSession().createCriteria(UsuarioEntity.class).list();
		return usuarios;
	}

	@Override
	public UsuarioEntity buscaPorId(Long id) {
		UsuarioEntity usuario = (UsuarioEntity) getCurrentSession().get(UsuarioEntity.class, id);
		return usuario;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
