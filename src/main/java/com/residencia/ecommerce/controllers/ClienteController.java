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
import com.residencia.ecommerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

@Autowired
ClienteService clienteService;

@GetMapping
public ResponseEntity<List<Cliente>> findAll(){
List<Cliente>clienteList = clienteService.findAll();

return new ResponseEntity <>(clienteList,HttpStatus.OK);

}

@GetMapping("/{id}")
public ResponseEntity<Cliente>findById(@PathVariable Integer id){
	Cliente cliente = clienteService.findById(id);
	return new ResponseEntity <>(cliente,HttpStatus.OK);
}
@PostMapping 
public ResponseEntity<Cliente>save(@RequestBody Cliente cliente){
	Cliente novoCliente = clienteService.save(cliente);
		return new ResponseEntity <>(novoCliente,HttpStatus.CREATED);
		
		}
@PutMapping 
public ResponseEntity<Cliente>update(@RequestBody Cliente cliente,Integer id){
	Cliente novoCliente = clienteService.update(cliente,id);
	return new ResponseEntity <>(novoCliente,HttpStatus.CREATED);

}
@DeleteMapping("/{id}")
public ResponseEntity<String>delete(@PathVariable Integer id){
	clienteService.delete(id);
	return new ResponseEntity<>("",HttpStatus.OK);
			
}
}


