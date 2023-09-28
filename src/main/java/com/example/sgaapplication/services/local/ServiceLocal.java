package com.example.sgaapplication.services.local;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryLocal;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;

@Service
public class ServiceLocal {
    
    @Autowired
    private RepositoryLocal repositoryLocal;

    public Local insertLocal(String codigoLocal, String nombreLocal, Aeropuerto aeropuertoDelLocal) {
        Local local = new Local(codigoLocal, nombreLocal, aeropuertoDelLocal, codigoLocal);

        return repositoryLocal.save(local);
    }
}
