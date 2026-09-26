package com.csc311mod3lab2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {


    private static String registeredFirstName = "";

    @FXML private Rectangle barFill;

    @FXML private TextField firstNameField;



    @FXML private Label welcomeLabel;

    @FXML
    public void initialize() { // Runs on splash screen

        if (barFill != null) {
            Platform.runLater(this::runSplashAnimation);
        }


        if (welcomeLabel != null) {
            if (registeredFirstName != null && !registeredFirstName.trim().isEmpty()) {
                welcomeLabel.setText("Welcome, " + registeredFirstName.trim());
            } else {
                welcomeLabel.setText("Welcome, Student");
            }
        }
    }
        //fills up loading
    private void runSplashAnimation() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(barFill.widthProperty(), 0)),
                new KeyFrame(Duration.millis(1600), new KeyValue(barFill.widthProperty(), 160))
        );

        timeline.setOnFinished(event -> {
            try {
                if (barFill.getScene() == null || barFill.getScene().getWindow() == null) {
                    return;
                }
                Stage stage = (Stage) barFill.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/csc311mod3lab2/registration.fxml"));
                Parent root = loader.load();
                stage.setScene(new Scene(root, 800, 600));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        timeline.play();
    }

    @FXML
    public void handleCreateAccount(ActionEvent event) {

        if (firstNameField != null && !firstNameField.getText().isEmpty()) {
            registeredFirstName = firstNameField.getText().trim();
        }
        navigateTo("/com/csc311mod3lab2/landing.fxml", (Node) event.getSource());
    }

    @FXML
    public void signout(ActionEvent event) {
        Platform.exit();

    }

    private void navigateTo(String fxmlPath, Node sourceNode) {
        try {
            Stage stage = (Stage) sourceNode.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root, 800, 600));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}