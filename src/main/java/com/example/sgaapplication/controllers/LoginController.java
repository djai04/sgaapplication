package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.services.cliente.ServiceCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("LoginWindow.fxml")
public class LoginController {

    @Autowired
    ServiceCliente serviceCliente;

    @FXML
    private Label errorLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordInputText;

    @FXML
    private TextField userInputText;

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        serviceCliente.insertCliente(userInputText.getText(), passwordInputText.getText(), "");
        userInputText.clear();
        passwordInputText.clear();
        errorLabel.setText("Usuario creado correctamente!");
        userInputText.requestFocus();
    }
}
