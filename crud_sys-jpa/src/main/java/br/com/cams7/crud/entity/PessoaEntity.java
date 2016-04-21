/**
 * 
 */
package br.com.cams7.crud.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cams7.sys.entity.AbstractEntity;
import br.com.cams7.sys.validator.CPF;

/**
 * Entidade pessoa
 * 
 * @author cesar
 *
 */
@Entity
@Table(name = "pessoa", uniqueConstraints = @UniqueConstraint(columnNames = "cpf"))
@NamedQueries({
		@NamedQuery(name = "Pessoa.buscaPessoasSemUsuarioPeloNome", query = "SELECT p FROM PessoaEntity p WHERE p.id NOT IN(SELECT p.id FROM UsuarioEntity u INNER JOIN u.pessoa p) AND LOWER(p.nome) LIKE :nome"),
		@NamedQuery(name = "Pessoa.buscaPeloNome", query = "SELECT p FROM PessoaEntity p WHERE LOWER(p.nome) LIKE :nome"),
		@NamedQuery(name = "Pessoa.buscaQtdCadastradaPeloCPF", query = "SELECT COUNT(p) FROM PessoaEntity p WHERE p.cpf=:cpf"),
		@NamedQuery(name = "Pessoa.buscaCpfPeloId", query = "SELECT p.cpf FROM PessoaEntity p WHERE p.id=:id") })

public class PessoaEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Long id;

	@Column
	@NotEmpty
	@Size(min = 3, max = 45)
	private String nome;

	@Column(length = 11)
	@NotEmpty
	@CPF
	private String cpf;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date nascimento;

	@OneToMany(mappedBy = "pessoa")
	private List<EnderecoEntity> enderecos;

	public PessoaEntity() {
		super();
	}

	public PessoaEntity(Long id) {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public List<EnderecoEntity> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

}
