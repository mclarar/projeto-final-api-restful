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

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@Operation(summary = "Resgata todos os produtos.", description = "Resgata todos os produtos.", responses = {
			@ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Os produtos não foram encontrados. :( "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produtoList = produtoService.findAll();

		if (produtoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum produto encontrado.");
		}
		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}

	@Operation(summary = "Resgata o produto pelo seu ID", description = "Informe o ID do produto para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe produto com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		if (produto == null) {
			throw new NoSuchElementFoundException("O Produto de id = " + id + " não foi encontrado.");
		}

		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@Operation(summary = "Insere um produto na base de dados", description = "Informe os dados requisitados no corpo no JSON para adicionar um novo produto.", responses = {
			@ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse prouto não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto novoProduto = produtoService.save(produto);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);

	}

	@Operation(summary = "Atualiza um produto na base de dados", description = "Informe os dados requisitados no corpo no JSON para atualizar um produto.", responses = {
			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse produto não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto, Integer id) {
		if (produtoService.findById(produto.getIdProduto()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O Produto de id = " + produto.getIdProduto() + " não foi encontrado.");
		}

		return new ResponseEntity<>(produtoService.update(produto, id), HttpStatus.OK);
	}

	@Operation(summary = "Deleta um pedido na base de dados", description = "Informe o ID na url para deletar um pedido.", responses = {
			@ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não tem como deletar algo que não existe :( tente novamente."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		if (produtoService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. O Produto de id = " + id + " não foi encontrado.");
		}

		produtoService.delete(id);
		return new ResponseEntity<>("O Produto de id = " + id + " foi excluído com sucesso.", HttpStatus.OK);

	}

	// DTO AREA

	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProdutoDTO(produtoDTO), HttpStatus.CREATED);
	}

}
