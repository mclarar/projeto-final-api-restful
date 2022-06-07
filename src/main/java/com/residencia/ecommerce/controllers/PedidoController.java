package com.residencia.ecommerce.controllers;

import java.util.List;

import javax.mail.MessagingException;

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

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> pedidoList = pedidoService.findAll();

		if (pedidoList.isEmpty()) {
			throw new NoSuchElementFoundException("Nenhum Pedido encontrado.");
		}
		return new ResponseEntity<>(pedidoList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
		Pedido pedido = pedidoService.findById(id);
		if (pedido == null) {
			throw new NoSuchElementFoundException("O Pedido de id " + id + " não foi encontrado.");
		}
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) throws Exception {
		Pedido novoPedido = pedidoService.save(pedido);
		return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Pedido> update(@RequestBody Pedido pedido, Integer id) {
		if (pedidoService.findById(pedido.getIdPedido()) == null) {
			throw new NoSuchElementFoundException(
					"Não foi possível atualizar. O Pedido de id " + pedido.getIdPedido() + " não foi encontrado.");
		}
		return new ResponseEntity<>(pedidoService.update(pedido, id), HttpStatus.OK);
	}

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
	public ResponseEntity<PedidoDTO> savePedidoDTO(@RequestBody PedidoDTO pedidoDTO) throws MessagingException {
		return new ResponseEntity<>(pedidoService.savePedidoDTO(pedidoDTO), HttpStatus.CREATED);

	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<PedidoDTO> findDTOById(@PathVariable Integer id) {
		PedidoDTO pedido = pedidoService.findDTOById(id);
		if (pedido == null) {
			throw new NoSuchElementFoundException("O Pedido de id " + id + " não foi encontrado.");
		}
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}
}