package com.example.sgaapplication.services.vuelo;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryVuelo;

@Service
public class ServiceVuelo {

    @Autowired
    private RepositoryVuelo repositoryVuelo;

    public void saveVuelo(String codigoVuelo, String aerolinea, String origen, String destino, String matriculaAvion, String fechaSalida, String fechaLlegada, LocalTime horaSalida, LocalTime horaLlegada) {
        Vuelo alreadyExists = repositoryVuelo.findByCodigoVuelo(codigoVuelo);
        if (alreadyExists == null) {
            repositoryVuelo.save(new Vuelo(codigoVuelo, aerolinea, origen, destino, matriculaAvion, fechaSalida, fechaLlegada, horaSalida, horaLlegada, "00"));
        }
    }

    public boolean isOrigen(String codigoVuelo, String codigoAeropuerto) {

        Vuelo vuelo = repositoryVuelo.findByCodigoVuelo(codigoVuelo);

        if (vuelo.getAeropuertoOrigen().equals(codigoAeropuerto)) {
            return true;
        } else {
            return false;
        }
        
    }

    public void validarVuelo(String codigoVuelo, boolean origen) {
        Vuelo vuelo = repositoryVuelo.findByCodigoVuelo(codigoVuelo);

        if (origen) {
            if (vuelo.getEstado().equals("00")) {
                vuelo.setEstado("10");
                repositoryVuelo.save(vuelo);
            } else if (vuelo.getEstado().equals("01")) {
                vuelo.setEstado("11");
                repositoryVuelo.save(vuelo);
            }
        } else {
            if (vuelo.getEstado().equals("00")) {
                vuelo.setEstado("01");
                repositoryVuelo.save(vuelo);
            } else if (vuelo.getEstado().equals("10")) {
                vuelo.setEstado("11");
                repositoryVuelo.save(vuelo);
            }
        }
    }

    public void denegarVuelo(String codigoVuelo) {
        Vuelo vuelo = repositoryVuelo.findByCodigoVuelo(codigoVuelo);
        vuelo.setEstado("22");
        repositoryVuelo.save(vuelo);
    }
}
