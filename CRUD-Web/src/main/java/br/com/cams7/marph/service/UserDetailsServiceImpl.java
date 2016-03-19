/**
 * 
 */
package br.com.cams7.marph.service;

//import static br.com.cams7.marph.entity.UsuarioEntity.Role.ROLE_ADMIN;
//import static br.com.cams7.marph.entity.UsuarioEntity.Role.ROLE_NEWUSER;
//import static br.com.cams7.marph.entity.UsuarioEntity.Role.ROLE_USER;

//import java.util.Arrays;
//import java.util.HashSet;
import java.util.Set;

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
//import br.com.cams7.marph.entity.UsuarioEntity.Role;

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
		UsuarioEntity usuario = (UsuarioEntity) session.getNamedQuery("Usuario.buscaPorLogin")
				.setParameter("login", username).uniqueResult();

		return buildUserForAuthentication(usuario, usuario.getAuthorities());
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
