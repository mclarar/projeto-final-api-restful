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

import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

@Autowired
CategoriaService categoriaService;

@GetMapping
public ResponseEntity<List<Categoria>> findAll(){
List<Categoria>categoriaList = categoriaService.findAll();

return new ResponseEntity <>(categoriaList,HttpStatus.OK);

}

@GetMapping("/{id}")
public ResponseEntity<Categoria>findById(@PathVariable Integer id){
	Categoria categoria = categoriaService.findById(id);
	return new ResponseEntity <>(categoria,HttpStatus.OK);
}
@PostMapping 
public ResponseEntity<Categoria>save(@RequestBody Categoria categoria){
	Categoria novoCategoria = categoriaService.save(categoria);
		return new ResponseEntity <>(novoCategoria,HttpStatus.CREATED);
		
		}
@PutMapping 
public ResponseEntity<Categoria>update(@RequestBody Categoria categoria,Integer id){
	Categoria novoCategoria = categoriaService.update(categoria,id);
	return new ResponseEntity <>(novoCategoria,HttpStatus.CREATED);

}
@DeleteMapping("/{id}")
public ResponseEntity<String>delete(@PathVariable Integer id){
	categoriaService.delete(id);
	return new ResponseEntity<>("",HttpStatus.OK);
			
}
}



