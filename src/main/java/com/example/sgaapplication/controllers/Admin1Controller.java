package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.services.cliente.ServiceCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("AdminWindow1.fxml")
public class Admin1Controller {
    @Autowired
    ServiceCliente serviceCliente;

    @FXML
    private Button addButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    void onAddButtonClick(ActionEvent event) {
        serviceCliente.insertCliente(usernameInput.getText(), passwordInput.getText(), "");
        usernameInput.clear();
        passwordInput.clear();
        usernameInput.requestFocus();
    }
}
