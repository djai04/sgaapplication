package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sgaapplication.services.cliente.Cliente;

public interface RepositoryCliente extends JpaRepository<Cliente, Long> {
    Cliente findById(long id);
    Cliente findByPasaporte(String pasaporte);
}
