package com.residencia.ecommerce.dto;

import java.util.List;

public class CategoriaDTO {
	private Integer idCategoria;
	private String nomeCategoria;
	private String descricaoCategoria;
	private List<ProdutoDTO> produtoDTOList;
	
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	public String getDescricaoCategoria() {
		return descricaoCategoria;
	}
	public void setDescricaoCategoria(String descricaoCategoria) {
		this.descricaoCategoria = descricaoCategoria;
	}
	public List<ProdutoDTO> getProdutoDTOList() {
		return produtoDTOList;
	}
	public void setProdutoDTOList(List<ProdutoDTO> produtoDTOList) {
		this.produtoDTOList = produtoDTOList;
	}
	@Override
	public String toString() {
		return "CategoriaDTO [idCategoria=" + idCategoria + ", nomeCategoria=" + nomeCategoria + ", descricaoCategoria="
				+ descricaoCategoria + ", produtoDTOList=" + produtoDTOList + "]";
	}
	
}
