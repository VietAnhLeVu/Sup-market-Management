package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EmployeeDashboard {
    @FXML
    AnchorPane titlePane;

    @FXML
    Button minimizeButton;

    @FXML
    public void initialize() {
        setDragAndDrop();
        setMinimize();
    }

    public void setDragAndDrop() {
        titlePane.setOnMousePressed(pressEvent -> titlePane.setOnMouseDragged(dragEvent -> {
            titlePane.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
            titlePane.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
        }));
    }

    public void setMinimize() {
        minimizeButton.setOnAction(actionEvent -> ((Stage) minimizeButton.getScene().getWindow()).setIconified(true));
    }

    public void close() {
        System.exit(0);
    }
}
