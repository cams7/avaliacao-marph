package br.com.cams7.app.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Classe de modelo que representa uma mercadoria.
 * 
 * <p>
 * A <code>mercadoria</code> é um objeto persistido no banco de dados, por isso
 * a classificamos como <strong>Entidade</strong>.
 * </p>
 * 
 * <p>
 * Por se tratar de uma Entidade persistente, a <code>Mercadoria</code> utiliza
 * as anotações <code>JPA</code> para definir o mapeamento <code>ORM</code>.
 * 
 * <p>
 * As funcionalidades desse sistema demonstração são concentradas no cadastro
 * (CRUD) de mercadorias.
 * </p>
 * 
 * <p>
 * A classe <code>AbstractPersistable</code> (Spring Data JPA) define uma
 * estrutura com ID da entidade, método equals e hashcode. O objetivo em
 * <i>enxugar</i> o mapeamento JPA.
 * </p>
 * 
 * <p>
 * Outra característica dessa classe, é o uso de anotações do Bean Validations
 * para validar o estado (dados) da <code>Mercadoria</code>. Bean Validations
 * (JSR 303) é uma especificação Java para habilitar a validação de dados via o
 * uso de anotações. O principal provider dessa API é o
 * <code>Hibernate Validator</code>.
 * </p>
 * 
 * @author YaW Tecnologia
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "mercadoria")
public class MercadoriaEntity implements Serializable {

	@Id
	@SequenceGenerator(name = "seq_mercadoria", sequenceName = "gen_mercadoria_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mercadoria")
	@Column(name = "id_mercadoria")
	private Integer id;

	@NotNull
	@Size(min = 5, max = 200)
	@Column(length = 200)
	private String nome;

	private String descricao;

	@NotNull
	@Min(value = 1)
	private Integer quantidade;

	@NotNull
	@Min(value = 1)
	private Float preco;

	private static final NumberFormat numberFmt = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

	public MercadoriaEntity() {
		super();
	}

	public MercadoriaEntity(Integer id, String nome, String descricao, Integer quantidade, Float preco) {
		this();

		setId(id);
		this.nome = nome;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getPrecoFormatado() {
		return convertPrecoToString(this.preco);
	}

	public static String convertPrecoToString(Float preco) {
		return numberFmt.format(preco);
	}

	public static float formatStringToPreco(String strPreco) throws ParseException {
		return numberFmt.parse(strPreco).floatValue();
	}

}
