package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public Pedido findById(Integer id) {
		return pedidoRepository.findById(id).get();
	}
		
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido update(Pedido pedido, Integer id) {
		return pedidoRepository.save(pedido);
	}
	
	public void delete(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedidoRepository.delete(pedido);
	}
}
