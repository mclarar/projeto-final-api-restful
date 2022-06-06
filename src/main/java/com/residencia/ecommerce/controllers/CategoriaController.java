package com.residencia.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.CategoriaService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/categoria")
@OpenAPIDefinition(info = @Info(title = "API Ecommerce", description = "API ecommerce feita para disciplina Desenvolvimento de API Restful da Residência em TIC Software do Serratec 2022.1", version = "1.000", contact = @Contact(name = "Grupo 01", url = "http://serratec.org/", email = "grupo01.serratec.turma01@gmail.com")))
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@Operation(summary = "Resgata todos as categorias", description = "resgata todas as categorias", responses = {
			@ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe categoria com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categoriaList = categoriaService.findAll();
		if (categoriaList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhuma categoria encontrada.");
		}
		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	@Operation(summary = "Resgata a categoria pelo seu ID", description = "Informe o ID da categoria para obter as informações sobre ela.", responses = {
			@ApiResponse(responseCode = "200", description = "Categoria encontrada com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe categoria com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findById(id);
		if (categoria == null) {
			throw new NoSuchElementFoundException("A Categoria de id " + id + " não foi encontrada.");
		}
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	@Operation(summary = "Insere uma categoria na base de dados", description = "Informe os dados requisitados no corpo no JSON para adicionar uma nova categoria.", responses = {
			@ApiResponse(responseCode = "200", description = "Categoria adicionada com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Essa categoria não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria novoCategoria = categoriaService.save(categoria);
		return new ResponseEntity<>(novoCategoria, HttpStatus.CREATED);

	}

	@Operation(summary = "Atualiza uma categoria na base de dados", description = "Informe os dados requisitados no corpo no JSON para atualizar uma categoria.", responses = {
			@ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Essa categoria não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PutMapping
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, Integer id) {
		if (categoriaService.findById(categoria.getIdCategoria()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar. A Categoria de id "
					+ categoria.getIdCategoria() + " não foi encontrada.");
		}

		return new ResponseEntity<>(categoriaService.update(categoria, id), HttpStatus.OK);

	}

	@Operation(summary = "Deleta uma categoria na base de dados", description = "Informe o ID na url para deletar uma categoria.", responses = {
			@ApiResponse(responseCode = "200", description = "Categoria deletada com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não tem como deletar algo que não existe :( tente novamente."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		if (categoriaService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir.A Categoria de id " + id + " não foi encontrada.");
		}
		categoriaService.delete(id);
		return new ResponseEntity<>("A Categoria de id " + id + " foi excluída com sucesso.", HttpStatus.OK);

	}
	
	@PostMapping("/dto")
	public ResponseEntity<CategoriaDTO> saveDTO(@RequestBody CategoriaDTO categoriaDTO) {
		CategoriaDTO novoCategoria = categoriaService.saveDTO(categoriaDTO);
		return new ResponseEntity<>(novoCategoria, HttpStatus.CREATED);

	}
}
