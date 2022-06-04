package com.residencia.ecommerce.dto;

import java.util.Date;

public class ProdutoDTO {
	private Integer idProduto;
	private String nomeProduto;
	private Integer quantidadeEstoque;
	private Date dataCadastro;
	private String imagemProduto;
	private CategoriaDTO categoriaDTO;
	
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}
	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getImagemProduto() {
		return imagemProduto;
	}
	public void setImagemProduto(String imagemProduto) {
		this.imagemProduto = imagemProduto;
	}
	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}
	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}
	
	@Override
	public String toString() {
		return "ProdutoDTO [idProduto=" + idProduto + ", nomeProduto=" + nomeProduto + ", quantidadeEstoque="
				+ quantidadeEstoque + ", dataCadastro=" + dataCadastro + ", imagemProduto=" + imagemProduto
				+ ", categoriaDTO=" + categoriaDTO + "]";
	}
}
