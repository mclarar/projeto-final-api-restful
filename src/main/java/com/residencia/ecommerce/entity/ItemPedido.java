package com.residencia.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	private Double percentualDesconto;
	
	@Column(name = "valor_bruto")
	private Double valorBruto;
	
	@Column(name = "valor_liquido")
	private Double valorLiquido;
	
	@OneToOne
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	private Cliente idPedido;
	
	@OneToOne
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private Cliente idProduto;

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

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public Cliente getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Cliente idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Cliente idProduto) {
		this.idProduto = idProduto;
	}
	
	
}
