package com.example.sgaapplication.controllers;

import org.springframework.stereotype.Controller;

import com.example.sgaapplication.services.vuelo.Vuelo;
import com.example.sgaapplication.services.vuelo.VueloSkeleton;

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
    public TableColumn<Vuelo, String> aerolineaColumna;

    @FXML
    public TableColumn<Vuelo, String> codigoColumna;

    @FXML
    public TableColumn<Vuelo, String> matriculaColumna;

    @FXML
    public TableColumn<Vuelo, String> origenColumna;

    @FXML
    public TableColumn<Vuelo, String> destinoColumna;

    @FXML
    public TableColumn<Vuelo, String> fechaSalidaColumna;

    @FXML
    public TableColumn<Vuelo, String> fechaLlegadaColumna;

    @FXML
    public TableColumn<Vuelo, String> horaSalidaColumna;

    @FXML
    public TableColumn<Vuelo, String> horaLlegadaColumna;

    @FXML
    public void initialize() {

    }
}
