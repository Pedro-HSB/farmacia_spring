package com.generation.farmacia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=100)
	@NotBlank(message="O Atributo Nome  e Obrigatorio")
	@Size(min=10,max=100,message="o Nome  deve ser maior que 10")
	private String nome;
	
	@Column(length=100)
	@NotBlank(message="O Atributo Descricao e Obrigatorio")
	@Size(min=10,max=1000,message="o Descricao deve ser maior que 10")
	private String descricao;
	
	@Column(length=100)
	@NotBlank(message="O Atributo Fornecedor e Obrigatorio")
	@Size(min=10,max=1000,message="o Fornecedor deve ser maior que 10")
	private String fornecedor ;
	
	@Column(length=100)
    private Integer estoque;
	
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

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

}
