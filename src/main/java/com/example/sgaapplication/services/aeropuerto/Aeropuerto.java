package com.example.sgaapplication.services.aeropuerto;

import java.util.List;

import com.example.sgaapplication.services.aerolinea.Aerolinea;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Aeropuerto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aeropuerto {
    @Id
    private String codigoAeropuerto;

    private String nombreAeropuerto;
    private String pais;
    private String ciudad;
    private int capacidadPuertas;
    private int capacidadEstacionamiento;
    private String contrasena;

    @ManyToMany
    private List<Aerolinea> aerolineasHabilitadas;
}
