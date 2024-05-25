package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class EmployeeDashboardController {

    @FXML
    private TableColumn<?, ?> col_brand;

    @FXML
    private TableColumn<?, ?> col_price;

    @FXML
    private TableColumn<?, ?> col_productName;

    @FXML
    private TableColumn<?, ?> col_quantity;

    @FXML
    private Label employee_id;

    @FXML
    private Button minimizeButton;

    @FXML Button logout;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private Button purchase_addBtn1;

    @FXML
    private TextField purchase_brand;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private ComboBox<?> purchase_productName;

    @FXML
    private Spinner<?> purchase_quantity;

    @FXML
    private Button purchase_receiptBtn;

    @FXML
    private TableView<?> purchase_tableView;

    @FXML
    private Label purchase_total;

    @FXML
    private AnchorPane titlePane;

    public EmployeeDashboardController() {
    }

    @FXML
    public void initialize() {
        setEmployeeId();
        setDragAndDrop();
        setMinimize();
    }

    public void setEmployeeId() {
        //TODO: set employee id text.
         employee_id.setText("USERNAME");
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

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {
                ((Stage) logout.getScene().getWindow()).setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("LoginDashboard.fxml")
                )));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Data part
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
}
