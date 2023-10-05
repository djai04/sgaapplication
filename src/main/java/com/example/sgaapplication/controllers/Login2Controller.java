package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.services.cliente.ServiceCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Controller
@FxmlView("LoginWindow2.fxml")
public class Login2Controller {

    public Login2Controller() {
        System.out.println("Hola");
    }

    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Autowired
    ServiceCliente serviceCliente;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        /** serviceCliente.insertCliente(usernameInput.getText(), passwordInput.getText());
        usernameInput.clear();
        passwordInput.clear();
        usernameInput.requestFocus(); **/
        if (usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
            FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
            Parent root = fxweaver1.loadView(AdminTabbedWindow2Controller.class);
            Scene scene = loginButton.getScene();
            scene.setRoot(root);
        } else {
            System.out.println("Login fallado");
        }
    }
}