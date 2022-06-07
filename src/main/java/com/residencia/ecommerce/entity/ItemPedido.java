package com.residencia.ecommerce.entity;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Integer idItemPedido;
	
	@Column(name = "quantidade")
	private Integer quantidadeItemPedido;
	
	@Column(name = "preco_venda")
	private Integer precoVenda;
	
	@Column(name = "percentual_desconto")
	private BigDecimal percentualDesconto;
	
	@Column(name = "valor_bruto")
	private BigDecimal valorBruto;
	
	@Column(name = "valor_liquido")
	private BigDecimal valorLiquido;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Pedido pedido;
	
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	public Produto produto;

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "produto=" + produto+" quantidadeItemPedido=" + quantidadeItemPedido
				+ ", precoVenda=" + precoVenda + ", percentualDesconto=" + percentualDesconto + ", valorBruto="
				+ valorBruto + ", valorLiquido=" + valorLiquido ;
	}
	
}
