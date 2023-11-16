package com.example.sgaapplication.services.pasajero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sgaapplication.persistency.RepositoryPasajero;
import com.example.sgaapplication.services.pasajero.Pasajero;

@Service
public class ServicePasajero {

    @Autowired
    private RepositoryPasajero repositoryPasajero;

    public Pasajero insertPasajero(String pasaporte, String nombre) {
        Pasajero pasajero = new Pasajero();
        pasajero.setPasaporte(pasaporte);
        pasajero.setNombre(nombre);

        return repositoryPasajero.save(pasajero);    
    }
    
}
