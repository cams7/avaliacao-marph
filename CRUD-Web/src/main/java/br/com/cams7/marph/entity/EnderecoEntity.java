/**
 * 
 */
package br.com.cams7.marph.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author cesar
 *
 */
@Entity
@Table(name = "endereco")
public class EnderecoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "endereco_seq", sequenceName = "endereco_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_seq")
	@Column(name = "id_endereco")
	private Long id;

	@Column
	@NotEmpty
	@Size(min = 2, max = 100)
	private String rua;

	@Column
	@NotEmpty
	@Size(min = 3, max = 50)
	private String bairro;

	@Column
	@NotEmpty
	@Digits(fraction = 0, integer = 12)
	private String telefone;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
	private PessoaEntity pessoa;

	public EnderecoEntity() {
		super();
	}

	public EnderecoEntity(Long id) {
		this();
		setId(id);
	}

	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public PessoaEntity getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaEntity pessoa) {
		this.pessoa = pessoa;
	}

}