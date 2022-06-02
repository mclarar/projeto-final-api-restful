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

import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.service.ItemPedidoService;

@RestController
@RequestMapping("/itemPedido")
public class ItemPedidoController {

@Autowired
ItemPedidoService itemPedidoService;

@GetMapping
public ResponseEntity<List<ItemPedido>> findAll(){
List<ItemPedido>itemPedidoList = itemPedidoService.findAll();

return new ResponseEntity <>(itemPedidoList,HttpStatus.OK);

}

@GetMapping("/{id}")
public ResponseEntity<ItemPedido>findById(@PathVariable Integer id){
	ItemPedido itemPedido = itemPedidoService.findById(id);
	return new ResponseEntity <>(itemPedido,HttpStatus.OK);
}
@PostMapping 
public ResponseEntity<ItemPedido>save(@RequestBody ItemPedido itemPedido){
	ItemPedido novoItemPedido = itemPedidoService.save(itemPedido);
		return new ResponseEntity <>(novoItemPedido,HttpStatus.CREATED);
		
		}
@PutMapping 
public ResponseEntity<ItemPedido>update(@RequestBody ItemPedido itemPedido,Integer id){
	ItemPedido novoItemPedido = itemPedidoService.update(itemPedido,id);
	return new ResponseEntity <>(novoItemPedido,HttpStatus.CREATED);

}
@DeleteMapping("/{id}")
public ResponseEntity<String>delete(@PathVariable Integer id){
	itemPedidoService.delete(id);
	return new ResponseEntity<>("",HttpStatus.OK);
			
}
}


