package com.example.supermarket;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.supermarket.LoginModel.*;

public class LoginController {

    @FXML
    private AnchorPane colorPane;
    @FXML
    private TextField username;
    @FXML
    private AnchorPane main_form;
    @FXML
    private Label label;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Hyperlink hyperlink;
    @FXML
    private StackPane window;
    private double x = 0;
    private double y = 0;

    @FXML
    public void initialize() {
        window.setOnMousePressed(pressEvent -> {
            window.setOnMouseDragged(dragEvent -> {
                window.getScene().getWindow().setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                window.getScene().getWindow().setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                window.getScene().getWindow().setOpacity(0.8);
            });
            window.setOnMouseReleased(releaseEvent -> window.getScene().getWindow().setOpacity(1));
        });
        username.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                login();
            }
        });
        password.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                login();
            }
        });
        loginBtn.setOnAction(actionEvent -> {
            login();
        });
    }

    public void login() {
        if ((getCurrentForm() == FormType.ADMIN)) adminLogin();
        else if (getCurrentForm() == FormType.EMPLOYEE) employeeLogin();
    }

    public void adminLogin() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            AlertBox alertBox = new AlertBox(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
            alertBox.showAlert();
        } else if (!verifyAdminCredentialSQL(username.getText(), password.getText())) {
            AlertBox alertBox = new AlertBox(Alert.AlertType.ERROR, "Error Message", null, "Wrong username/password");
            alertBox.showAlert();
        } else {
            AlertBox alertBox = new AlertBox(Alert.AlertType.INFORMATION, "Information message", null, "Successfully login!");
            alertBox.showAlert();

            Stage stage = (Stage) window.getScene().getWindow();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.setScene(new Scene(root));
            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);
            });
            stage.show();
        }
    }

    public void employeeLogin() {
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            AlertBox alertBox = new AlertBox(Alert.AlertType.ERROR, "Error Message", null, "Please fill all blank fields");
            alertBox.showAlert();
        } else if (!verifyEmployeeCredentialSQL(username.getText(), password.getText())) {
            AlertBox alertBox = new AlertBox(Alert.AlertType.ERROR, "Error Message", null, "Wrong username/password");
            alertBox.showAlert();
        } else {
            AlertBox alertBox = new AlertBox(Alert.AlertType.INFORMATION, "Information message", null, "Successfully login!");
            alertBox.showAlert();
            try {
                Stage stage = (Stage) window.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeDashboard.fxml"));
                Parent root = loader.load();
                EmployeeDashboardController controller = loader.getController();
                controller.setEmployeeId(getEmployeeNameSQL(username.getText()));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void switchForm() {
        if (getCurrentForm() == FormType.ADMIN) {
            //admin -> employee
            label.setText("EMPLOYEE SIGN IN");
            label.setLayoutX(115);
            colorPane.setStyle("-fx-background-color:linear-gradient(to top right, #64522c, #a8962b)");
            hyperlink.setText("Login Admin Account");
        } else if (getCurrentForm() == FormType.EMPLOYEE) {
            //employee -> admin
            label.setText("ADMIN SIGN IN");
            label.setLayoutX(134);
            colorPane.setStyle("-fx-background-color: #5078f2;");
            hyperlink.setText("Login Employee Account");
        }
    }

    //0: admin, 1: employee
    public FormType getCurrentForm() {
        return (label.getText().equals("ADMIN SIGN IN")) ? FormType.ADMIN : FormType.EMPLOYEE;
    }

    public void close() {
        System.exit(0);
    }

    public enum FormType {
        ADMIN, EMPLOYEE
    }


}