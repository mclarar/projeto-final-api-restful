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

import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.service.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

@Autowired
PedidoService pedidoService;

@GetMapping
public ResponseEntity<List<Pedido>> findAll(){
List<Pedido>pedidoList = pedidoService.findAll();

return new ResponseEntity <>(pedidoList,HttpStatus.OK);

}

@GetMapping("/{id}")
public ResponseEntity<Pedido>findById(@PathVariable Integer id){
	Pedido pedido = pedidoService.findById(id);
	return new ResponseEntity <>(pedido,HttpStatus.OK);
}
@PostMapping 
public ResponseEntity<Pedido>save(@RequestBody Pedido pedido){
	Pedido novoPedido = pedidoService.save(pedido);
		return new ResponseEntity <>(novoPedido,HttpStatus.CREATED);
		
		}
@PutMapping 
public ResponseEntity<Pedido>update(@RequestBody Pedido pedido,Integer id){
	Pedido novoPedido = pedidoService.update(pedido,id);
	return new ResponseEntity <>(novoPedido,HttpStatus.CREATED);

}
@DeleteMapping("/{id}")
public ResponseEntity<String>delete(@PathVariable Integer id){
	pedidoService.delete(id);
	return new ResponseEntity<>("",HttpStatus.OK);
			
}
}


