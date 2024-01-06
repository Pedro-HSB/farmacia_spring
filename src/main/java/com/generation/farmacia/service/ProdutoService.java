package com.generation.farmacia.service;

import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@Service
public class ProdutoService {

	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Optional<Produto> cadastrarProduto(Produto produto) {

		if (produtoRepository.findAllByNome(produto.getNome()).isPresent())
			return Optional.empty();

		return Optional.of(produtoRepository.save(produto));
	
	}

	public Optional<Produto> atualizarProduto(Produto produto) {
		
		if(produtoRepository.findById(produto.getId()).isPresent()) {

			Optional<Produto> buscaProduto = produtoRepository.findAllByNome(produto.getNome());

			if ( (buscaProduto.isPresent()) && ( buscaProduto.get().getId() != produto.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto já existe!", null);

			return Optional.ofNullable(produtoRepository.save(produto));
			
		}

		return Optional.empty();
	
	}	

}
