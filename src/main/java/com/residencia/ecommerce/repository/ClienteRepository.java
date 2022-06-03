package com.residencia.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.residencia.ecommerce.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


	Optional<Cliente> findByNomeCliente(String nome);

}
