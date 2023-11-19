package com.example.sgaapplication.services.vuelo;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Vuelo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vuelo {
    @Id
    private String codigoVuelo;

    private String aerolinea;
    private String aeropuertoOrigen;
    private String aeropuertoDestino;
    private String matriculaAvion;
    private String fechaSalida;
    private String fechaLlegada;
    private LocalTime horaSalida;
    private LocalTime horaLlegada;
    private String estado;
    // 00 = No aprobado por origen ni destino
    // 10 = Aprobado por origen
    // 01 = Aprobado por destino
    // 11 = Aprobado por origen y por destino
    // 22 = Denegado por origen o por destino

    private String pistaOrigen;
    private String puertaOrigen;

    private String pistaDestino;
    private String puertaDestino;
}
