package com.example.sgaapplication.services.avion;

import com.example.sgaapplication.services.aerolinea.Aerolinea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Avion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avion {
    @Id
    private String matricula;

    @Column(
            name = "capacidad",
            nullable = false
    )
    private String capacidad;

    @Column(
            name = "asientos",
            nullable = false
    )
    private String asientos;

    @Column(
            name = "estado",
            nullable = false
    )
    private String estado;

    @Column(
            name = "aerolinea",
            nullable = false
    )
    private String aerolinea;
    
}
