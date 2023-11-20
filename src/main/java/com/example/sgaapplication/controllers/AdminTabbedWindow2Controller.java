package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryAerolinea;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AdminTabbedWindow2.fxml")
public class AdminTabbedWindow2Controller {

    @Autowired
    ConfigurableApplicationContext applicationContext;

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
    private Button LogoutButton;

    @FXML
    private void showError(String titulo, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void showSuccess(String titulo, String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void onAddAeropuertoButtonClick(ActionEvent event) {
        if (serviceAeropuerto.validarDatosAeropuerto(AeropuertoCodigo.getText(), AeropuertoNombre.getText(), AeropuertoPais.getText(), AeropuertoCiudad.getText())) {
            serviceAeropuerto.insertAeropuerto(AeropuertoCodigo.getText(), AeropuertoNombre.getText(), AeropuertoPais.getText(), AeropuertoCiudad.getText(), 0, 0);
            AeropuertoCodigo.clear();
            AeropuertoNombre.clear();
            AeropuertoPais.clear();
            AeropuertoCiudad.clear();
            AeropuertoCodigo.requestFocus();
            showSuccess("Alta de aeropuerto", "Aeropuerto dado de alta correctamente!", "");
        } else {
            showError("Error alta de aeropuerto", "No se pudo dar de alta el aeropuerto!", "Verificar que todos los datos cumplan con los estandares establecidos");
        }
    }

    @FXML
    void onAddAerolineaButtonClick(ActionEvent event) {
        if (serviceAerolinea.validarDatosAerolinea(AerolineaCodigo.getText(), AerolineaNombre.getText(), AerolineaPais.getText())) {
            serviceAerolinea.insertAerolinea(AerolineaCodigo.getText(), AerolineaNombre.getText(), AerolineaPais.getText());
            AerolineaCodigo.clear();
            AerolineaNombre.clear();
            AerolineaPais.clear();
            AerolineaCodigo.requestFocus();
            showSuccess("Alta de aerolinea", "Aerolinea dada de alta correctamente!", "");
        } else {
            showError("Error alta de aerolinea", "No se pudo dar de alta la aerolinea!", "Verificar que todos los datos cumplan con los estandares establecidos");
        }
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
        Parent root = fxweaver1.loadView(Login2Controller.class);
        Scene scene = LogoutButton.getScene();
        scene.setRoot(root);
    }
}
