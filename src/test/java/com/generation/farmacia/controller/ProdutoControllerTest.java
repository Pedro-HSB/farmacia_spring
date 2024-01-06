package com.generation.farmacia.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;
import com.generation.farmacia.service.ProdutoService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProdutoControllerTest {
	
	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoService produtoService;

	@Test
	@DisplayName("Cadastrar Produtos")
	public void deveCriarProduto() {

		// Corpo da requisição
		HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(
				new Produto(0L, "Analgésico Forte", "Alívio rápido para dores intensas", 15.99, 750, LocalDate.now()));
		// Requisição HTTP
		ResponseEntity<Produto> corpoResposta = testRestTemplate
				.exchange("/produtos", HttpMethod.POST,
				corpoRequisicao, Produto.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("😀 Deve Atualizar Produtos")
	public void deveAtualizarProduto() {
		
		Optional<Produto> produtoCadastrado = produtoService.cadastrarProduto(
				new Produto(0L, "produto 3", " descricao3", 103.00,103, LocalDate.now()));
		
		/* Corpo da Requisição */
		HttpEntity<Produto> corpoRequisicao = new HttpEntity<Produto>(
				new Produto( 
				produtoCadastrado.get().getId(),"Loção Pós-Sol", "Hidratação e alívio para pele após exposição solar", 22.49, 100, LocalDate.now()));
		
		/* Requisição HTTP */
		
		ResponseEntity<Produto> corpoResposta = testRestTemplate
				.exchange("/produtos", HttpMethod.PUT, corpoRequisicao, Produto.class);
		
		/* Verifica o HTTP Status Code */
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		
		
	}

	@Test
	@DisplayName("Deve Listar Todos Produtos")
	public void deveListarTodosProdutos() {

		produtoService.cadastrarProduto(
				new Produto(0L, "Xarope para Tosse Infantil", "Alívio da tosse em crianças", 9.99, 520, LocalDate.now()));

		produtoService.cadastrarProduto(
				new Produto(0L, "Kit de Escova de Dentes e Creme Dental", "Combo para higiene bucal", 17.75, 820, LocalDate.now()));



		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/produtos", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Deve Buscar Produtos Por ID")
	public void deveBuscarProdutoPorId() {

		Optional<Produto> produtoCadastrado = produtoService
				.cadastrarProduto(
						new Produto(2005L, "Shampoo Revitalizante", "Restaura a vitalidade dos cabelos", 13.49, 680, LocalDate.now()));

		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/produtos/" + produtoCadastrado.get().getId(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Deve Buscar Produtos Por Nome")
	public void deveBuscarProdutoPorNome() {

		Optional<Produto> produtoCadastrado = produtoService
				.cadastrarProduto(
						new Produto(0L, "Comprimidos Mastigáveis de Vitamina D", "Suplemento para ossos saudáveis", 18.99, 950, LocalDate.now()));


		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/produtos/nome/" + produtoCadastrado.get().getNome(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Deve Buscar Produtos Por Menor Preco")
	public void deveBuscarProdutoPorMenorPreco() {

		Optional<Produto> produtoCadastrado = produtoService
				.cadastrarProduto(
						new Produto(0L, "Creme Anti-Envelhecimento", "Reduz linhas finas e rugas", 29.99, 150, LocalDate.now()));



		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/produtos/menorpreco/" + produtoCadastrado.get().getPreco(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Deve Buscar Produtos Por Maior Preco")
	public void deveBuscarProdutoPorMaiorPreco() {

		Optional<Produto> produtoCadastrado = produtoService
				.cadastrarProduto(
						new Produto(0L, "Comprimidos de Melatonina", "Auxilia na regulação do sono", 12.49, 580, LocalDate.now()));



		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/produtos/maiorpreco/" + produtoCadastrado.get().getPreco(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}

}
