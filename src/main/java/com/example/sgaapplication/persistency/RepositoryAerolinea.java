package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sgaapplication.services.aerolinea.Aerolinea;

public interface RepositoryAerolinea extends JpaRepository<Aerolinea, String> {
    Aerolinea findByCodigoAerolinea(String codigoAerolinea);
}
