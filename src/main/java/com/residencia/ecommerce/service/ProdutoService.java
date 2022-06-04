package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.dto.ClienteDTO;
import com.residencia.ecommerce.dto.EnderecoDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	public Produto findById(Integer id) {
		return produtoRepository.findById(id).get();
	}

	public Produto findByNome(String nome) {
		return produtoRepository.findByNomeProduto(nome).get();
	}

	public Produto save(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Produto update(Produto produto, Integer id) {
		return produtoRepository.save(produto);
	}

	public void delete(Integer id) {
		Produto produto = produtoRepository.findById(id).get();
		produtoRepository.delete(produto);
	}

	// DTO AREA
	
//	public ProdutoDTO findProdutoDTOById(Integer id) {
//		return produtoRepository.findById(id).isPresent() ? converterEntidadeParaDTO(produtoRepository.findById(id).get())
//				: null;
//	}

	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto = converterDTOParaEntidade(produtoDTO);
		produtoRepository.save(produto);

		return converterEntidadeParaDTO(produto);
	}

	public ProdutoDTO converterEntidadeParaDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setImagemProduto(produto.getImagemProduto());

		CategoriaDTO categoriaDTO = categoriaService.findCategoriaDTOById(produto.getCategoria().getIdCategoria());
		produtoDTO.setCategoriaDTO(categoriaDTO);
		

		return produtoDTO;
	}

	public Produto converterDTOParaEntidade(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();

		produto.setIdProduto(produtoDTO.getIdProduto());
		produto.setNomeProduto(produtoDTO.getNomeProduto());
		produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
		produto.setDataCadastro(produtoDTO.getDataCadastro());
		produto.setImagemProduto(produtoDTO.getImagemProduto());
//		produto.setCategoria(cated);
		return produto;
	}
}