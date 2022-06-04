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

import com.residencia.ecommerce.dto.CepDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.service.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> enderecoList = enderecoService.findAll();
		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id) {
		Endereco endereco = enderecoService.findById(id);
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@GetMapping("/cep/{cep}")
	public ResponseEntity<CepDTO> consultarCep(@PathVariable String cep) {
		CepDTO cepDTO = enderecoService.consultarCepDTO(cep);
		 //if(null == cepDTO.getCep())
		 //throw new NoSuchElementFoundException("Não foi encontrado dados para esse CEP"+ cep);
		 //else
		return new ResponseEntity<>(cepDTO, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) {
		Endereco novoEndereco = enderecoService.save(endereco);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
	}
	
	@PostMapping("/cep/{cep}")
    public ResponseEntity<EnderecoDTO> saveCep(@PathVariable String cep) {
        return new ResponseEntity<>(enderecoService.saveCep(cep), HttpStatus.CREATED);
    }

	@PutMapping
	public ResponseEntity<Endereco> update(@RequestBody Endereco endereco, Integer id) {
		Endereco novoEndereco = enderecoService.update(endereco, id);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		enderecoService.delete(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
