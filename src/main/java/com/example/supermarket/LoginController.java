package com.example.supermarket;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

public class LoginController {

    @FXML
    private Hyperlink employee_hyperlink;

    @FXML
    private TextField admin_username;

    @FXML
    private Button employee_loginBtn;

    @FXML
    private PasswordField employee_password;

    @FXML
    private TextField employee_id;

    @FXML
    private AnchorPane main_form;

    @FXML
    private AnchorPane admin_form;

    @FXML
    private AnchorPane employee_form;

    @FXML
    private Hyperlink admin_hyperlink;

    @FXML
    private PasswordField admin_password;

    @FXML
    private Button admin_loginBtn;

    //Databases tool
    private Connection connection;
    private ResultSet resultSet;

    private PreparedStatement preparedStatement;

    private double x = 0;
    private double y = 0;
    public void adminLogin() {

        String adminData = "SELECT * FROM admin WHERE username = ? and password = ?";

        try {
            connection = SQLconnect.connectTodb();

            Alert alert;

            if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {
                preparedStatement = connection.prepareStatement(adminData);
                preparedStatement.setString(1, admin_username.getText());
                preparedStatement.setString(2, admin_password.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {

                    // HIDE LOG IN FORM

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully login!");
                    alert.showAndWait();

                    admin_loginBtn.getScene().getWindow().hide();


                    Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    root.setOnMousePressed((MouseEvent event) -> {
                        x =event.getSceneX();
                        y = event.getSceneY();
                    });


                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });

                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username/password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchForm(ActionEvent event) {
        if(event.getSource() == admin_hyperlink) {
            admin_form.setVisible(false);
            employee_form.setVisible(true);
        }else if(event.getSource() == employee_hyperlink)
        {
            admin_form.setVisible(true);
            employee_form.setVisible(false);
        }
    }
    public void close() {
        System.exit(0);
    }






}