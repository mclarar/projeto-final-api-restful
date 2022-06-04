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

import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categoriaList = categoriaService.findAll();
		if (categoriaList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhuma categoria encontrada.");
		}
		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findById(id);
		if (categoria == null) {
			throw new NoSuchElementFoundException("A Categoria de id " + id + " não foi encontrada.");
		}
		return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) {
		Categoria novoCategoria = categoriaService.save(categoria);
		return new ResponseEntity<>(novoCategoria, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Categoria> update(@RequestBody Categoria categoria, Integer id) {
		if (categoriaService.findById(categoria.getIdCategoria()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. A Categoria de id " + categoria.getIdCategoria() + " não foi encontrada.");
		}

		return new ResponseEntity<>(categoriaService.update(categoria, id), HttpStatus.OK);

	}

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
}
