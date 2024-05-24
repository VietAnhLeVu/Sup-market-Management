package com.example.supermarket;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {


    @FXML
    private TableColumn<?, ?> addProducts_productID_col;

    @FXML
    private TableColumn<?, ?> employees_gender_col;

    @FXML
    private TextField employees_Password;

    @FXML
    private TextField employees_Lastname;

    @FXML
    private AnchorPane addProducts_form;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button addProducts_clearBtn;

    @FXML
    private Button addProducts_btn;

    @FXML
    private Button employees_btn;

    @FXML
    private TextField addProducts_productID;

    @FXML
    private Label dashboard_todayIncome;

    @FXML
    private TableColumn<?, ?> addProducts_brandName_col;

    @FXML
    private Button logout;

    @FXML
    private TableView<?> addProducts_tableView;

    @FXML
    private TableColumn<?, ?> addProducts_status_col;

    @FXML
    private TextField addProducts_brandName;

    @FXML
    private Button addProducts_deleteBtn;

    @FXML
    private TableColumn<?, ?> employees_password_col;

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private Button employees_clearBtn;

    @FXML
    private TableColumn<?, ?> employees_Employee_ID_col;

    @FXML
    private TextField addProducts_price;

    @FXML
    private TableColumn<?, ?> addProducts_productName_col;

    @FXML
    private TextField addProducts_search;

    @FXML
    private Button employees_updateBtn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private TableColumn<?, ?> employees_last_name_col;

    @FXML
    private TableColumn<?, ?> employees_first_name_col;

    @FXML
    private TableColumn<?, ?> addProducts_price_col;

    @FXML
    private TableColumn<?, ?> employees_DOM_col;

    @FXML
    private Label dashboard_activeEmployees;

    @FXML
    private Button addProducts_addBtn;

    @FXML
    private ComboBox<?> addProducts_status;

    @FXML
    private TextField addProducts_productName;

    @FXML
    private TextField employees_EmployeesID;

    @FXML
    private Button addProducts_updateBtn;

    @FXML
    private TextField employees_Firstname;

    @FXML
    private ComboBox<?> employees_Gender;

    @FXML
    private AnchorPane employees_form;

    @FXML
    private Button employees_deleteBtn;

    @FXML
    private Button employees_saveBtn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private TableView<?> employees_tableView;

    @FXML
    private AnchorPane main_form;

    private double x = 0;
    private double y = 0;
    public void logout() {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("LoginDashboard.fxml"));
                Stage stage= new Stage();
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX()-x);
                    stage.setY(event.getScreenY()-y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchForm(ActionEvent event) {
        if(event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addProducts_form.setVisible(false);
            employees_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to right, #5c4dcc,#3521cc)");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            employees_btn.setStyle("-fx-background-color:transparent");
        }else if(event.getSource() == addProducts_btn) {
            addProducts_form.setVisible(true);
            dashboard_form.setVisible(false);
            employees_form.setVisible(false);

            addProducts_btn.setStyle("-fx-background-color:linear-gradient(to right, #5c4dcc,#3521cc)");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            employees_btn.setStyle("-fx-background-color:transparent");
        }else if(event.getSource() == employees_btn){
            employees_form.setVisible(true);
            dashboard_form.setVisible(false);
            addProducts_form.setVisible(false);

            employees_btn.setStyle("-fx-background-color:linear-gradient(to right, #5c4dcc,#3521cc)");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            dashboard_btn.setStyle("-fx-background-color:transparent");
        }
    }
    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}

