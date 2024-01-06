package com.generation.farmacia.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_produtos")
public class Produto {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	@NotBlank(message="O Atributo Nome  e Obrigatorio")
	@Size(min=5,max=100,message="o Nome  deve ser maior que 10")
	private String nome;
	
	@Column(length=100)
	@NotBlank(message="O Atributo Descricao e Obrigatorio")
	@Size(min=10,max=1000,message="o Descricao deve ser maior que 10")
	private String descricao;
	
	@Column(columnDefinition = "DECIMAL(5,2)")
	@DecimalMax("10000.00") 
	@DecimalMin("00.00") 
	private double preco;
	
	@Column(length=100)
    private Integer estoque;
	
	@NotNull(message = "O Atributo data de validade é Obrigatório!")
	@Column(name = "dt_nascimento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dt_vali;
	
	@ManyToOne
    @JsonIgnoreProperties("produtos")
    private Categoria categoria;
	
	

	public Produto(Long id,String nome,String descricao,double preco, Integer estoque,LocalDate dt_vali) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.dt_vali = dt_vali;
	}
	
	public Produto() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public LocalDate getDt_vali() {
		return dt_vali;
	}

	public void setDt_vali(LocalDate dt_vali) {
		this.dt_vali = dt_vali;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
		
}
