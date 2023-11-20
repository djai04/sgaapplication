package com.example.sgaapplication.services.boleto;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryAvion;
import com.example.sgaapplication.persistency.RepositoryBoleto;
import com.example.sgaapplication.persistency.RepositoryVuelo;
import com.example.sgaapplication.services.avion.Avion;
import com.example.sgaapplication.services.vuelo.Vuelo;

@Service
public class ServiceBoleto {
    @Autowired
    RepositoryBoleto boletoRepository;

    @Autowired
    RepositoryVuelo vueloRepository;

    @Autowired
    RepositoryAvion avionRepository;

    public void saveBoleto(String codigo, String pasaporte) {

        String identificadorBoleto = codigo + pasaporte;
        Boleto alreadyExists = boletoRepository.findByIdentificador(identificadorBoleto);
        if (alreadyExists == null) {
            boletoRepository.save(new Boleto(identificadorBoleto, codigo, pasaporte));
        }
    }

    public ServiceBoleto() {

    }

    public boolean validarPasaporte(String pasaporte, String codigo) {

        if (!pasaporte.matches("[A-Z]\\d{6}")) {
            return false;
        }

        List<Boleto> boletosDeVuelo = boletoRepository.findAll();
        boletosDeVuelo.removeIf(e -> !e.getCodigo().equals(codigo));

        Vuelo esteVuelo = vueloRepository.findByCodigoVuelo(codigo);
        String matriculaAvion = esteVuelo.getMatriculaAvion();

        Avion esteAvion = avionRepository.findByMatricula(matriculaAvion);
        int capacidadAvion = Integer.parseInt(esteAvion.getAsientos());

        if (capacidadAvion <= boletosDeVuelo.size()) {
            return false;
        }

        return true;
    }

}