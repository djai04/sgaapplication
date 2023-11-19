package com.example.sgaapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import com.example.sgaapplication.persistency.RepositoryAerolinea;
import com.example.sgaapplication.persistency.RepositoryAeropuerto;
import com.example.sgaapplication.persistency.UserSession;
import com.example.sgaapplication.services.aerolinea.ServiceAerolinea;
import com.example.sgaapplication.services.aeropuerto.ServiceAeropuerto;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    private void showError(String titulo, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

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
                    UserSession usr = UserSession.getInstance();
                    usr.setCodigo(usernameInput.getText());
                    FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
                    Parent root = fxweaver1.loadView(AeropuertoTabbedWindow1Controller.class);
                    Scene scene = loginButton.getScene();
                    scene.setRoot(root);
                } else {
                    System.out.println("Contraseña incorrecta");
                    showError("Error en el login", "Contraseña incorrecta!", "La contraseña ingresada no corresponde a ningun aeropuerto.");
                }
            } else {
                System.out.println("No existe aeropuerto.");
                showError("Error en el login", "Usuario no existe!", "No existe ningun aeropuerto con ese codigo.");
            }
        } else if (typeInput.getValue().equals("Aerolinea")) {
            if (repositoryAerolinea.existsById(usernameInput.getText())) {
                if (repositoryAerolinea.findByCodigoAerolinea(usernameInput.getText()).getContrasena().equals(passwordInput.getText())) {
                    UserSession usr = UserSession.getInstance();
                    usr.setCodigo(usernameInput.getText());
                    FxWeaver fxweaver1 = applicationContext.getBean(FxWeaver.class);
                    Parent root = fxweaver1.loadView(AerolineaTabbedWindow1Controller.class);
                    Scene scene = loginButton.getScene();
                    scene.setRoot(root);
                } else {
                    System.out.println("Contraseña incorrecta");
                    showError("Error en el login", "Contraseña incorrecta!", "La contraseña ingresada no corresponde a ninguna aerolinea.");
                }
            } else {
                System.out.println("No existe aeropuerto.");
                showError("Error en el login", "Usuario no existe!", "No existe ninguna aerolinea con ese codigo.");
            }
        } else {
            System.out.println("Login fallado");
            showError("Error en el login", "Error inesperado", "Hubo un error inesperado al intentar iniciar sesión.");
        }
    }
}