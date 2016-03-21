/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.marph.entity.UsuarioEntity;

/**
 * @author cesar
 *
 */
@Service("authenticationService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username == null || "".equals(username))
			throw new UsernameNotFoundException("username is empty or null");

		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Usuario.buscaPeloLogin");
		query.setParameter("login", username);
		UsuarioEntity usuario = (UsuarioEntity) query.uniqueResult();

		return buildUserForAuthentication(usuario, usuario.getAutorizacoes());
	}

	// Converts br.com.cams7.marph.entity.UsuarioEntity user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(UsuarioEntity usuario, Set<? extends GrantedAuthority> authorities) {
		String username = usuario.getLogin();
		String password = usuario.getSenha();
		boolean enabled = usuario.getHabilitado();

		return new User(username, password, enabled, true, true, true, authorities);
	}

}
