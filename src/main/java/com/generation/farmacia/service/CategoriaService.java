package com.generation.farmacia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Categoria> cadastrarCategoria(Categoria categoria) {

		if (categoriaRepository.findAllByNome(categoria.getNome()).isPresent())
			return Optional.empty();

		return Optional.of(categoriaRepository.save(categoria));
	
	}

	public Optional<Categoria> atualizarCategoria(Categoria categoria) {
		
		if(categoriaRepository.findById(categoria.getId()).isPresent()) {

			Optional<Categoria> buscaCategoria = categoriaRepository.findAllByNome(categoria.getNome());

			if ( (buscaCategoria.isPresent()) && ( buscaCategoria.get().getId() != categoria.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoria já existe!", null);

			return Optional.ofNullable(categoriaRepository.save(categoria));
			
		}

		return Optional.empty();
	
	}	
}
