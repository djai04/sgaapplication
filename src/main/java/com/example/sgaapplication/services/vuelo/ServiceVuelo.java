package com.example.sgaapplication.services.vuelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryVuelo;
import com.example.sgaapplication.services.avion.Avion;

@Service
public class ServiceVuelo {

    @Autowired
    private RepositoryVuelo repositoryVuelo;

    public void saveVuelo(String codigoVuelo, String aerolinea, String origen, String destino, String matriculaAvion, String fechaSalida, String fechaLlegada, String horaSalida, String horaLlegada) {
        Vuelo alreadyExists = repositoryVuelo.findByCodigoVuelo(codigoVuelo);
        if (alreadyExists == null) {
            repositoryVuelo.save(new Vuelo(codigoVuelo, aerolinea, origen, destino, matriculaAvion, fechaSalida, fechaLlegada, horaSalida, horaLlegada));
        }
    }
}
