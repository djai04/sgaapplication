package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sgaapplication.services.local.Local;

public interface RepositoryLocal extends JpaRepository<Local, String> {
    Local findByCodigoLocal(String codigoLocal);
}
