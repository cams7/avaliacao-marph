/**
 * 
 */
package br.com.cams7.marph.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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

import br.com.cams7.app.entity.AbstractEntity;

/**
 * Entidade usuario
 * 
 * @author cesar
 *
 */
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "login"))
@NamedQueries({
		@NamedQuery(name = "Usuario.buscaPeloLogin", query = "SELECT u FROM UsuarioEntity u WHERE u.login=:login"),
		@NamedQuery(name = "Usuario.buscaTodosDadosPessoais", query = "SELECT u FROM UsuarioEntity u JOIN FETCH u.pessoa") })
public class UsuarioEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private Long id;

	@Column
	@NotEmpty
	@Size(min = 3, max = 30)
	private String login;

	@Column(length = 80, nullable = false)
	private String senha;

	@Column(name = "status")
	@NotNull
	private Boolean habilitado;

	@Column(name = "autorizacoes", length = 50, nullable = true)
	private String stringAutorizacoes;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
	private PessoaEntity pessoa;

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

	public String getStringAutorizacoes() {
		return stringAutorizacoes;
	}

	public void setStringAutorizacoes(String stringAutorizacoes) {
		this.stringAutorizacoes = stringAutorizacoes;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

	public Set<Autorizacao> getAutorizacoes() {
		if (getStringAutorizacoes() == null)
			return new HashSet<>();

		Set<Autorizacao> autorizacoes = new HashSet<Autorizacao>();

		for (String value : getStringAutorizacoes().split("-")) {
			Autorizacao autorizacao = Autorizacao.valueOf(value);
			autorizacoes.add(autorizacao);
		}

		return autorizacoes;
	}

	public void setAutorizacoes(Set<Autorizacao> autorizacoes) {
		if (autorizacoes != null && !autorizacoes.isEmpty()) {
			int totalAutorizacoes = autorizacoes.size();
			StringBuffer stringAutorizacoes = new StringBuffer();
			int count = 0;

			for (Autorizacao autorizacao : autorizacoes) {
				stringAutorizacoes.append(autorizacao.name());
				if (count != totalAutorizacoes - 1)
					stringAutorizacoes.append("-");
				count++;
			}

			setStringAutorizacoes(stringAutorizacoes.toString());
		} else
			setStringAutorizacoes(null);
	}

	public enum Autorizacao {
		CLIENTE, SECRETARIO, ADMINISTRADOR
	}

}
