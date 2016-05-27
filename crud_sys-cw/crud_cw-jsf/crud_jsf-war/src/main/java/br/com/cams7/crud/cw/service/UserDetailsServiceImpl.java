/**
 * 
 */
package br.com.cams7.crud.cw.service;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cams7.crud.entity.UsuarioEntity;
import br.com.cams7.crud.entity.UsuarioEntity.Autorizacao;

/**
 * @author cesar
 *
 */
@Service("authenticationService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@PersistenceContext // (unitName = "CRUDSysUnit")
	private EntityManager entityManager;

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

		Query query = entityManager.createNamedQuery("Usuario.buscaPeloLogin");
		query.setParameter("login", login);
		UsuarioEntity usuario = (UsuarioEntity) query.getSingleResult();

		return buildUserForAuthentication(usuario, getAuthority(usuario.getAutorizacoes()));
	}

	/**
	 * Converts br.com.cams7.crud.entity.UsuarioEntity user to
	 * org.springframework.security.core.userdetails.User
	 * 
	 * @param usuario
	 * @param autorizacoes
	 * @return
	 */
	private User buildUserForAuthentication(UsuarioEntity usuario, Set<? extends GrantedAuthority> autorizacoes) {
		String login = usuario.getLogin();
		String senha = usuario.getSenha();
		boolean habilitado = usuario.getHabilitado();

		return new User(login, senha, habilitado, true, true, true, autorizacoes);
	}

	/**
	 * @param autorizacoes
	 * @return
	 */
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
