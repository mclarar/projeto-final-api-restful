package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}
	
	public Endereco findById(Integer id) {
		return enderecoRepository.findById(id).get();
	}
		
	public Endereco save(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco update(Endereco endereco, Integer id) {
		return enderecoRepository.save(endereco);
	}
	
	public void delete(Integer id) {
		Endereco endereco = enderecoRepository.findById(id).get();
		enderecoRepository.delete(endereco);
	}
}
