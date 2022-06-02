package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).get();
	}
	
	public Cliente findByNome(String nome) {
		return clienteRepository.findByNome(nome).get();
	}
	
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente update(Cliente cliente, Integer id) {
		return clienteRepository.save(cliente);
	}
	
	public void delete(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();
		clienteRepository.delete(cliente);
	}
}
