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

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.exception.NoSuchElementFoundException;
import com.residencia.ecommerce.service.ItemPedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

	@Autowired
	ItemPedidoService itemPedidoService;

	@Operation(summary = "Resgata todos os itens pedidos.", description = "Resgata todos os itens pedidos.", responses = {
			@ApiResponse(responseCode = "200", description = "itens encontrados com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Os itens não foram encontrados. :( "),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping
	public ResponseEntity<List<ItemPedido>> findAll() {
		List<ItemPedido> itemPedidoList = itemPedidoService.findAll();
		if (itemPedidoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum item do pedido foi encontrado.");
		}
		return new ResponseEntity<>(itemPedidoList, HttpStatus.OK);
	}

	@Operation(summary = "Resgata o item pedido pelo seu ID", description = "Informe o ID do item para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Item encontrado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não existe item com esse ID :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, da uma olhadinha no código ;-;") })
	@GetMapping("/{id}")
	public ResponseEntity<ItemPedido> findById(@PathVariable Integer id) {
		ItemPedido itemPedido = itemPedidoService.findById(id);
		if (itemPedido == null) {
			throw new NoSuchElementFoundException("O item do pedido de id " + id + " não foi encontrado.");
		}
		return new ResponseEntity<>(itemPedido, HttpStatus.OK);
	}

	@Operation(summary = "Insere um item pedido na base de dados", description = "Informe os dados requisitados no corpo no JSON para adicionar um novo item.", responses = {
			@ApiResponse(responseCode = "200", description = "Item adicionado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse item não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PostMapping
	public ResponseEntity<ItemPedido> save(@RequestBody @Valid ItemPedido itemPedido) {
		ItemPedido novoItemPedido = itemPedidoService.save(itemPedido);
		return new ResponseEntity<>(novoItemPedido, HttpStatus.CREATED);
	}

	@Operation(summary = "Atualiza um item pedido na base de dados", description = "Informe os dados requisitados no corpo no JSON para atualizar um item.", responses = {
			@ApiResponse(responseCode = "200", description = "item atualizado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Esse item não existe :("),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@PutMapping
	public ResponseEntity<ItemPedido> update(@RequestBody ItemPedido itemPedido, Integer id) {
		if (itemPedidoService.findById(itemPedido.getIdItemPedido()) == null) {
			throw new NoSuchElementFoundException("Não foi possível atualizar. item do pedido id  "
					+ itemPedido.getIdItemPedido() + " não foi encontrado.");
		}
		return new ResponseEntity<>(itemPedidoService.update(itemPedido, id), HttpStatus.OK);
	}

	@Operation(summary = "Deleta um item pedido na base de dados", description = "Informe o ID na url para deletar um item pedido.", responses = {
			@ApiResponse(responseCode = "200", description = "Item pedido deletado com sucesso :)"),
			@ApiResponse(responseCode = "400", description = "Informação invalida :o"),
			@ApiResponse(responseCode = "404", description = "Não tem como deletar algo que não existe :( tente novamente."),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para isso, meu consagrado :("),
			@ApiResponse(responseCode = "500", description = "Vixe! quinhentão, dá uma olhadinha no código ;-;") })
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		if (itemPedidoService.findById(id) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível excluir. Item do pedido id " + id + " não foi encontrado.");
		}

		itemPedidoService.delete(id);
		return new ResponseEntity<>("Item do pedido = " + id + " foi excluído com sucesso.", HttpStatus.OK);

	}
	
	@PostMapping("/dto")
	public ResponseEntity<ItemPedidoDTO> saveDTO(@RequestBody ItemPedidoDTO itemPedidoDTO) {
		ItemPedidoDTO novoItemPedidoDTO = itemPedidoService.saveDTO(itemPedidoDTO);
		return new ResponseEntity<>(novoItemPedidoDTO, HttpStatus.CREATED);
	}
}
