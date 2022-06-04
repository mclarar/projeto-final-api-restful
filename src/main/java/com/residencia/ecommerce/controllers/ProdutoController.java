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

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produtoList = produtoService.findAll();

		if (produtoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum produto encontrado.");
		}
		return new ResponseEntity<>(produtoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		if (produto == null) {
			throw new NoSuchElementFoundException("O Produto de id = " + id + " não foi encontrado.");
		}

		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto novoProduto = produtoService.save(produto);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto, Integer id) {
		if (produtoService.findById(produto.getIdProduto()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O Produto de id = " + produto.getIdProduto() + " não foi encontrado.");
		}

		return new ResponseEntity<>(produtoService.update(produto, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		if (produtoService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. O Produto de id = " + id + " não foi encontrado.");
		}

		produtoService.delete(id);
		return new ResponseEntity<>("O Produto de id = " + id + " foi excluído com sucesso.", HttpStatus.OK);

	}


	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id) {
		Produto produto = produtoService.findById(id);
		return new ResponseEntity<>(produto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Produto> save(@RequestBody Produto produto) {
		Produto novoProduto = produtoService.save(produto);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto, Integer id) {
		Produto novoProduto = produtoService.update(produto, id);
		return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		produtoService.delete(id);
		return new ResponseEntity<>("", HttpStatus.OK);

	}
	
	//DTO AREA
	
	@PostMapping("/dto")
	public ResponseEntity<ProdutoDTO> saveProdutoDTO(@RequestBody ProdutoDTO produtoDTO) {
		return new ResponseEntity<>(produtoService.saveProdutoDTO(produtoDTO), HttpStatus.CREATED);
	}
	

}
