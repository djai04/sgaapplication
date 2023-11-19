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

import ch.qos.logback.core.joran.conditional.IfAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private Button DenegarBoton;

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
    private TableView<Vuelo> ConsultaTabla;

    @FXML
    private TableColumn<Vuelo, String> aerolineaColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> codigoColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> matriculaColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> origenColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> destinoColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> fechaSalidaColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> fechaLlegadaColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> horaSalidaColumnaConsulta;

    @FXML
    private TableColumn<Vuelo, String> horaLlegadaColumnaConsulta;

    @FXML
    private ComboBox<String> aerolineasHabilitadasCombo;

    @FXML
    private TextField PuertaCapacidad;

    @FXML
    private TextField PistaCapacidad;

    @FXML
    private TextField PistaAsignada;

    @FXML
    private TextField PuertaAsignada;

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

        // Inicio de poblacion de tabla de vuelos a validar
        List<Vuelo> vuelos = repositoryVuelo.findAll();

        ObservableList<Vuelo> vuelosObservable = FXCollections.observableArrayList(vuelos);

        ObservableList<Vuelo> vuelosEnTabla = FXCollections.observableArrayList();

        for (Vuelo vuelo : vuelosObservable) {
            if ((vuelo.getAeropuertoOrigen().equals(loggedUser.getCodigo()) || vuelo.getAeropuertoDestino().equals(loggedUser.getCodigo())) && (!vuelo.getEstado().equals("11") && !vuelo.getEstado().equals("22"))) {
                if (!(serviceVuelo.isOrigen(vuelo.getCodigoVuelo(), loggedUser.getCodigo()) && vuelo.getEstado().equals("10")) && !(!serviceVuelo.isOrigen(vuelo.getCodigoVuelo(), loggedUser.getCodigo()) && vuelo.getEstado().equals("01"))) {
                    vuelosEnTabla.add(vuelo);
                }
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
        // Fin de poblacion de tabla de vuelos a validar

        // Inicio de poblacion de tabla de vuelos confirmados
        List<Vuelo> vuelosConfirmados = repositoryVuelo.findAll();

        ObservableList<Vuelo> vuelosObservableConfirmados = FXCollections.observableArrayList(vuelos);

        ObservableList<Vuelo> vuelosEnTablaConfirmados = FXCollections.observableArrayList();

        for (Vuelo vuelo : vuelosObservableConfirmados) {
            if ((vuelo.getAeropuertoOrigen().equals(loggedUser.getCodigo()) || vuelo.getAeropuertoDestino().equals(loggedUser.getCodigo())) && vuelo.getEstado().equals("11")) {
                vuelosEnTablaConfirmados.add(vuelo);
            }
        }

        ConsultaTabla.setItems(vuelosEnTablaConfirmados);

        aerolineaColumnaConsulta.setCellValueFactory(new PropertyValueFactory("aerolinea"));
        codigoColumnaConsulta.setCellValueFactory(new PropertyValueFactory("codigoVuelo"));
        matriculaColumnaConsulta.setCellValueFactory(new PropertyValueFactory("matriculaAvion"));
        origenColumnaConsulta.setCellValueFactory(new PropertyValueFactory("aeropuertoOrigen"));
        destinoColumnaConsulta.setCellValueFactory(new PropertyValueFactory("aeropuertoDestino"));
        fechaSalidaColumnaConsulta.setCellValueFactory(new PropertyValueFactory("fechaSalida"));
        fechaLlegadaColumnaConsulta.setCellValueFactory(new PropertyValueFactory("fechaLlegada"));
        horaSalidaColumnaConsulta.setCellValueFactory(new PropertyValueFactory("horaSalida"));
        horaLlegadaColumnaConsulta.setCellValueFactory(new PropertyValueFactory("horaLlegada"));
        // Fin de poblacion de tabla de vuelos confirmados

        // Inicio de poblacion de combobox de aerolineas
        List<Aerolinea> aerolineas = repositoryAerolinea.findAll();

        aerolineasHabilitadasCombo.getItems().removeAll(aerolineasHabilitadasCombo.getItems());
        
        List<String> aerolineasYaHabilitadas = serviceAeropuerto.getAerolineasAsociadas(loggedUser.getCodigo());
        
        for (Aerolinea aerolinea : aerolineas) {
            if (!aerolineasYaHabilitadas.contains(aerolinea.getCodigoAerolinea())) {
                aerolineasHabilitadasCombo.getItems().add(aerolinea.getCodigoAerolinea());
            }
        }
        // Fin de poblacion de combobox de aerolineas

        // Inicio placeholder
        PuertaAsignada.setPromptText("Puerta");
        PistaAsignada.setPromptText("Pista");
        // Final placeholder
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
        showSuccess("Asociación correcta", "La asociación ha sido exitosa!", "La aerolinea ha sido asociada correctamente.");
    }

    @FXML
    void onModificarPuertasButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();

        try {
            serviceAeropuerto.modificarPuertas(loggedUser.getCodigo(), Integer.parseInt(PuertaCapacidad.getText()));
            showSuccess("Modificación correcta", "La modificación ha sido exitosa!", "La cantidad de puertas fue cambiada.");
            PuertaCapacidad.clear();
            PuertaCapacidad.requestFocus();
        } catch (Exception e) {
            // TODO: handle exception
            showError("Error al modificar", "No se modificaron las puertas", "La cantidad de puertas debe ser un numero.");
        }
    }

    @FXML
    void onModificarPistasButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();

        try {
            serviceAeropuerto.modificarPistas(loggedUser.getCodigo(), Integer.parseInt(PistaCapacidad.getText()));
            showSuccess("Modificación correcta", "La modificación ha sido exitosa!", "La cantidad de pistas fue cambiada.");
            PistaCapacidad.clear();
            PistaCapacidad.requestFocus();
        } catch (Exception e) {
            // TODO: handle exception
            showError("Error al modificar", "No se modificaron las puertas", "La cantidad de puertas debe ser un numero.");

        }
    }

    @FXML
    void onValidarSeleccionButtonClick(ActionEvent event) {
        UserSession loggedUser = UserSession.getInstance();
        String vueloSeleccionado = ValidacionTabla.getSelectionModel().getSelectedItem().getCodigoVuelo();

        if (serviceVuelo.validarPistasPuertas(loggedUser.getCodigo(), PistaAsignada.getText(), PuertaAsignada.getText(), serviceVuelo.isOrigen(vueloSeleccionado, loggedUser.getCodigo()), vueloSeleccionado)) {
            serviceVuelo.validarVuelo(vueloSeleccionado, serviceVuelo.isOrigen(vueloSeleccionado, loggedUser.getCodigo()), PistaAsignada.getText(), PuertaAsignada.getText());
            showSuccess("Validación correcta", "La validación ha sido exitosa!", "El vuelo fue validado por este aeropuerto.");
        } else {
            showError("Error al validar vuelo", "El vuelo no pudo ser validado!", "No está disponible esa pista o puerta.");
        }
    }

    @FXML
    void onDenegarSeleccionButtonClick(ActionEvent event) {
        String vueloSeleccionado = ValidacionTabla.getSelectionModel().getSelectedItem().getCodigoVuelo();
        serviceVuelo.denegarVuelo(vueloSeleccionado);
        showSuccess("Denegación correcta", "La denegación ha sido exitosa!", "El vuelo fue denegado por este aeropuerto.");
    }
}
