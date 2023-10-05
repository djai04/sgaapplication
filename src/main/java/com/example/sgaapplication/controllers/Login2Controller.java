package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryAerolinea;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;
import com.example.sgaapplication.services.cliente.ServiceCliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    // @Autowired
    // ServiceCliente serviceCliente;

    @Autowired
    ServiceAeropuerto serviceAeropuerto;

    @Autowired
    RepositoryAeropuerto repositoryAeropuerto;

    @Autowired
    ServiceAerolinea serviceAerolinea;

    @Autowired
    RepositoryAerolinea repositoryAerolinea;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private ComboBox<String> typeInput;

    @FXML
    public void initialize() {
        typeInput.getItems().removeAll(typeInput.getItems());
        typeInput.setValue("Aeropuerto");
        typeInput.getItems().addAll("Aeropuerto", "Aerolinea", "Admin");
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        if (usernameInput.getText().equals("admin") && passwordInput.getText().equals("admin")) {
            FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
            Parent root = fxweaver1.loadView(AdminTabbedWindow2Controller.class);
            Scene scene = loginButton.getScene();
            scene.setRoot(root);
        } else if (typeInput.getValue().equals("Aeropuerto")) {
            if (repositoryAeropuerto.existsById(usernameInput.getText())) {
                if (repositoryAeropuerto.findByCodigoAeropuerto(usernameInput.getText()).getContrasena().equals(passwordInput.getText())) {
                    FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
                    Parent root = fxweaver1.loadView(AeropuertoTabbedWindow1Controller.class);
                    Scene scene = loginButton.getScene();
                    scene.setRoot(root);
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            } else {
                System.out.println("No existe aeropuerto.");
            }
        } else if (typeInput.getValue().equals("Aerolinea")) {
            if (repositoryAerolinea.existsById(usernameInput.getText())) {
                if (repositoryAerolinea.findByCodigoAerolinea(usernameInput.getText()).getContrasena().equals(passwordInput.getText())) {
                    FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
                    Parent root = fxweaver1.loadView(AerolineaTabbedWindow1Controller.class);
                    Scene scene = loginButton.getScene();
                    scene.setRoot(root);
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            } else {
                System.out.println("No existe aeropuerto.");
            }
        } else {
            System.out.println("Login fallado");
        }
    }
}