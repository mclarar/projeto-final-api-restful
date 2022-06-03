package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).get();
	}
	
	public Categoria findByNome(String nome) {
		return categoriaRepository.findByNomeCategoria(nome).get();
	}
	
	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update(Categoria categoria, Integer id) {
		return categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).get();
		categoriaRepository.delete(categoria);
	}
	
}
