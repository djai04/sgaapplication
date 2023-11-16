package com.example.sgaapplication.services.pasajero;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Pasajero")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {
    @Id
    private String pasaporte;

    private String nombre;
}
