/**
 * 
 */
package br.com.cams7.marph.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import br.com.cams7.app.entity.AbstractEntity;

/**
 * @author cesar
 *
 */
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "login") )
@NamedQueries({
		@NamedQuery(name = "Usuario.buscaPorLogin", query = "SELECT u FROM UsuarioEntity u JOIN FETCH u.authorities WHERE u.login=:login"),
		@NamedQuery(name = "Usuario.buscaTodosDadosPessoais", query = "SELECT u FROM UsuarioEntity u JOIN FETCH u.pessoa p")})
public class UsuarioEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	// @SequenceGenerator(name = "usuario_seq", sequenceName = "usuario_seq",
	// initialValue = 1, allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "usuario_seq")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;

	@Column
	@NotEmpty
	@Size(min = 3, max = 30)
	private String login;

	@Column
	@NotEmpty
	@Size(min = 10, max = 80)
	private String senha;

	@Column(name = "status")
	@NotNull
	private Boolean habilitado;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
	private PessoaEntity pessoa;

	@Column(name = "autorizacao")
	@ElementCollection(targetClass = Role.class, fetch = FetchType.LAZY)
	@CollectionTable(name = "usuario_autorizacao", joinColumns = @JoinColumn(name = "id_usuario") )
	private Set<Role> authorities;

	public UsuarioEntity() {
		super();
		setHabilitado(Boolean.FALSE);
	}

	public UsuarioEntity(Long id) {
		super(id);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public Set<Role> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Role> authorities) {
		this.authorities = authorities;
	}

	public enum Role implements GrantedAuthority {
		ROLE_USER, ROLE_NEWUSER, ROLE_ADMIN;

		@Override
		public String getAuthority() {
			return toString();
		}
	}

}
