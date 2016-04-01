/**
 * 
 */
package br.com.cams7.marph.service;

import java.util.HashSet;
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
import br.com.cams7.marph.entity.UsuarioEntity.Autorizacao;

/**
 * @author cesar
 *
 */
@Service("authenticationService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Metodo chamado apos o login
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		if (login == null || "".equals(login))
			throw new UsernameNotFoundException("username is empty or null");

		Session session = sessionFactory.getCurrentSession();

		Query query = session.getNamedQuery("Usuario.buscaPeloLogin");
		query.setParameter("login", login);
		UsuarioEntity usuario = (UsuarioEntity) query.uniqueResult();

		return buildUserForAuthentication(usuario, getAuthority(usuario.getAutorizacoes()));
	}

	// Converts br.com.cams7.marph.entity.UsuarioEntity user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(UsuarioEntity usuario, Set<? extends GrantedAuthority> autorizacoes) {
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		boolean habilitado = usuario.getHabilitado();

		return new User(login, senha, habilitado, true, true, true, autorizacoes);
	}

	private Set<Authority> getAuthority(Set<Autorizacao> autorizacoes) {
		Set<Authority> authorities = new HashSet<>();
		for (Autorizacao autorizacao : autorizacoes)
			authorities.add(Authority.valueOf(autorizacao.name()));

		return authorities;
	}

	public enum Authority implements GrantedAuthority {
		CLIENTE, SECRETARIO, ADMINISTRADOR;

		@Override
		public String getAuthority() {
			return toString();
		}
	}

}
