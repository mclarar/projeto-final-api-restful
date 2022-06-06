package com.residencia.ecommerce.dto;

import java.util.Date;

public class ProdutoDTO {
	private Integer idProduto;
	private String nomeProduto;
	private Integer quantidadeEstoque;
	private Date dataCadastro;
	private String imagemProduto;
	private String descricaoProduto;
	private Float valorProduto;
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
	public String getDescricaoProduto() {
		return descricaoProduto;
	}
	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}
	public Float getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(Float valorProduto) {
		this.valorProduto = valorProduto;
	}
	public ProdutoDTO() {
		super();
	}
	public ProdutoDTO(Integer idProduto, String nomeProduto, Integer quantidadeEstoque, Date dataCadastro,
			String imagemProduto, String descricaoProduto, Float valorProduto, CategoriaDTO categoriaDTO) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.quantidadeEstoque = quantidadeEstoque;
		this.dataCadastro = dataCadastro;
		this.imagemProduto = imagemProduto;
		this.descricaoProduto = descricaoProduto;
		this.valorProduto = valorProduto;
		this.categoriaDTO = categoriaDTO;
	}
	@Override
	public String toString() {
		return  "idProduto" ;
	}
	
}
