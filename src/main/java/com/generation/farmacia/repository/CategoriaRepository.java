package com.generation.farmacia.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.farmacia.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Categoria> findAllByNomeContainingIgnoreCase(@Param("Nome")String Nome);
	
	List<Categoria> findAllByDescricaoContainingIgnoreCase(@Param("Descricao")String Descricao);

	Optional<Categoria> findAllByNome(@Param("Nome")String Nome);
	
}
