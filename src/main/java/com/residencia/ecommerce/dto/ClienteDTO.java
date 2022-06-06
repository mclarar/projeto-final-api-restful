package com.residencia.ecommerce.dto;

import java.util.Date;
import java.util.List;

public class ClienteDTO {
	private Integer idCliente;
	private String email;
	private String nomeCliente;
	private String cpf;
	private String telefoneCliente;
	private Date dataNascimento;
	private EnderecoDTO enderecoDTO;
	private List<PedidoDTO> pedidoDTOList;
	
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefoneCliente() {
		return telefoneCliente;
	}
	public void setTelefoneCliente(String telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public EnderecoDTO getEnderecoDTO() {
		return enderecoDTO;
	}
	public void setEnderecoDTO(EnderecoDTO enderecoDTO) {
		this.enderecoDTO = enderecoDTO;
	}
	public List<PedidoDTO> getPedidoDTOList() {
		return pedidoDTOList;
	}
	public void setPedidoDTOList(List<PedidoDTO> pedidoDTOList) {
		this.pedidoDTOList = pedidoDTOList;
	}
	public ClienteDTO() {
		super();
	}
	public ClienteDTO(Integer idCliente, String email, String nomeCliente, String cpf, String telefoneCliente,
			Date dataNascimento, EnderecoDTO enderecoDTO, List<PedidoDTO> pedidoDTOList) {
		super();
		this.idCliente = idCliente;
		this.email = email;
		this.nomeCliente = nomeCliente;
		this.cpf = cpf;
		this.telefoneCliente = telefoneCliente;
		this.dataNascimento = dataNascimento;
		this.enderecoDTO = enderecoDTO;
		this.pedidoDTOList = pedidoDTOList;
	}
	@Override
	public String toString() {
		return "ClienteDTO [idCliente=" + idCliente + ", email=" + email + ", nomeCliente=" + nomeCliente + ", cpf="
				+ cpf + ", telefoneCliente=" + telefoneCliente + ", dataNascimento=" + dataNascimento + ", enderecoDTO="
				+ enderecoDTO + ", pedidoDTOList=" + pedidoDTOList + "]";
	}
}
