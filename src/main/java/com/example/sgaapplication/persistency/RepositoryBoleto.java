package com.example.sgaapplication.persistency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sgaapplication.services.boleto.*;
import java.util.List;



@Repository
public interface RepositoryBoleto extends JpaRepository<Boleto, String> {
    Boleto findByIdentificador(String identificador);

    Boleto findAllByCodigo(String codigo);
}