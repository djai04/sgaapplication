package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryAerolinea;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AdminTabbedWindow2.fxml")
public class AdminTabbedWindow2Controller {

    @Autowired
    ServiceAeropuerto serviceAeropuerto;

    @Autowired
    RepositoryAeropuerto repositoryAeropuerto;

    @Autowired
    ServiceAerolinea serviceAerolinea;

    @Autowired
    RepositoryAerolinea repositoryAerolinea;

    @FXML
    private Button AerolineaAlta;

    @FXML
    private TextField AerolineaCodigo;

    @FXML
    private TextField AerolineaNombre;

    @FXML
    private TextField AerolineaPais;

    @FXML
    private Button AeropuertoAlta;

    @FXML
    private TextField AeropuertoCiudad;

    @FXML
    private TextField AeropuertoCodigo;

    @FXML
    private TextField AeropuertoNombre;

    @FXML
    private TextField AeropuertoPais;

    @FXML
    void onAddAeropuertoButtonClick(ActionEvent event) {
        serviceAeropuerto.insertAeropuerto(AeropuertoCodigo.getText(), AeropuertoNombre.getText(), AeropuertoPais.getText(), AeropuertoCiudad.getText(), 0, 0);
        AeropuertoCodigo.clear();
        AeropuertoNombre.clear();
        AeropuertoPais.clear();
        AeropuertoCiudad.clear();
        AeropuertoCodigo.requestFocus();
    }

    @FXML
    void onAddAerolineaButtonClick(ActionEvent event) {
        serviceAerolinea.insertAerolinea(AerolineaCodigo.getText(), AerolineaNombre.getText(), AerolineaPais.getText());
        AerolineaCodigo.clear();
        AerolineaNombre.clear();
        AerolineaPais.clear();
        AerolineaCodigo.requestFocus();
    }
}
