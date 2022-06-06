package com.residencia.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.ecommerce.dto.CategoriaDTO;
import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Categoria;
import com.residencia.ecommerce.entity.Produto;
import com.residencia.ecommerce.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).get();
	}

	public Categoria findByNome(String nome) {
		return categoriaRepository.findByNomeCategoria(nome).get();
	}

	public Categoria save(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria, Integer id) {
		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {
		Categoria categoria = categoriaRepository.findById(id).get();
		categoriaRepository.delete(categoria);
	}

	public CategoriaDTO findDTOById(Integer id) {
		return categoriaRepository.findById(id).isPresent() ? converterEntidadeParaDTO(categoriaRepository.findById(id).get())
				: null;
	}

	public CategoriaDTO converterEntidadeParaDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();

		categoriaDTO.setIdCategoria(categoria.getIdCategoria());
		categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
		categoriaDTO.setDescricaoCategoria(categoria.getDescricaoCategoria());

		/*List<ProdutoDTO> produtoDTOList = new ArrayList<>();
		if (null != categoria.getProdutoList()) {
			for (Produto produto : categoria.getProdutoList()) {
				ProdutoDTO produtoDTO = new ProdutoDTO();
				produtoDTO.setIdProduto(produto.getIdProduto());
				produtoDTO.setDataCadastro(produto.getDataCadastro());
				produtoDTO.setNomeProduto(produto.getNomeProduto());
				produtoDTO.setImagemProduto(produto.getImagemProduto());
				produtoDTO.setQuantidadeEstoque(produto.getQuantidadeEstoque());
				produtoDTO.setValorProduto(produto.getValorProduto());
				produtoDTO.setDescricaoProduto(produto.getDescricaoProduto());

				produtoDTOList.add(produtoDTO);
			}
			categoriaDTO.setProdutoDTOList(produtoDTOList);
		}*/
		return categoriaDTO;
	}
	
	public Categoria converterDTOParaEntidade(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		
		categoria.setIdCategoria(categoriaDTO.getIdCategoria());
		categoria.setNomeCategoria(categoriaDTO.getNomeCategoria());
		categoria.setDescricaoCategoria(categoriaDTO.getDescricaoCategoria());
		
		/*List<Produto> produtoList = new ArrayList<>();
		if (null != categoriaDTO.getProdutoDTOList()) {
			for (ProdutoDTO produtoDTO : categoriaDTO.getProdutoDTOList()) {
				Produto produto = new Produto();
				produto.setIdProduto(produtoDTO.getIdProduto());
				produto.setDataCadastro(produtoDTO.getDataCadastro());
				produto.setImagemProduto(produtoDTO.getImagemProduto());
				produto.setNomeProduto(produtoDTO.getNomeProduto());
				produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
				produto.setDescricaoProduto(produtoDTO.getDescricaoProduto());
				produto.setValorProduto(produtoDTO.getValorProduto());
				
				produtoList.add(produto);
			}
			categoria.setProdutoList(produtoList);
		}*/
		return categoria;
	}
	
	public CategoriaDTO saveDTO(CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria();
		categoria = converterDTOParaEntidade(categoriaDTO);

		Categoria categorianova = categoriaRepository.save(categoria);

		return converterEntidadeParaDTO(categorianova);
	}
}