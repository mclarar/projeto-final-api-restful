package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;
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

	// DTO AREA

	public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido = converterDTOParaEntidade(pedidoDTO);
		pedidoRepository.save(pedido);

		return converterEntidadeParaDTO(pedido);
	}

	public PedidoDTO converterEntidadeParaDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataEntrega(pedido.getDataEntrega());
		pedidoDTO.setStatusPedido(pedido.getStatusPedido());
		
		
		return pedidoDTO;

	}

	public Pedido converterDTOParaEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();

		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());

		return pedido;
	}

}
