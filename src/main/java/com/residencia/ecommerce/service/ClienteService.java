package com.residencia.ecommerce.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
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
		return clienteRepository.findByNomeCliente(nome).get();
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
	
	/*public ClienteDTO converterEntidadeParaDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		
		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNomeCliente(cliente.getNomeCliente());
		clienteDTO.setTelefoneCliente(cliente.getNomeCliente());*/
		
		
		
	
}
