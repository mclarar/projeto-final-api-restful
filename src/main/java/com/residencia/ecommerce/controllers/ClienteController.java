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

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.exception.CpfException;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clienteList = clienteService.findAll();
		if (clienteList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum cliente encontrado.");
		}
		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		if (null == cliente) {
			throw new NoSuchElementFoundException("Não foi encontrado cliente com o id " + id);
		} else {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
	}
	
	/*@GetMapping("/{cpf}")
	public ResponseEntity<Cliente> findByCPF(@PathVariable String cpf) {
		Cliente cliente = clienteService.findByCPF(cpf);
		if (null == cliente) {
			throw new NoSuchElementFoundException("Não foi encontrado cliente com o CPF " + cpf);
		} else {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
	}*/

	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.save(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente, Integer id) {
		if (clienteService.findById(cliente.getIdCliente()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O Cliente de id = " + cliente.getIdCliente() + " não foi encontrado.");
		}
		return new ResponseEntity<>(clienteService.update(cliente, id), HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		if (clienteService.findById(id) == null) {
			throw new NoSuchElementFoundException("Não foi encontrado cliente com o id " + id);
		}
		clienteService.delete(id);
		return new ResponseEntity<>("o CLiente de ID " + id + "foi excluido com sucesso", HttpStatus.OK);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<ClienteDTO> saveDTO(@RequestBody ClienteDTO cliente) {
		ClienteDTO novoCliente = clienteService.saveDTO(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}
}