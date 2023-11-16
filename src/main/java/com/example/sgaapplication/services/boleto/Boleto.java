package com.example.sgaapplication.services.boleto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String codigo;
    private String pasaporte;

    /**public Boleto(String codigo, String pasaporte) {
        this.codigo = codigo;
        this.pasaporte = pasaporte;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }**/
}