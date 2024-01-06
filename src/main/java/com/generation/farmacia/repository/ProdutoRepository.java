package com.generation.farmacia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.farmacia.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long>{

	List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
	List<Produto> findByPrecoLessThan(@Param("preco")double preco);
	List<Produto> findByPrecoGreaterThan(@Param("preco")double preco);
	Optional<Produto> findAllByNome(@Param("nome")String nome);
}
