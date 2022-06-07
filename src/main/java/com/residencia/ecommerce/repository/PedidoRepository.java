package com.residencia.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

	PedidoDTO save(PedidoDTO pedidoDTO);

	void save(Produto produtoBD);




}
