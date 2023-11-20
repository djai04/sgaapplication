package com.example.sgaapplication.services.boleto;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryBoleto;

@Service
public class ServiceBoleto {
    @Autowired
    RepositoryBoleto boletoRepository;

    public void saveBoleto(String codigo, String pasaporte) {

        String identificadorBoleto = codigo + pasaporte;
        Boleto alreadyExists = boletoRepository.findByIdentificador(identificadorBoleto);
        if (alreadyExists == null) {
            boletoRepository.save(new Boleto(identificadorBoleto, codigo, pasaporte));
        }
    }

    public ServiceBoleto() {

    }

    public boolean validarPasaporte(String pasaporte) {
        return pasaporte.matches("[A-Z]\\d{6}");
    }

}