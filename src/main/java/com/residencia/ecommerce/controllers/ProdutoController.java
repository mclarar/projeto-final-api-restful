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

import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

@Autowired
ProdutoService produtoService;

@GetMapping
public ResponseEntity<List<Produto>> findAll(){
List<Produto>produtoList = produtoService.findAll();

return new ResponseEntity <>(produtoList,HttpStatus.OK);

}

@GetMapping("/{id}")
public ResponseEntity<Produto>findById(@PathVariable Integer id){
	Produto produto = produtoService.findById(id);
	return new ResponseEntity <>(produto,HttpStatus.OK);
}
@PostMapping 
public ResponseEntity<Produto>save(@RequestBody Produto produto){
	Produto novoProduto = produtoService.save(produto);
		return new ResponseEntity <>(novoProduto,HttpStatus.CREATED);
		
		}
@PutMapping 
public ResponseEntity<Produto>update(@RequestBody Produto produto,Integer id){
	Produto novoProduto = produtoService.update(produto,id);
	return new ResponseEntity <>(novoProduto,HttpStatus.CREATED);

}
@DeleteMapping("/{id}")
public ResponseEntity<String>delete(@PathVariable Integer id){
	produtoService.delete(id);
	return new ResponseEntity<>("",HttpStatus.OK);
			
}
}


