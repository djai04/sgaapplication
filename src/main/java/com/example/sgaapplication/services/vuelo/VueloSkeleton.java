package com.example.sgaapplication.services.vuelo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class VueloSkeleton {
    
    public SimpleStringProperty aerolineaColumna;

    public SimpleStringProperty codigoColumna;

    public SimpleStringProperty matriculaColumna;

    public SimpleStringProperty origenColumna;

    public SimpleStringProperty destinoColumna;

    public SimpleStringProperty fechaSalidaColumna;

    public SimpleStringProperty fechaLlegadaColumna;

    public SimpleStringProperty horaSalidaColumna;

    public SimpleStringProperty horaLlegadaColumna;

    public VueloSkeleton(String aerolineaColumna, String codigoColumna, String matriculaColumna, String origenColumna, String destinoColumna, String fechaSalidaColumna, String fechaLlegadaColumna, String horaSalidaColumna, String horaLlegadaColumna) {
        this.aerolineaColumna = new SimpleStringProperty(aerolineaColumna);

        this.codigoColumna = new SimpleStringProperty(codigoColumna);

        this.matriculaColumna = new SimpleStringProperty(matriculaColumna);

        this.origenColumna = new SimpleStringProperty(origenColumna);

        this.destinoColumna = new SimpleStringProperty(destinoColumna);

        this.fechaSalidaColumna = new SimpleStringProperty(fechaSalidaColumna);

        this.fechaLlegadaColumna = new SimpleStringProperty(fechaLlegadaColumna);

        this.horaSalidaColumna = new SimpleStringProperty(horaSalidaColumna);

        this.horaLlegadaColumna = new SimpleStringProperty(horaLlegadaColumna);
    }

    public String getAerolinea() {
        return aerolineaColumna.get();
    }

    public String getCodigo() {
        return codigoColumna.get();
    }

    public String getMatricula() {
        return matriculaColumna.get();
    }

    public String getOrigen() {
        return origenColumna.get();
    }

    public String getDestino() {
        return destinoColumna.get();
    }

    public String getFechaSalida() {
        return fechaSalidaColumna.get();
    }

    public String getFechaLlegada() {
        return fechaLlegadaColumna.get();
    }

    public String getHoraSalida() {
        return horaSalidaColumna.get();
    }

    public String getHoraLlegada() {
        return horaLlegadaColumna.get();
    }

    public void setCodigo(String codigo) {
        this.codigoColumna = new SimpleStringProperty(codigo);
    }

    public void setAerolinea(String aerolinea) {
        this.aerolineaColumna = new SimpleStringProperty(aerolinea);
    }

    public void setMatricula(String matricula) {
        this.matriculaColumna = new SimpleStringProperty(matricula);
    }

    public void setOrigen(String origen) {
        this.origenColumna = new SimpleStringProperty(origen);
    }

    public void setDestino(String destino) {
        this.destinoColumna = new SimpleStringProperty(destino);
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalidaColumna = new SimpleStringProperty(fechaSalida);
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegadaColumna = new SimpleStringProperty(fechaLlegada);
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalidaColumna = new SimpleStringProperty(horaSalida);
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegadaColumna = new SimpleStringProperty(horaLlegada);
    }
}
