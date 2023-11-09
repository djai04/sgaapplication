package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sgaapplication.services.vuelo.Vuelo;

public interface RepositoryVuelo extends JpaRepository<Vuelo, String> {
    Vuelo findByCodigoVuelo(String codigoVuelo);

    Vuelo findByAerolinea(String aerolinea);
}
