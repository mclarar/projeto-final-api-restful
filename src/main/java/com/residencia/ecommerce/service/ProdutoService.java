package com.residencia.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
//	public Produto findByDescricao(String descricao) {
//		return produtoRepository.findByDescricaoProduto(descricao).get();
//	}
	
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
}
