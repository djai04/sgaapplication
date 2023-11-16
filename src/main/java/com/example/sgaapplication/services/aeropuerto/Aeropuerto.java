package com.example.sgaapplication.services.aeropuerto;

import java.util.List;

import com.example.sgaapplication.services.aerolinea.Aerolinea;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
    @JoinTable(
        name = "aerolineas_aeropuertos_habilitar", 
        joinColumns = @JoinColumn(name = "codigoAeropuerto"), 
        inverseJoinColumns = @JoinColumn(name = "codigoAerolinea"))
    private List<Aerolinea> aerolineasHabilitadas;
}
