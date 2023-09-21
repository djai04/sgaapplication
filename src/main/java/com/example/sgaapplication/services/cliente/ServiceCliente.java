package com.example.sgaapplication.services.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryCliente;

@Service
public class ServiceCliente {

    @Autowired
    private RepositoryCliente repositoryCliente;

    public Cliente insertCliente(String pasaporte, String contrasena, String tipo) {
        Cliente cliente = new Cliente();
        cliente.setPasaporte(pasaporte);
        cliente.setContrasena(contrasena);
        cliente.setTipo(tipo);

        return repositoryCliente.save(cliente);
    }
}
