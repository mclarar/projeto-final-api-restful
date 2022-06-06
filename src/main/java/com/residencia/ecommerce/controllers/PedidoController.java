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

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@Operation(summary = "Resgata todos os pedidos.", description = "Resgata todos os pedidos.", responses = {
			@ApiResponse(responseCode = "200", description = "Pedidos encontrados com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Os pedidos não foram encontrados. :( "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> pedidoList = pedidoService.findAll();

		if (pedidoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum Pedido encontrado.");
		}
		return new ResponseEntity<>(pedidoList, HttpStatus.OK);
	}

	@Operation(summary = "Resgata o pedido pelo seu ID", description = "Informe o ID do pedido para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Pedido encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe pedido com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findById(id);
		if (pedido == null) {
			throw new NoSuchElementFoundException("O Pedido de id " + id + " não foi encontrado.");
		}
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@Operation(summary = "Insere um pedido na base de dados", description = "Informe os dados requisitados no corpo no JSON para adicionar um novo pedido.", responses = {
			@ApiResponse(responseCode = "200", description = "Pedido adicionado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse pedido não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PostMapping
	public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) throws Exception {
		Pedido novoPedido = pedidoService.save(pedido);
		return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualiza um pedido na base de dados", description = "Informe os dados requisitados no corpo no JSON para atualizar um pedido.", responses = {
			@ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse pedido não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PutMapping
	public ResponseEntity<Pedido> update(@RequestBody Pedido pedido, Integer id) {
		if (pedidoService.findById(pedido.getIdPedido()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O Pedido de id " + pedido.getIdPedido() + " não foi encontrado.");
		}
		return new ResponseEntity<>(pedidoService.update(pedido, id), HttpStatus.OK);
	}

	@Operation(summary = "Deleta um pedido na base de dados", description = "Informe o ID na url para deletar um pedido.", responses = {
			@ApiResponse(responseCode = "200", description = "Pedido deletado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não tem como deletar algo que não existe :( tente novamente."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		if (pedidoService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. O Pedido de id " + id + " não foi encontrado.");
		}
		pedidoService.delete(id);
		return new ResponseEntity<>("O Pedido de id " + id + " foi excluído com sucesso.", HttpStatus.OK);

	}

	// DTO AREA

	@PostMapping("/dto")
	public ResponseEntity<PedidoDTO> savePedidoDTO(@RequestBody PedidoDTO pedidoDTO) {
		return new ResponseEntity<>(pedidoService.savePedidoDTO(pedidoDTO), HttpStatus.CREATED);

	}
}