package com.example.sgaapplication.services.aeropuerto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;

@Service
public class ServiceAeropuerto {

    @Autowired
    private RepositoryAeropuerto repositoryAeropuerto;

    public Aeropuerto insertAeropuerto(String codigoAeropuerto, String nombreAeropuerto, String pais, String ciudad, int capacidadPuertas, int capacidadEstacionamiento) {
        Aeropuerto aeropuerto = new Aeropuerto();
        aeropuerto.setCodigoAeropuerto(codigoAeropuerto);
        aeropuerto.setNombreAeropuerto(nombreAeropuerto);
        aeropuerto.setPais(pais);
        aeropuerto.setCiudad(ciudad);
        aeropuerto.setCapacidadPuertas(capacidadPuertas);
        aeropuerto.setCapacidadEstacionamiento(capacidadEstacionamiento);
        aeropuerto.setContrasena(codigoAeropuerto);

        return repositoryAeropuerto.save(aeropuerto);    
    }
    
}
