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

import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Operation(summary = "Resgata todos os clientes.", description = "Resgata todos os clientes.", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Os clientes não foram encontrados. :( Pra onde será que foram?"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		List<Cliente> clienteList = clienteService.findAll();
		if (clienteList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum cliente encontrado.");
		}
		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	@Operation(summary = "Resgata o cliente pelo seu ID", description = "Informe o ID do cliente para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe cliente com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		if (null == cliente) {
			throw new NoSuchElementFoundException("Não foi encontrado cliente com o id " + id);
		} else {
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
	}

	@Operation(summary = "Insere um cliente na base de dados", description = "Informe os dados requisitados no corpo no JSON para adicionar um novo cliente.", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente adicionada com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse cliente não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
		Cliente novoCliente = clienteService.save(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualiza um cliente na base de dados", description = "Informe os dados requisitados no corpo no JSON para atualizar um cliente.", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse cliente não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PutMapping
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente, Integer id) {
		if (clienteService.findById(cliente.getIdCliente()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O Cliente de id = " + cliente.getIdCliente() + " não foi encontrado.");
		}
		return new ResponseEntity<>(clienteService.update(cliente, id), HttpStatus.CREATED);

	}

	@Operation(summary = "Deleta um cliente na base de dados", description = "Informe o ID na url para deletar um cliente.", responses = {
			@ApiResponse(responseCode = "200", description = "Cliente deletado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não tem como deletar algo que não existe :( tente novamente."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		if (clienteService.findById(id) == null) {
			throw new NoSuchElementFoundException("Não foi encontrado cliente com o id " + id);
		}
		clienteService.delete(id);
		return new ResponseEntity<>("o CLiente de ID " + id + "foi excluido com sucesso", HttpStatus.OK);
	}
}