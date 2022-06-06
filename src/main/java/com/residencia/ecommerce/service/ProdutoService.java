package com.residencia.ecommerce.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	ArquivoService arquivoService;

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

	public ProdutoDTO findProdutoDTOById(Integer id) {
		return produtoRepository.findById(id).isPresent()
				? converterEntidadeParaDTO(produtoRepository.findById(id).get())
				: null;
	}

	/*
	 * public Produto saveProduto(String produto, MultipartFile file) throws
	 * Exception {
	 * 
	 * Produto produtoNovo = new Produto(); try { ObjectMapper objectMapper = new
	 * ObjectMapper(); produtoNovo = objectMapper.readValue(produto, Produto.class);
	 * 
	 * } catch (IOException e) { System.out.println("Ocorreu um erro na conversão");
	 * } Produto produtoBD = produtoRepository.save(produtoNovo);
	 * produtoBD.setImagemProduto(produtoBD.getIdProduto() + "_" +
	 * file.getOriginalFilename()); Produto produtoAtualizado =
	 * produtoRepository.save(produtoBD);
	 * 
	 * try { arquivoService.criarArquivo(produtoBD.getIdProduto() + "_" +
	 * file.getOriginalFilename(), file); } catch (Exception e) { throw new
	 * Exception("Ocorreu um erro ao tentar copiar o arquivo - " +
	 * e.getStackTrace()); }
	 * 
	 * 
	 * return produtoAtualizado;
	 * 
	 * }
	 */
	public ProdutoDTO saveProduto(String produto, MultipartFile file) throws Exception {
		Produto produtos = new Produto();
		ProdutoDTO produtoNovo = new ProdutoDTO();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			produtoNovo = objectMapper.readValue(produto, ProdutoDTO.class);

		} catch (IOException e) {
			System.out.println("Ocorreu um erro na conversão");
		}
		produtoNovo.setImagemProduto(produtoNovo.hashCode() + "_" + file.getOriginalFilename());
		try {
			arquivoService.criarArquivo(produtoNovo.hashCode()+ "_" + file.getOriginalFilename(), file);
		} catch (Exception e) {
			throw new Exception("Ocorreu um erro ao tentar copiar o arquivo - " + e.getStackTrace());
		}
		produtos = converterDTOParaEntidade(produtoNovo);

		Produto produtoo = produtoRepository.save(produtos);
		return converterEntidadeParaDTO(produtoo);
	}

	/*
	 * public ProdutoDTO saveDTO(ProdutoDTO produtoDTO) { Produto produtos = new
	 * Produto(); produtos = converterDTOParaEntidade(produtoDTO); Produto
	 * produtoNovo = produtoRepository.save(produtos); return
	 * converterEntidadeParaDTO(produtoNovo); }
	 */

	public ProdutoDTO converterEntidadeParaDTO(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();

		produtoDTO.setIdProduto(produto.getIdProduto());
		produtoDTO.setNomeProduto(produto.getNomeProduto());
		produtoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setImagemProduto(produto.getImagemProduto());
		produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());
		produtoDTO.setValorProduto(produto.getValorProduto());

		CategoriaDTO categoriaDTO = categoriaService.findDTOById(produto.getCategoria().getIdCategoria());
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
		produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
		produto.setValorProduto(produtoDTO.getValorProduto());

		Categoria categoria = categoriaService.findById(produtoDTO.getCategoriaDTO().getIdCategoria());
		produto.setCategoria(categoria);

		return produto;
	}
}