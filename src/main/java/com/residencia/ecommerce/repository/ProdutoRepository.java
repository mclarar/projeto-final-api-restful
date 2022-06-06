package com.residencia.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.ecommerce.dto.ProdutoDTO;
import com.residencia.ecommerce.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


	Optional<Produto> findByNomeProduto(String nome);

	ProdutoDTO save(ProdutoDTO produtoNovo);


}
