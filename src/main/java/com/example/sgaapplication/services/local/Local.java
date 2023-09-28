package com.example.sgaapplication.services.local;

import com.example.sgaapplication.services.aeropuerto.Aeropuerto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Local")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Local {
    @Id
    private String codigoLocal;

    private String nombreLocal;

    @ManyToOne
    private Aeropuerto aeropuertoDelLocal;
    private String contrasena;
}
