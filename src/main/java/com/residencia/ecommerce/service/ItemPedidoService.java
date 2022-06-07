package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ItemPedidoRepository;
import com.residencia.ecommerce.repository.PedidoRepository;

@Service
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private PedidoRepository pedidoRepository;
	
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
	
	public ItemPedidoDTO findDTOById(Integer id) {
		return itemPedidoRepository.findById(id).isPresent() ? converterEntidadeParaDTO(itemPedidoRepository.findById(id).get())
				: null;
	}
	
	public ItemPedidoDTO converterEntidadeParaDTO(ItemPedido itemPedido) {
		ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO ();
		
		itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
		itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
		itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
		itemPedidoDTO.setQuantidadeItemPedido(itemPedido.getQuantidadeItemPedido());
		itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
		itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
		
		PedidoDTO pedidoDTO = pedidoService.findDTOById(itemPedido.getPedido().getIdPedido());
		itemPedidoDTO.setPedidoDTO(pedidoDTO);
		
		ProdutoDTO produtoDTO = produtoService.findProdutoDTOById(itemPedido.getProduto().getIdProduto());
		itemPedidoDTO.setProdutoDTO(produtoDTO);
		
		return itemPedidoDTO;
	}
	
	public ItemPedido converterDTOParaEntidade(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
		itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
		itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
		itemPedido.setQuantidadeItemPedido(itemPedidoDTO.getQuantidadeItemPedido());
		itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
		itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());
		
		Pedido pedido = pedidoService.findById(itemPedidoDTO.getPedidoDTO().getIdPedido());
		itemPedido.setPedido(pedido);
		
		Produto produto = produtoService.findById(itemPedidoDTO.getProdutoDTO().getIdProduto());
		itemPedido.setProduto(produto);
		
		return itemPedido;
	}
	
	public ItemPedidoDTO saveDTO(ItemPedidoDTO itemPedidoDTO) {
		ItemPedido itemPedido = new ItemPedido();
		
		itemPedido = converterDTOParaEntidade(itemPedidoDTO);
		itemPedidoRepository.save(itemPedido);

		return converterEntidadeParaDTO(itemPedido);
	}
}
