package com.example.sgaapplication.services.avion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.persistency.RepositoryAvion;
import com.example.sgaapplication.services.aerolinea.Aerolinea;

@Service
public class ServiceAvion {
    @Autowired
    private RepositoryAvion repositoryAvion;

    public void saveAvion(String matricula, String capacidad, String asientos, String aerolinea) {
        Avion alreadyExists = repositoryAvion.findByMatricula(matricula);
        if (alreadyExists == null) {
            repositoryAvion.save(new Avion(matricula, capacidad, asientos, "Espera", aerolinea));
        }
    }

    public boolean validarDatosAvion(String matricula, String capacidadAsientos, String capacidadCarga) {

        try {
            if (!(matricula.matches("[A-Z]\\d{5}"))) {
                System.out.println("Matricula");
                return false;
            } else if (!(Integer.parseInt(capacidadAsientos) > 1)) {
                System.out.println("Asientos");
                return false;
            } else if (!(Integer.parseInt(capacidadCarga) > 1)) {
                System.out.println("Carga");
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception");
            return false;
        }
    }

}
