package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.dto.ItemPedidoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.ItemPedido;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.exception.EmailException;
import com.residencia.ecommerce.repository.ItemPedidoRepository;
import com.residencia.ecommerce.repository.PedidoRepository;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}

	public Pedido findById(Integer id) {
		return pedidoRepository.findById(id).get();
	}

	public Pedido save(Pedido pedido) throws Exception {
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		return pedidoSalvo;
	}

	public Pedido update(Pedido pedido, Integer id) {
		return pedidoRepository.save(pedido);
	}

	public void delete(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedidoRepository.delete(pedido);
	}

	// DTO AREA

	public PedidoDTO findDTOById(Integer id) {
		return pedidoRepository.findById(id).isPresent() ? converterEntidadeParaDTO(pedidoRepository.findById(id).get())
				: null;
	}
	
	public PedidoDTO savePedidoDTO(PedidoDTO pedidoDTO) throws MessagingException {
		Pedido pedido = new Pedido();
		pedido = converterDTOParaEntidade(pedidoDTO);
		Pedido pedidonovo = pedidoRepository.save(pedido);
		//List<ItemPedido> ItemPedidoList = new ArrayList<>();
		if (null != pedidoDTO.getItemPedidoDTOList()) {
			for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItemPedidoDTOList()) {
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setPedido(pedidonovo);
				itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
				itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
				itemPedido.setQuantidadeItemPedido(itemPedidoDTO.getQuantidadeItemPedido());
				itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
				itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());
				
				//itemPedido.setProduto(produtoService.converterDTOParaEntidade(itemPedidoDTO.getProdutoDTO()));
		        
				//ItemPedidoList.add(itemPedido);	
				itemPedidoRepository.save(itemPedido);
			}
		}
		
		//PedidoDTO pedidoSalvo = new PedidoDTO();
		//String corpoEmail = "<h1>Hello!!</h1>"+ pedidoSalvo.toString();
		//emailService.enviarEmailHtml("amanda.costa7@aluno.senai.br","Recebemos seu pedido!!", corpoEmail);
		emailService.enviarEmailHtml(pedido, pedidoDTO);
	
		return converterEntidadeParaDTO(pedidonovo);
		
	}
	
	public PedidoDTO converterEntidadeParaDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setDataEnvio(pedido.getDataEnvio());
		pedidoDTO.setDataEntrega(pedido.getDataEntrega());
		pedidoDTO.setStatusPedido(pedido.getStatusPedido());
		
		ClienteDTO clienteDTO = clienteService.findClienteDTOById(pedido.getCliente().getIdCliente());
		pedidoDTO.setClienteDTO(clienteDTO);
		
		
		List<ItemPedidoDTO> ItemPedidoDTOList = new ArrayList<>();
		if (null != pedido.getItemPedidoList()) {
			for (ItemPedido itemPedido : pedido.getItemPedidoList()) {
				ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
				itemPedidoDTO.setIdItemPedido(itemPedido.getIdItemPedido());
				itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
				itemPedidoDTO.setPrecoVenda(itemPedido.getPrecoVenda());
				itemPedidoDTO.setQuantidadeItemPedido(itemPedido.getQuantidadeItemPedido());
				itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
				itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
				
				
				
				ItemPedidoDTOList.add(itemPedidoDTO);
			}
			pedidoDTO.setItemPedidoDTOList(ItemPedidoDTOList);
		}
		return pedidoDTO;
	}

	public Pedido converterDTOParaEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setDataEnvio(pedidoDTO.getDataEnvio());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());
		
		Cliente cliente = clienteService.findById(pedidoDTO.getClienteDTO().getIdCliente());
		pedido.setCliente(cliente);
		
		List<ItemPedido> ItemPedidoList = new ArrayList<>();
		if (null != pedidoDTO.getItemPedidoDTOList()) {
			for (ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItemPedidoDTOList()) {
				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setIdItemPedido(itemPedidoDTO.getIdItemPedido());
				itemPedido.setPercentualDesconto(itemPedidoDTO.getPercentualDesconto());
				itemPedido.setPrecoVenda(itemPedidoDTO.getPrecoVenda());
				itemPedido.setQuantidadeItemPedido(itemPedidoDTO.getQuantidadeItemPedido());
				itemPedido.setValorBruto(itemPedidoDTO.getValorBruto());
				itemPedido.setValorLiquido(itemPedidoDTO.getValorLiquido());
				
				
				ItemPedidoList.add(itemPedido);	
			}
			
			pedido.setItemPedidoList(ItemPedidoList);
		}
		return pedido;
	}

}
