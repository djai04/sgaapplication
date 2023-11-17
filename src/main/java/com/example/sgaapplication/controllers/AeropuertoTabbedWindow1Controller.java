package com.example.sgaapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.example.sgaapplication.persistency.RepositoryAerolinea;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.persistency.RepositoryVuelo;
import com.example.sgaapplication.persistency.UserSession;
import com.example.sgaapplication.services.aerolinea.Aerolinea;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aeropuerto.Aeropuerto;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;
import com.example.sgaapplication.services.vuelo.ServiceVuelo;
import com.example.sgaapplication.services.vuelo.Vuelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AeropuertoTabbedWindow1.fxml")
public class AeropuertoTabbedWindow1Controller {

    public AeropuertoTabbedWindow1Controller() {
        
    }

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Autowired
    RepositoryVuelo repositoryVuelo;

    @Autowired
    ServiceVuelo serviceVuelo;

    @Autowired
    RepositoryAerolinea repositoryAerolinea;

    @Autowired
    ServiceAerolinea serviceAerolinea;

    @Autowired
    RepositoryAeropuerto repositoryAeropuerto;

    @Autowired
    ServiceAeropuerto serviceAeropuerto;

    @FXML
    private TableView<?> ConsultaTabla;

    @FXML
    private ToggleButton FuncionarioAlta;

    @FXML
    private Button LogoutButton;

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
    private ComboBox<String> aerolineasHabilitadasCombo;

    @FXML
    private TextField PuertaCapacidad;

    @FXML
    private TextField PistaCapacidad;

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

        List<Aerolinea> aerolineas = repositoryAerolinea.findAll();

        aerolineasHabilitadasCombo.getItems().removeAll(aerolineasHabilitadasCombo.getItems());
        
        List<String> aerolineasYaHabilitadas = serviceAeropuerto.getAerolineasAsociadas(loggedUser.getCodigo());
        
        for (Aerolinea aerolinea : aerolineas) {
            if (!aerolineasYaHabilitadas.contains(aerolinea.getCodigoAerolinea())) {
                aerolineasHabilitadasCombo.getItems().add(aerolinea.getCodigoAerolinea());
            }
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
    void onHabilitarAerolineaButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();
        serviceAeropuerto.asociarAeropuertoAerolinea(loggedUser.getCodigo(), aerolineasHabilitadasCombo.getValue());
    }

    @FXML
    void onModificarPuertasButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();
        serviceAeropuerto.modificarPuertas(loggedUser.getCodigo(), Integer.parseInt(PuertaCapacidad.getText()));
        PuertaCapacidad.clear();
        PuertaCapacidad.requestFocus();
    }

    @FXML
    void onModificarPistasButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();
        serviceAeropuerto.modificarPistas(loggedUser.getCodigo(), Integer.parseInt(PistaCapacidad.getText()));
        PistaCapacidad.clear();
        PistaCapacidad.requestFocus();
    }
}
