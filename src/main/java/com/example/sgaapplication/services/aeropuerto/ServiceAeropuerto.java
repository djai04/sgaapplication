package com.example.sgaapplication.services.aeropuerto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sgaapplication.persistency.RepositoryAerolinea;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.services.aerolinea.Aerolinea;

@Service
public class ServiceAeropuerto {

    @Autowired
    private RepositoryAeropuerto repositoryAeropuerto;

    @Autowired
    private RepositoryAerolinea repositoryAerolinea;

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

    @Transactional
    public void asociarAeropuertoAerolinea(String codigoAeropuerto, String codigoAerolinea) {
        Aeropuerto esteAeropuerto = repositoryAeropuerto.findByCodigoAeropuerto(codigoAeropuerto);
        Aerolinea estaAerolinea = repositoryAerolinea.findByCodigoAerolinea(codigoAerolinea);

        if (!esteAeropuerto.getAerolineasHabilitadas().contains(estaAerolinea)) {
            esteAeropuerto.getAerolineasHabilitadas().add(estaAerolinea);
        }
    }

    @Transactional
    public List<String> getAerolineasAsociadas(String codigoAeropuerto) {
        Aeropuerto esteAeropuerto = repositoryAeropuerto.findByCodigoAeropuerto(codigoAeropuerto);
        List<Aerolinea> aerolineasYaHabilitadas = esteAeropuerto.getAerolineasHabilitadas();
        List<String> codigosAerolineasYaHabilitadas = new ArrayList<>();

        for (Aerolinea aerolinea : aerolineasYaHabilitadas) {
            codigosAerolineasYaHabilitadas.add(aerolinea.getCodigoAerolinea());
        }

        return codigosAerolineasYaHabilitadas;
    }

    @Transactional
    public List<Aerolinea> getAerolineasAsociadasAsAerolineas(String codigoAeropuerto) {
        Aeropuerto esteAeropuerto = repositoryAeropuerto.findByCodigoAeropuerto(codigoAeropuerto);
        List<Aerolinea> aerolineasYaHabilitadas = esteAeropuerto.getAerolineasHabilitadas();

        return aerolineasYaHabilitadas;
    }

    public void modificarPuertas(String codigoAeropuerto, int capacidadPuertas) {
        Aeropuerto esteAeropuerto = repositoryAeropuerto.findByCodigoAeropuerto(codigoAeropuerto);
        esteAeropuerto.setCapacidadPuertas(capacidadPuertas);
        repositoryAeropuerto.save(esteAeropuerto);
    }

    public void modificarPistas(String codigoAeropuerto, int capacidadPistas) {
        Aeropuerto esteAeropuerto = repositoryAeropuerto.findByCodigoAeropuerto(codigoAeropuerto);
        esteAeropuerto.setCapacidadEstacionamiento(capacidadPistas);
        repositoryAeropuerto.save(esteAeropuerto);
    }
    
}
