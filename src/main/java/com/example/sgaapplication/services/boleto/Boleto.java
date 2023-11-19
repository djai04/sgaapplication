package com.example.sgaapplication.services.boleto;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "boletos")
@Entity(name = "Boleto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Boleto {
    @Id
    private String identificador;

    private String codigo;
    private String pasaporte;
}