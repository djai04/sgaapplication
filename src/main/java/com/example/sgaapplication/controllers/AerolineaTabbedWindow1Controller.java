package com.example.sgaapplication.controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.persistency.RepositoryAvion;
import com.example.sgaapplication.persistency.RepositoryBoleto;
import com.example.sgaapplication.persistency.RepositoryVuelo;
import com.example.sgaapplication.persistency.UserSession;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;
import com.example.sgaapplication.services.aerolinea.Aerolinea;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;
import com.example.sgaapplication.services.avion.Avion;
import com.example.sgaapplication.services.avion.ServiceAvion;
import com.example.sgaapplication.services.boleto.ServiceBoleto;
import com.example.sgaapplication.services.vuelo.ServiceVuelo;
import com.example.sgaapplication.services.vuelo.Vuelo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
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

    @Autowired
    RepositoryVuelo repositoryVuelo;

    @Autowired
    ServiceVuelo serviceVuelo;

    @Autowired
    ServiceBoleto serviceBoleto;

    @Autowired
    RepositoryBoleto repositoryBoleto;


    @Autowired
    ConfigurableApplicationContext applicationContext;

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
    private Button LogoutButton;

    @FXML
    private Button agregarBoletoButton;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField pasaporteTextField;

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
    public void initialize() {
        UserSession loggedUser = UserSession.getInstance();

        List<Aeropuerto> aeropuertos = repositoryAeropuerto.findAll();
        VueloOrigenCombo.getItems().removeAll(VueloOrigenCombo.getItems());
        VueloDestinoCombo.getItems().removeAll(VueloDestinoCombo.getItems());
        
        for (Aeropuerto aeropuerto : aeropuertos) {
            List<String> aerolineasEnAeropuerto = serviceAeropuerto.getAerolineasAsociadas(aeropuerto.getCodigoAeropuerto());

            if (aerolineasEnAeropuerto.contains(loggedUser.getCodigo())) {
                VueloOrigenCombo.getItems().add(aeropuerto.getCodigoAeropuerto());
                VueloDestinoCombo.getItems().add(aeropuerto.getCodigoAeropuerto());
            }

        }

        List<Avion> aviones = repositoryAvion.findAll();
        VueloAvionCombo.getItems().removeAll(VueloAvionCombo.getItems());

        for (Avion avion : aviones) {
            if (avion.getAerolinea().equals(loggedUser.getCodigo())) {
                VueloAvionCombo.getItems().add(avion.getMatricula());
            }
        }
    }

    @FXML
    void onAddAvionButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();

        if (serviceAvion.validarDatosAvion(AvionMatricula.getText(), AvionCapacidadAsientos.getText(), AvionCapacidadCarga.getText())) {
            serviceAvion.saveAvion(AvionMatricula.getText(), AvionCapacidadCarga.getText(), AvionCapacidadAsientos.getText(), loggedUser.getCodigo());
            AvionCapacidadAsientos.clear();
            AvionCapacidadCarga.clear();
            AvionMatricula.clear();
            AvionMatricula.requestFocus();
            showSuccess("Alta de avion", "Alta de avion exitosa!", "El avion fue dado de alta correctamente.");
        } else {
            showError("Error alta avion", "Datos invalidos en alta avion", "Matricula: Un numero y 5 mayusculas.");
        }

    }

    @FXML
    void onAddVueloButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();

        String flightCode = loggedUser.getCodigo() + VueloAvionCombo.getValue() + VueloHoraSalida.getText();

        if (serviceVuelo.validarDatosVuelo(flightCode, loggedUser.getCodigo(), VueloOrigenCombo.getValue(), VueloDestinoCombo.getValue(), VueloAvionCombo.getValue(), VueloFechaSalida.getValue(), VueloFechaLlegada.getValue(), VueloHoraSalida.getText(), VueloHoraLlegada.getText())) {
            if (serviceVuelo.validarAvionEnUso(loggedUser.getCodigo(), VueloAvionCombo.getValue(), VueloHoraSalida.getText(), VueloHoraLlegada.getText(), VueloFechaSalida.getValue(), VueloFechaLlegada.getValue())) {
                serviceVuelo.saveVuelo(flightCode, loggedUser.getCodigo(), VueloOrigenCombo.getValue(), VueloDestinoCombo.getValue(), VueloAvionCombo.getValue(), VueloFechaSalida.getValue().toString(), VueloFechaLlegada.getValue().toString(), timeParser(VueloHoraSalida.getText()), timeParser(VueloHoraLlegada.getText()));
                showSuccess("Alta vuelo avion", "Alta de vuelo exitosa!", "El vuelo fue dado de alta correctamente.");
            } else {
                showError("Error alta vuelo", "No se pudo dar de alta el vuelo!", "El avión elegido está ocupado en ese horario..");
            }
        } else {
            showError("Error alta vuelo", "No se pudo dar de alta el vuelo!", "Alguna de los datos tiene un formato erroneo.");
        }

        VueloHoraLlegada.clear();
        VueloHoraSalida.clear();
    }

    private LocalTime timeParser(String inputString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            return LocalTime.parse(inputString, formatter);
        } catch (Exception ex) {
            return null;
        }
    }

    @FXML
    void onLogoutButtonClick(ActionEvent event) {
        FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
        Parent root = fxweaver1.loadView(Login2Controller.class);
        Scene scene = LogoutButton.getScene();
        scene.setRoot(root);
    }

    @FXML
    void onAgregarBoletoButtonClick() {
        String codigo = codigoTextField.getText();
        String pasaporte = pasaporteTextField.getText();

        serviceBoleto.saveBoleto(codigo, pasaporte);

        codigoTextField.clear();
        pasaporteTextField.clear();

        codigoTextField.requestFocus();
    }

}
