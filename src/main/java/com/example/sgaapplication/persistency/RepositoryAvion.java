package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sgaapplication.services.avion.Avion;

@Repository
public interface RepositoryAvion extends JpaRepository<Avion, String> {
    Avion findByMatricula(String matricula);

    Avion[] findByCapacidad(String capacidad);

    Avion[] findByAsientos(String asientos);

    Avion[] findByEstado(String estado);

    Avion[] findByAerolinea(String aerolinea);
}
