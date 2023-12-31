package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sgaapplication.services.pasajero.Pasajero;

@Repository
public interface RepositoryPasajero extends JpaRepository<Pasajero, String> {
   
}
