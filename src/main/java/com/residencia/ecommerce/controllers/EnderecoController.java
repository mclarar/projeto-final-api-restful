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

import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> enderecoList = enderecoService.findAll();
		if (enderecoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum endereco encontrado.");
		}
		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id) {
		Endereco endereco = enderecoService.findById(id);
		if (endereco == null) {
			throw new NoSuchElementFoundException("O endereco de id " + id + " não foi encontrado.");
		}
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
		Endereco novoEndereco = enderecoService.save(endereco);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);

	}

	@PutMapping
	public ResponseEntity<Endereco> update(@RequestBody Endereco endereco, Integer id) {
		if (enderecoService.findById(endereco.getIdEndereco()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O endereco de id " + endereco.getIdEndereco() + " não foi encontrado.");
		}

		return new ResponseEntity<>(enderecoService.update(endereco, id), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		if (enderecoService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. O endereco de id " + id + " não foi encontrado.");
		}
		enderecoService.delete(id);
		return new ResponseEntity<>(" O endereco de id " + id + " foi excluído com sucesso.", HttpStatus.OK);
	}
}
