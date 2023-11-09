package com.example.sgaapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryVuelo;
import com.example.sgaapplication.persistency.UserSession;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;
import com.example.sgaapplication.services.vuelo.ServiceVuelo;
import com.example.sgaapplication.services.vuelo.Vuelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AeropuertoTabbedWindow1.fxml")
public class AeropuertoTabbedWindow1Controller {

    public AeropuertoTabbedWindow1Controller() {
        System.out.println("=========HOLA==========");
    }

    @Autowired
    RepositoryVuelo repositoryVuelo;

    @Autowired
    ServiceVuelo serviceVuelo;

    @FXML
    private TableView<?> ConsultaTabla;

    @FXML
    private ToggleButton FuncionarioAlta;

    @FXML
    private ComboBox<String> FuncionarioCargo;

    @FXML
    private TextField FuncionarioIdentificador;

    @FXML
    private TextField FuncionarioNombre;

    @FXML
    private Button LocalAlta;

    @FXML
    private TextField LocalNombre;

    @FXML
    private Button ValidacionBoton;

    @FXML
    private TableView<Vuelo> ValidacionTabla;

    @FXML
    private TableColumn<Vuelo, String> aerolineaColumna;

    @FXML
    private TableColumn<Vuelo, String> codigoColumna;

    @FXML
    private TableColumn<Vuelo, String> matriculaColumna;

    @FXML
    private TableColumn<Vuelo, String> origenColumna;

    @FXML
    private TableColumn<Vuelo, String> destinoColumna;

    @FXML
    private TableColumn<Vuelo, String> fechaSalidaColumna;

    @FXML
    private TableColumn<Vuelo, String> fechaLlegadaColumna;

    @FXML
    private TableColumn<Vuelo, String> horaSalidaColumna;

    @FXML
    private TableColumn<Vuelo, String> horaLlegadaColumna;

    @FXML
    public void initialize() {
        UserSession loggedUser = UserSession.getInstance();

        List<Vuelo> vuelos = repositoryVuelo.findAll();

        ObservableList<Vuelo> vuelosObservable = FXCollections.observableArrayList(vuelos);

        ObservableList<Vuelo> vuelosEnTabla = FXCollections.observableArrayList();

        for (Vuelo vuelo : vuelosObservable) {
            if (vuelo.getAeropuertoOrigen().equals(loggedUser.getCodigo()) || vuelo.getAeropuertoDestino().equals(loggedUser.getCodigo())) {
                vuelosEnTabla.add(vuelo);
            }
        }

        ValidacionTabla.setItems(vuelosEnTabla);

        aerolineaColumna.setCellValueFactory(new PropertyValueFactory("aerolinea"));
        codigoColumna.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        matriculaColumna.setCellValueFactory(new PropertyValueFactory("matriculaAvion"));
        origenColumna.setCellValueFactory(new PropertyValueFactory("aeropuertoOrigen"));
        destinoColumna.setCellValueFactory(new PropertyValueFactory("aeropuertoDestino"));
        fechaSalidaColumna.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
        fechaLlegadaColumna.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));
        horaSalidaColumna.setCellValueFactory(new PropertyValueFactory("horaSalida"));
        horaLlegadaColumna.setCellValueFactory(new PropertyValueFactory("horaLlegada"));
    }
}
