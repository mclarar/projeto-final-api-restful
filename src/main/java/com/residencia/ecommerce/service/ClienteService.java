package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.CepDTO;
import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.dto.PedidoDTO;
import com.residencia.ecommerce.entity.Cliente;
import com.residencia.ecommerce.entity.Endereco;
import com.residencia.ecommerce.entity.Pedido;
import com.residencia.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoService enderecoService;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente findById(Integer id) {
		return clienteRepository.findById(id).get();
	}

	public Cliente findByNome(String nome) {
		return clienteRepository.findByNomeCliente(nome).get();
	}

	/*
	 * public Cliente findByCPF(String cpf) { return
	 * clienteRepository.findByCpfCliente(cpf).get();
	 * 
	 * }
	 */

	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
//	public Cliente updateClienteDTO(Integer id, Cliente entity) throws Exception {
//		
//
//		return clienteRepository.save(null);
//	}

	public void delete(Integer id) {
		Cliente cliente = clienteRepository.findById(id).get();
		clienteRepository.delete(cliente);
	}

	public ClienteDTO findClienteDTOById(Integer id) {
		return clienteRepository.findById(id).isPresent()
				? converterEntidadeParaDTO(clienteRepository.findById(id).get())
				: null;
	}

	public ClienteDTO saveDTO(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		CepDTO cepDTO = enderecoService.consultarCepDTO(clienteDTO.getEnderecoDTO().getCep());
		EnderecoDTO novoEndereco = enderecoService.CepDTOParaEnderecoDTO(cepDTO);
		novoEndereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
		clienteDTO.setEnderecoDTO(novoEndereco);
		cliente = converterDTOParaEntidade(clienteDTO);
		Cliente clientenovo = clienteRepository.save(cliente);
		return converterEntidadeParaDTO(clientenovo);
	}

	public ClienteDTO converterEntidadeParaDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();

		clienteDTO.setIdCliente(cliente.getIdCliente());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setDataNascimento(cliente.getDataNascimento());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNomeCliente(cliente.getNomeCliente());
		clienteDTO.setTelefoneCliente(cliente.getTelefoneCliente());
		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setBairro(cliente.getEndereco().getBairro());
		enderecoDTO.setCep(cliente.getEndereco().getCep());
		enderecoDTO.setCidade(cliente.getEndereco().getCidade());
		enderecoDTO.setComplemento(cliente.getEndereco().getComplemento());
		enderecoDTO.setNumero(cliente.getEndereco().getNumero());
		enderecoDTO.setRua(cliente.getEndereco().getRua());
		enderecoDTO.setUf(cliente.getEndereco().getUf());
		enderecoDTO.setIdEndereco(cliente.getEndereco().getIdEndereco());

		clienteDTO.setEnderecoDTO(enderecoDTO);

		List<PedidoDTO> pedidoDTOList = new ArrayList<>();
		if (null != cliente.getPedidoList()) {
			for (Pedido pedido : cliente.getPedidoList()) {
				PedidoDTO pedidoDTO = new PedidoDTO();
				pedidoDTO.setDataEntrega(pedido.getDataEntrega());
				pedidoDTO.setDataEnvio(pedido.getDataEnvio());
				pedidoDTO.setDataPedido(pedido.getDataPedido());
				pedidoDTO.setIdPedido(pedido.getIdPedido());
				pedidoDTO.setStatusPedido(pedido.getStatusPedido());

				pedidoDTOList.add(pedidoDTO);
			}
			clienteDTO.setPedidoDTOList(pedidoDTOList);
		}
		return clienteDTO;
	}

	public Cliente converterDTOParaEntidade(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();

		cliente.setCpf(clienteDTO.getCpf());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setNomeCliente(clienteDTO.getNomeCliente());
		cliente.setTelefoneCliente(clienteDTO.getTelefoneCliente());

		Endereco endereco = new Endereco();
		endereco.setCep(clienteDTO.getEnderecoDTO().getCep());
		endereco.setNumero(clienteDTO.getEnderecoDTO().getNumero());
		endereco.setBairro(clienteDTO.getEnderecoDTO().getBairro());
		endereco.setCidade(clienteDTO.getEnderecoDTO().getCidade());
		endereco.setComplemento(clienteDTO.getEnderecoDTO().getComplemento());
		endereco.setIdEndereco(clienteDTO.getEnderecoDTO().getIdEndereco());
		endereco.setRua(clienteDTO.getEnderecoDTO().getRua());
		endereco.setUf(clienteDTO.getEnderecoDTO().getUf());

		Endereco novoEndereco = enderecoService.save(endereco);
		cliente.setEndereco(novoEndereco);

		List<Pedido> pedidoList = new ArrayList<>();
		if (null != clienteDTO.getPedidoDTOList()) {
			for (PedidoDTO pedidoDTO : clienteDTO.getPedidoDTOList()) {
				Pedido pedido = new Pedido();
				pedido.setDataEntrega(pedidoDTO.getDataEntrega());
				pedido.setDataEnvio(pedidoDTO.getDataEnvio());
				pedido.setDataPedido(pedidoDTO.getDataPedido());
				pedido.setIdPedido(pedidoDTO.getIdPedido());
				pedido.setStatusPedido(pedidoDTO.getStatusPedido());

				pedidoList.add(pedido);
			}
			cliente.setPedidoList(pedidoList);
		}
		return cliente;
	}

	public ClienteDTO findClienteDTOByCPF(ClienteDTO cliente) {
		if (clienteRepository.findByCpf(cliente.getCpf()).isPresent()) {
			return null;
		}
		return cliente;
	}
	
	public ClienteDTO findClienteDTOByEmail(ClienteDTO cliente) {
        if(clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            return null;
        }
        return cliente;
    }
}
