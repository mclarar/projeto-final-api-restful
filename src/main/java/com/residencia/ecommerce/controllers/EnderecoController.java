package com.residencia.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

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
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@Operation(summary = "Resgata todos os endereços.", description = "Resgata todos os endereços.", responses = {
			@ApiResponse(responseCode = "200", description = "Endereços encontrados com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Os endereços não foram encontrados. :( "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> enderecoList = enderecoService.findAll();

		if (enderecoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum endereco encontrado.");
		}

		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	@Operation(summary = "Resgata o endereço pelo seu ID", description = "Informe o ID do endereço para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe endereço com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id) {
		Endereco endereco = enderecoService.findById(id);
		if (endereco == null) {
			throw new NoSuchElementFoundException("O endereco de id " + id + " não foi encontrado.");
		}
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@Operation(summary = "Insere um endereço na base de dados", description = "Informe os dados requisitados no corpo no JSON para adicionar um novo endereço.", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço adicionado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse endereço não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PostMapping
	public ResponseEntity<Endereco> save(@RequestBody @Valid Endereco endereco) {
		Endereco novoEndereco = enderecoService.save(endereco);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);

	}

	@Operation(summary = "Atualiza um endereço na base de dados", description = "Informe os dados requisitados no corpo no JSON para atualizar um endereço.", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse endereço não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PutMapping("/{id}")
	public ResponseEntity<Endereco> update(@PathVariable (value = "id") Integer id, @RequestBody Endereco endereco) {
		if (enderecoService.findById(endereco.getIdEndereco()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar. O endereco de id "
					+ endereco.getIdEndereco() + " não foi encontrado.");
		}

		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}

	@Operation(summary = "Resgata o endereço pelo seu CEP", description = "Informe o CEP do endereço para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe endereço com esse CEP :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping("/cep/{cep}")
	public ResponseEntity<CepDTO> consultarCep(@PathVariable String cep) {
		CepDTO cepDTO = enderecoService.consultarCepDTO(cep);
		// if(null == cepDTO.getCep())
		// throw new NoSuchElementFoundException("Não foi encontrado dados para esse CEP
		// " + cep);
		// else
		return new ResponseEntity<>(cepDTO, HttpStatus.OK);
	}

	@Operation(summary = "Deleta um endereço na base de dados", description = "Informe o ID na url para deletar um endereço.", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço deletado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não tem como deletar algo que não existe :( tente novamente."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable (value = "id")  Integer id) {

		if (enderecoService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. O endereco de id " + id + " não foi encontrado.");
		}
		enderecoService.delete(id);
		return new ResponseEntity<>(" O endereco de id " + id + " foi excluído com sucesso.", HttpStatus.OK);

	}
}