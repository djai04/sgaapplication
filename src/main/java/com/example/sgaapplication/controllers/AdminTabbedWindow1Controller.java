package com.example.sgaapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryCliente;
import com.example.sgaapplication.services.cliente.ServiceCliente;

import jakarta.transaction.UserTransaction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AdminTabbedWindow1.fxml")
public class AdminTabbedWindow1Controller {
    
    @Autowired
    ServiceCliente serviceCliente;

    @Autowired
    RepositoryCliente repositoryCliente;

    @FXML
    private Button AddUserButton;

    @FXML
    private Tab AddUserTab;

    @FXML
    private Tab QueryUserTab;

    @FXML
    private TextField UsernameField;

    @FXML
    private ComboBox<String> UsertypeField;

    @FXML
    public void initialize() {
        UsertypeField.getItems().removeAll(UsertypeField.getItems());
        UsertypeField.setValue("Aeropuerto");
        UsertypeField.getItems().addAll("Aeropuerto", "Aerolinea", "Local", "Viajero", "Administrador");
    }

    @FXML
    void onAddUserButtonClick(ActionEvent event) {
        serviceCliente.insertCliente(UsernameField.getText(), UsernameField.getText(), UsertypeField.getValue());
        System.out.println(repositoryCliente.findByPasaporte(UsernameField.getText()).getId());
        System.out.println(repositoryCliente.findByPasaporte(UsernameField.getText()).getPasaporte());
        System.out.println(repositoryCliente.findByPasaporte(UsernameField.getText()).getTipo());
        UsernameField.clear();
        UsernameField.requestFocus();
    }
}
