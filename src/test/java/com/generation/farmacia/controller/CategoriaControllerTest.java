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

import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.model.Categoria;
import com.generation.farmacia.repository.CategoriaRepository;
import com.generation.farmacia.service.CategoriaService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {

	
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;

	@Test
	@DisplayName("Cadastrar Categorias")
	public void deveCriarCategoria() {

		// Corpo da requisição
		HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(
				new Categoria(0L, "Suplementos Alimentares", "Suplementos para nutrição", "NutriVibe Inc.", 40));

		// Requisição HTTP
		ResponseEntity<Categoria> corpoResposta = testRestTemplate
				.exchange("/categorias", HttpMethod.POST,
				corpoRequisicao, Categoria.class);

		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("😀 Deve Atualizar Categorias")
	public void deveAtualizarCategoria() {
		
		Optional<Categoria> categoriaCadastrado = categoriaService.cadastrarCategoria(
				new Categoria(0L, "Cuidados com a Pele", "Categorias para a saúde da pele", "SkinCare Solutions", 20));
		
		/* Corpo da Requisição */
		HttpEntity<Categoria> corpoRequisicao = new HttpEntity<Categoria>(
				new Categoria( 
				categoriaCadastrado.get().getId(),"Medicamentos de Venda","Categorias sem prescrição ","ABC Pharmace", 50));
		
		/* Requisição HTTP */
		
		ResponseEntity<Categoria> corpoResposta = testRestTemplate
				.exchange("/categorias", HttpMethod.PUT, corpoRequisicao, Categoria.class);
		
		/* Verifica o HTTP Status Code */
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		
		
	}

	@Test
	@DisplayName("Deve Listar Todos Categorias")
	public void deveListarTodosCategorias() {

		categoriaService.cadastrarCategoria(
				new Categoria(0L, "Acessórios para Bebês", "Categorias para cuidados com bebês", "BabyJoy Ltd.", 25));
		categoriaService.cadastrarCategoria(
				new Categoria(0L, "Categorias de Limpeza Doméstica", "Itens para limpeza da casa", "CleanHome Supplies", 35));


		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/categorias", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Deve Buscar Categorias Por ID")
	public void deveBuscarCategoriaPorId() {

		Optional<Categoria> categoriaCadastrado = categoriaService
				.cadastrarCategoria(
						new Categoria(0L, "Categorias de Higiene Pessoal", "Itens para cuidados pessoais", "CleanCare Ltd.", 30));

		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/categorias/" + categoriaCadastrado.get().getId(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Deve Buscar Categorias Por Descricao")
	public void deveBuscarCategoriaPorDescricao() {

		Optional<Categoria> produtoCadastrado = categoriaService
				.cadastrarCategoria(
						new Categoria(0L, "Produtos de Cuidados Capilares", "Shampoos, condicionadores, e tratamentos", "HairCare Ltd.", 30));



		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/categorias/Descricao/" + produtoCadastrado.get().getDescricao(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}
	
	@Test
	@DisplayName("Deve Buscar Categorias Por Nome")
	public void deveBuscarCategoriaPorNome() {

		Optional<Categoria> produtoCadastrado = categoriaService
				.cadastrarCategoria(
						new Categoria(107L, "Suplementos para Atletas", "Produtos para nutrição esportiva", "SportsNutri Inc.", 40));


		// Requisição HTTP
		ResponseEntity<String> corpoResposta = testRestTemplate
				.exchange("/categorias/Nome/" + produtoCadastrado.get().getNome(), HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}

}
