package com.example.sgaapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.persistency.RepositoryAvion;
import com.example.sgaapplication.persistency.UserSession;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;
import com.example.sgaapplication.services.avion.Avion;
import com.example.sgaapplication.services.avion.ServiceAvion;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AerolineaTabbedWindow1.fxml")
public class AerolineaTabbedWindow1Controller {
    @Autowired
    RepositoryAvion repositoryAvion;

    @Autowired
    ServiceAvion serviceAvion;

    @Autowired
    RepositoryAeropuerto repositoryAeropuerto;

    @Autowired
    ServiceAeropuerto serviceAeropuerto;

    @FXML
    private Button AvionAlta;

    @FXML
    private TextField AvionCapacidadAsientos;

    @FXML
    private TextField AvionCapacidadCarga;

    @FXML
    private TextField AvionMatricula;

    @FXML
    private Button VueloAlta;

    @FXML
    private ComboBox<String> VueloAvionCombo;

    @FXML
    private ComboBox<String> VueloOrigenCombo;

    @FXML
    private ComboBox<String> VueloDestinoCombo;

    @FXML
    private DatePicker VueloFechaLlegada;

    @FXML
    private DatePicker VueloFechaSalida;

    @FXML
    private TextField VueloHoraLlegada;

    @FXML
    private TextField VueloHoraSalida;

    @FXML
    public void initialize() {
        List<Aeropuerto> aeropuertos = repositoryAeropuerto.findAll();
        VueloOrigenCombo.getItems().removeAll(VueloOrigenCombo.getItems());
        VueloDestinoCombo.getItems().removeAll(VueloDestinoCombo.getItems());
        
        for (Aeropuerto aeropuerto : aeropuertos) {
            VueloOrigenCombo.getItems().add(aeropuerto.getNombreAeropuerto());
            VueloDestinoCombo.getItems().add(aeropuerto.getNombreAeropuerto());
        }

        List<Avion> aviones = repositoryAvion.findAll();
        VueloAvionCombo.getItems().removeAll(VueloAvionCombo.getItems());

        for (Avion avion : aviones) {
            VueloAvionCombo.getItems().add(avion.getMatricula());
        }
    }

    @FXML
    void onAddAvionButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();
        System.out.println(loggedUser.getCodigo());
        serviceAvion.saveAvion(AvionMatricula.getText(), AvionCapacidadCarga.getText(), AvionCapacidadAsientos.getText(), loggedUser.getCodigo());
        AvionCapacidadAsientos.clear();
        AvionCapacidadCarga.clear();
        AvionMatricula.clear();
        AvionMatricula.requestFocus();
    }

    @FXML
    void onAddVueloButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();

    }
}
