package com.example.sgaapplication.services.aerolinea;

import java.util.List;

import com.example.sgaapplication.services.aeropuerto.Aeropuerto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Aerolinea")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aerolinea {
    @Id
    private String codigoAerolinea;

    private String nombreAerolinea;
    private String pais;
    private String contrasena;

    @ManyToMany(mappedBy = "aerolineasHabilitadas")
    private List<Aeropuerto> aeropuertosHabilitados;
}
