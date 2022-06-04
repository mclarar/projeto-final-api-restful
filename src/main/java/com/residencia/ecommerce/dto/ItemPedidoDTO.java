package com.residencia.ecommerce.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
	private Integer idItemPedido;
	private Integer quantidadeItemPedido;
	private Integer precoVenda;
	private BigDecimal percentualDesconto;
	private BigDecimal valorBruto;
	private BigDecimal valorLiquido;
	private PedidoDTO pedidoDTO;
	private ProdutoDTO produtoDTO;
	
	public Integer getIdItemPedido() {
		return idItemPedido;
	}
	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}
	public Integer getQuantidadeItemPedido() {
		return quantidadeItemPedido;
	}
	public void setQuantidadeItemPedido(Integer quantidadeItemPedido) {
		this.quantidadeItemPedido = quantidadeItemPedido;
	}
	public Integer getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Integer precoVenda) {
		this.precoVenda = precoVenda;
	}
	public BigDecimal getPercentualDesconto() {
		return percentualDesconto;
	}
	public void setPercentualDesconto(BigDecimal percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}
	public BigDecimal getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}
	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}
	public PedidoDTO getPedidoDTO() {
		return pedidoDTO;
	}
	public void setPedidoDTO(PedidoDTO pedidoDTO) {
		this.pedidoDTO = pedidoDTO;
	}
	public ProdutoDTO getProdutoDTO() {
		return produtoDTO;
	}
	public void setProdutoDTO(ProdutoDTO produtoDTO) {
		this.produtoDTO = produtoDTO;
	}
	
	
}
