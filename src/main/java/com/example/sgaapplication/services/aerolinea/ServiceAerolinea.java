package com.example.sgaapplication.services.aerolinea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryAerolinea;

@Service
public class ServiceAerolinea {
    
    @Autowired
    private RepositoryAerolinea repositoryAerolinea;

    public Aerolinea insertAerolinea(String codigoAerolinea, String nombreAerolinea, String pais) {
        Aerolinea aerolinea = new Aerolinea();
        aerolinea.setCodigoAerolinea(codigoAerolinea);
        aerolinea.setNombreAerolinea(nombreAerolinea);
        aerolinea.setPais(pais);
        aerolinea.setContrasena(codigoAerolinea);

        return repositoryAerolinea.save(aerolinea);
    }
}
