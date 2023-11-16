package com.example.sgaapplication.services.boleto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryBoleto;

@Service
public class ServiceBoleto {
    @Autowired
    RepositoryBoleto boletoRepository;

    public void saveBoleto(String codigo, String pasaporte) {
        Boleto alreadyExists = boletoRepository.findByCodigo(codigo);
        if (alreadyExists == null) {
            boletoRepository.save(new Boleto(codigo, pasaporte));
        }
    }

    public ServiceBoleto() {

    }

}