package com.example.sgaapplication.services.vuelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.persistency.RepositoryVuelo;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;

import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
public class ServiceVuelo {

    @Autowired
    private RepositoryVuelo repositoryVuelo;

    @Autowired
    private RepositoryAeropuerto repositoryAeropuerto;

    public void saveVuelo(String codigoVuelo, String aerolinea, String origen, String destino, String matriculaAvion, String fechaSalida, String fechaLlegada, LocalTime horaSalida, LocalTime horaLlegada) {
        Vuelo alreadyExists = repositoryVuelo.findByCodigoVuelo(codigoVuelo);
        if (alreadyExists == null) {
            repositoryVuelo.save(new Vuelo(codigoVuelo, aerolinea, origen, destino, matriculaAvion, fechaSalida, fechaLlegada, horaSalida, horaLlegada, "00", "0", "0", "0", "0"));
        }
    }

    public boolean isOrigen(String codigoVuelo, String codigoAeropuerto) {

        Vuelo vuelo = repositoryVuelo.findByCodigoVuelo(codigoVuelo);

        if (vuelo.getAeropuertoOrigen().equals(codigoAeropuerto)) {
            return true;
        } else {
            return false;
        }
        
    }

    public void validarVuelo(String codigoVuelo, boolean origen, String pista, String puerta) {
        Vuelo vuelo = repositoryVuelo.findByCodigoVuelo(codigoVuelo);

        if (origen) {
            vuelo.setPistaOrigen(pista);
            vuelo.setPuertaOrigen(puerta);
            if (vuelo.getEstado().equals("00")) {
                vuelo.setEstado("10");
                repositoryVuelo.save(vuelo);
            } else if (vuelo.getEstado().equals("01")) {
                vuelo.setEstado("11");
                repositoryVuelo.save(vuelo);
            }
        } else {
            vuelo.setPistaDestino(pista);
            vuelo.setPuertaDestino(puerta);
            if (vuelo.getEstado().equals("00")) {
                vuelo.setEstado("01");
                repositoryVuelo.save(vuelo);
            } else if (vuelo.getEstado().equals("10")) {
                vuelo.setEstado("11");
                repositoryVuelo.save(vuelo);
            }
        }
    }

    public void denegarVuelo(String codigoVuelo) {
        Vuelo vuelo = repositoryVuelo.findByCodigoVuelo(codigoVuelo);
        vuelo.setEstado("22");
        repositoryVuelo.save(vuelo);
    }

    public boolean validarDatosVuelo(String codigoVuelo, String codigoAerolinea, String origen, String destino, String matricula, LocalDate fechaSalida, LocalDate fechaLlegada, String horaSalida, String horaLlegada) {

        if (!(fechaLlegada.isAfter(fechaSalida) || fechaLlegada.isEqual(fechaSalida))) {
            return false;
        } else if (timeParser(horaLlegada) == null || timeParser(horaSalida) == null) {
            return false;
        } else if (!(fechaSalida.isAfter(LocalDate.now()))) {
            return false;
        } else if (origen.equals(destino)) {
            return false;
        }

        LocalTime horaSalidaTime = timeParser(horaSalida);
        LocalTime horaLlegadaTime = timeParser(horaLlegada);
        LocalDateTime dateTimeSalida = fechaSalida.atTime(horaSalidaTime);
        LocalDateTime dateTimeLlegada = fechaLlegada.atTime(horaLlegadaTime);

        if (dateTimeSalida.isAfter(dateTimeLlegada)) {
            return false;
        }

        return true;
    }

    private LocalTime timeParser(String inputString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(inputString, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    private LocalDate dateParser(String inputString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(inputString, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Vuelo> getVuelosValidadosByAeropuerto(String codigoAeropuerto) {
        List<Vuelo> vuelos = repositoryVuelo.findAll();

        List<Vuelo> vuelosEnTabla = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getAeropuertoOrigen().equals(codigoAeropuerto) || vuelo.getAeropuertoDestino().equals(codigoAeropuerto)) {
                if (vuelo.getEstado().equals("11")) {
                    vuelosEnTabla.add(vuelo);
                }
            }
        }

        return vuelosEnTabla;
    }

    public List<Vuelo> getVuelosValidadosByAerolinea(String codigoAerolinea) {
        List<Vuelo> vuelos = repositoryVuelo.findAll();

        List<Vuelo> vuelosEnTabla = new ArrayList<>();

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getAerolinea().equals(codigoAerolinea)) {
                if (vuelo.getEstado().equals("11")) {
                    vuelosEnTabla.add(vuelo);
                }
            }
        }

        return vuelosEnTabla;
    }

    public Boolean validarAvionEnUso(String codigoAerolinea, String matricula, String horaSalida, String horaLlegada, LocalDate fechaSalida, LocalDate fechaLlegada) {
        List<Vuelo> vuelosValidados = getVuelosValidadosByAerolinea(codigoAerolinea);

        LocalTime horaSalidaTime = timeParser(horaSalida);
        LocalTime horaLlegadaTime = timeParser(horaLlegada);
        LocalDateTime dateTimeSalida = fechaSalida.atTime(horaSalidaTime);
        LocalDateTime dateTimeLlegada = fechaLlegada.atTime(horaLlegadaTime);

        for (Vuelo vueloValidado : vuelosValidados) {

            if (!vueloValidado.getMatriculaAvion().equals(matricula)) {
                continue;
            }

            LocalTime horaSalidaTimeValidada = vueloValidado.getHoraSalida();
            LocalTime horaLlegadaTimeValidada = vueloValidado.getHoraLlegada();
            
            LocalDate fechaSalidaValidado = dateParser(vueloValidado.getFechaSalida());
            LocalDate fechaLlegadaValidado = dateParser(vueloValidado.getFechaLlegada());

            LocalDateTime dateTimeSalidaValidado = fechaSalidaValidado.atTime(horaSalidaTimeValidada);
            LocalDateTime dateTimeLlegadaValidado = fechaLlegadaValidado.atTime(horaLlegadaTimeValidada);

            boolean terminaAntesEmpiece = dateTimeLlegada.isBefore(dateTimeSalidaValidado);
            boolean empiezaDespuesTermina = dateTimeSalida.isAfter(dateTimeLlegadaValidado);

            if (!(empiezaDespuesTermina || terminaAntesEmpiece)) {
                return false;
            }
        }
        
        return true;
    }

    public Boolean validarPistasPuertas(String codigoAeropuerto, String numeroPista, String numeroPuerta, boolean origen, String codigoVuelo) {

        try {
            int numeroPistaInt = Integer.parseInt(numeroPista);
            int numeroPuertaInt = Integer.parseInt(numeroPuerta);

            Aeropuerto esteAeropuerto = repositoryAeropuerto.findByCodigoAeropuerto(codigoAeropuerto);
            int cantidadPistas = esteAeropuerto.getCapacidadEstacionamiento();
            int cantidadPuertas = esteAeropuerto.getCapacidadPuertas();

            if (!(numeroPistaInt >= 1 && numeroPistaInt <= cantidadPistas)) {
                return false;
            } else if (!(numeroPuertaInt >= 1 && numeroPuertaInt <= cantidadPuertas)) {
                return false;
            }

            List<Vuelo> vuelosValidadosEnAeropuerto = getVuelosValidadosByAeropuerto(codigoAeropuerto);

            for (Vuelo vueloValidado : vuelosValidadosEnAeropuerto) {
                if (origen) {
                    if (vueloValidado.getPistaOrigen().equals(numeroPista) && validarSiVuelosInterfieren(codigoVuelo, vueloValidado.getCodigoVuelo())) {
                        return false;
                    }

                    if (vueloValidado.getPuertaOrigen().equals(numeroPuerta) && validarSiVuelosInterfieren(codigoVuelo, vueloValidado.getCodigoVuelo())) {
                        return false;
                    }
                } else {
                    if (vueloValidado.getPistaDestino().equals(numeroPista) && validarSiVuelosInterfieren(codigoVuelo, vueloValidado.getCodigoVuelo())) {
                        return false;
                    }

                    if (vueloValidado.getPuertaDestino().equals(numeroPuerta) && validarSiVuelosInterfieren(codigoVuelo, vueloValidado.getCodigoVuelo())) {
                        return false;
                    }
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean validarSiVuelosInterfieren(String codigoVueloNuevo, String codigoVueloViejo) {
        Vuelo vueloNuevo = repositoryVuelo.findByCodigoVuelo(codigoVueloNuevo);
        Vuelo vueloViejo = repositoryVuelo.findByCodigoVuelo(codigoVueloViejo);

        LocalTime horaSalidaVueloViejo = vueloViejo.getHoraSalida();
        LocalTime horaLlegadaVueloViejo = vueloViejo.getHoraLlegada();
        LocalDate fechaSalidaVueloViejo = dateParser(vueloViejo.getFechaSalida());
        LocalDate fechaLlegadaVueloViejo = dateParser(vueloViejo.getFechaLlegada());
        LocalDateTime fechaHoraSalidaVueloViejo = fechaSalidaVueloViejo.atTime(horaSalidaVueloViejo);
        LocalDateTime fechaHoraLlegadaVueloViejo = fechaLlegadaVueloViejo.atTime(horaLlegadaVueloViejo);

        LocalTime horaSalidaVueloNuevo = vueloNuevo.getHoraSalida();
        LocalTime horaLlegadaVueloNuevo = vueloNuevo.getHoraLlegada();
        LocalDate fechaSalidaVueloNuevo = dateParser(vueloNuevo.getFechaSalida());
        LocalDate fechaLlegadaVueloNuevo = dateParser(vueloNuevo.getFechaLlegada());
        LocalDateTime fechaHoraSalidaVueloNuevo = fechaSalidaVueloNuevo.atTime(horaSalidaVueloNuevo);
        LocalDateTime fechaHoraLlegadaVueloNuevo = fechaLlegadaVueloNuevo.atTime(horaLlegadaVueloNuevo);

        boolean terminaAntesEmpiece = fechaHoraLlegadaVueloNuevo.isBefore(fechaHoraSalidaVueloViejo);
        boolean empiezaDespuesTermina = fechaHoraSalidaVueloNuevo.isAfter(fechaHoraLlegadaVueloViejo);

        return !(terminaAntesEmpiece || empiezaDespuesTermina);
    }

}
