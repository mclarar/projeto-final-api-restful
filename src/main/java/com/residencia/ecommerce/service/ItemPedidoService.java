package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public List<ItemPedido> findAll() {
		return itemPedidoRepository.findAll();
	}
	
	public ItemPedido findById(Integer id) {
		return itemPedidoRepository.findById(id).get();
	}
		
	public ItemPedido save(ItemPedido itemPedido) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public ItemPedido update(ItemPedido itemPedido, Integer id) {
		return itemPedidoRepository.save(itemPedido);
	}
	
	public void delete(Integer id) {
		ItemPedido itemPedido = itemPedidoRepository.findById(id).get();
		itemPedidoRepository.delete(itemPedido);
	}
}
