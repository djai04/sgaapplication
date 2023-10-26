package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;

public interface RepositoryAeropuerto extends JpaRepository<Aeropuerto, String>{
    Aeropuerto findByCodigoAeropuerto(String codigoAeropuerto);
}
