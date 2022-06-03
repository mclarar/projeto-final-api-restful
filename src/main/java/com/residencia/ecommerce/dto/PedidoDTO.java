package com.residencia.ecommerce.dto;

import java.util.Date;

public class PedidoDTO {
	private Integer idPedido;
	private Date dataPedido;
	private Date dataEntrega;
	private Date dataEnvio;
	private Boolean statusPedido;
	private ClienteDTO clienteDTO;
	
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Date getDataEnvio() {
		return dataEnvio;
	}
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	public Boolean getStatusPedido() {
		return statusPedido;
	}
	public void setStatusPedido(Boolean statusPedido) {
		this.statusPedido = statusPedido;
	}
	public ClienteDTO getClienteDTO() {
		return clienteDTO;
	}
	public void setClienteDTO(ClienteDTO clienteDTO) {
		this.clienteDTO = clienteDTO;
	}
	
	
}
