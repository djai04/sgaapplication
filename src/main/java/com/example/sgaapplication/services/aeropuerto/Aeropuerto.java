package com.example.sgaapplication.services.aeropuerto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
}
