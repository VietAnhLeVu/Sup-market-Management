package com.example.supermarket;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertBox {
    private final Alert alert;

    public AlertBox(Alert.AlertType type, String title, String header, String content) {
        alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
    }

    public Optional<ButtonType> showAlert() {
        return alert.showAndWait();
    }
}
