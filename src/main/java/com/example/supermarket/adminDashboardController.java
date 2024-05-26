package com.example.supermarket;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class adminDashboardController implements Initializable {


    @FXML
    private TableColumn<productData, String> addProducts_productID_col;

    @FXML
    private TableColumn<employeeData, String> employees_email_col;

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
    private TableColumn<productData, String> addProducts_brandName_col;

    @FXML
    private Button logout;

    @FXML
    private TableView<productData> addProducts_tableView;

    @FXML
    private TableColumn<productData, Integer> addProducts_quantity_col;

    @FXML
    private TextField addProducts_brandName;

    @FXML
    private Button addProducts_deleteBtn;

    @FXML
    private TableColumn<employeeData, String> employees_password_col;

    @FXML
    private FontAwesomeIcon close;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private FontAwesomeIcon minimize;

    @FXML
    private Button employees_clearBtn;

    @FXML
    private TableColumn<employeeData, String> employees_Employee_ID_col;

    @FXML
    private TextField addProducts_price;

    @FXML
    private TableColumn<productData, String> addProducts_productName_col;

    @FXML
    private TextField addProducts_search;

    @FXML
    private Button employees_updateBtn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private TableColumn<employeeData, String> employees_last_name_col;

    @FXML
    private TableColumn<employeeData, String> employees_first_name_col;

    @FXML
    private TableColumn<productData, Double> addProducts_price_col;

    @FXML
    private TableColumn<employeeData, String> employees_jobTitle_col;

    @FXML
    private TextField employees_jobTitle;

    @FXML
    private Label dashboard_activeEmployees;

    @FXML
    private Button addProducts_addBtn;

    @FXML
    private TextField addProducts_quantity;

    @FXML
    private TextField addProducts_productName;

    @FXML
    private TextField employees_EmployeesID;

    @FXML
    private Button addProducts_updateBtn;

    @FXML
    private TextField employees_Firstname;

    @FXML
    private TextField employees_Email;

    @FXML
    private AnchorPane employees_form;

    @FXML
    private Button employees_deleteBtn;

    @FXML
    private Button employees_saveBtn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private TableView<employeeData> employees_tableView;

    @FXML
    private AnchorPane main_form;

    private double x = 0;
    private double y = 0;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<productData> addProductsList;
    private ObservableList<employeeData> employeesList;

    public void dashboardDisplayActiveEmployees() {
        String sql = "SELECT COUNT(employeeNumber) from employees";

        int countE = 0;

        connect = SQLconnect.connectTodb();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                countE = result.getInt("COUNT(employeeNumber)");
                dashboard_activeEmployees.setText(String.valueOf(countE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsAdd() {
        String insertProduct = "INSERT INTO products " +
                "(productCode,productLine,productName,quantityInStock,buyPrice) " +
                "VALUES(?,?,?,?,?)";

        connect = SQLconnect.connectTodb();
        try {
            Alert alert;

            if (addProducts_productID.getText().isEmpty() ||
                    addProducts_brandName.getText().isEmpty() ||
                    addProducts_productName.getText().isEmpty() ||
                    addProducts_quantity.getText().isEmpty() ||
                    addProducts_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String check = "SELECT productCode FROM products" +
                        " WHERE productCode ='" + addProducts_productID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Product ID: " + addProducts_productID.getText() + " was already existed!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertProduct);
                    prepare.setString(1, addProducts_productID.getText());
                    prepare.setString(2, addProducts_brandName.getText());
                    prepare.setString(3, addProducts_productName.getText());
                    prepare.setString(4, addProducts_quantity.getText());
                    prepare.setString(5, addProducts_price.getText());
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Suceesfully added!");
                    alert.showAndWait();

                    // UPDATE THE ADDED ROW
                    addProductsShowData();

                    // Clear infor once added
                    addProductsClear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsUpdate() {
        String updateProduct = "UPDATE products SET productLine = '" + addProducts_brandName.getText() + "'" +
                ",productName = '" + addProducts_productName.getText() + "'" +
                ",quantityInStock = '" + addProducts_quantity.getText() + "' " +
                ",buyPrice = '" + addProducts_price.getText() + "' " +
                "WHERE productCode = '" + addProducts_productID.getText() + "'";

        connect = SQLconnect.connectTodb();

        try {
            Alert alert;

            if (addProducts_productID.getText().isEmpty() ||
                    addProducts_brandName.getText().isEmpty() ||
                    addProducts_productName.getText().isEmpty() ||
                    addProducts_quantity.getText().isEmpty() ||
                    addProducts_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you wan to update Product ID : " +
                        "'" + addProducts_productID.getText() + "'" + "?");

                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateProduct);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated!");
                    alert.showAndWait();

                    addProductsShowData();
                    addProductsClear();
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsClear() {
        addProducts_productID.setText("");
        addProducts_brandName.setText("");
        addProducts_productName.setText("");
        addProducts_quantity.setText("");
        addProducts_price.setText("");
    }

    public void addProductsDelete() {
        String deleteProduct = "DELETE FROM products WHERE productCode = '" + addProducts_productID.getText() + "'";

        connect = SQLconnect.connectTodb();

        try {
            Alert alert;
            if (addProducts_productID.getText().isEmpty() ||
                    addProducts_brandName.getText().isEmpty() ||
                    addProducts_productName.getText().isEmpty() ||
                    addProducts_quantity.getText().isEmpty() ||
                    addProducts_price.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to DELETE Product ID : '" + addProducts_productID.getText() + "'");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteProduct);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();

                    addProductsClear();
                    addProductsShowData();
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductsSearch() {


        FilteredList<productData> filter = new FilteredList<>(addProductsList, e -> true);

        addProducts_search.textProperty().addListener((Observable, oldValue, newValue) -> {
//            System.out.println("Search text changed to: " + newValue);

            filter.setPredicate(predicateProductData -> {
//                System.out.println("Evaluating product: " + predicateProductData.getProductID());

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateProductData.getProductID().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getBrand().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getQuantity().toString().contains(searchKey)) {
                    return true;
                } else return predicateProductData.getPrice().toString().contains(searchKey);
            });
        });


        SortedList<productData> sortedList = new SortedList<>(filter);

        sortedList.comparatorProperty().bind(addProducts_tableView.comparatorProperty());
        addProducts_tableView.setItems(sortedList);


    }

    public void addProductsSelect() {
        productData prod = addProducts_tableView.getSelectionModel().getSelectedItem();
        int num = addProducts_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        addProducts_productID.setText(prod.getProductID());
        addProducts_brandName.setText(prod.getBrand());
        addProducts_productName.setText(prod.getProductName());
        addProducts_quantity.setText(String.valueOf(prod.getQuantity()));
        addProducts_price.setText(String.valueOf(prod.getPrice()));


    }

    public ObservableList<productData> addProductsListData() {
        ObservableList<productData> prodList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM products";

        connect = SQLconnect.connectTodb();
        try {

            productData prod;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                prod = new productData(result.getString("productCode"),
                        result.getString("productLine"),
                        result.getString("productName"),
                        result.getInt("quantityInStock"),
                        result.getDouble("buyPrice"));
                prodList.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodList;
    }

    public void addProductsShowData() {
        addProductsList = addProductsListData();

        addProducts_productID_col.setCellValueFactory(new PropertyValueFactory<>("productID"));
        addProducts_brandName_col.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addProducts_productName_col.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addProducts_quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        addProducts_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProducts_tableView.setItems(addProductsList);
    }

    public ObservableList<employeeData> employeesListData() {

        ObservableList<employeeData> emData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM employees";
        connect = SQLconnect.connectTodb();

        try {

            employeeData employeeD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                employeeD = new employeeData(result.getString("employeeNumber"),
                        result.getString("password"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("email"),
                        result.getString("jobTitle"));

                emData.add(employeeD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emData;
    }

    public void employeesShowListData() {
        employeesList = employeesListData();

        employees_Employee_ID_col.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        employees_password_col.setCellValueFactory(new PropertyValueFactory<>("password"));
        employees_first_name_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employees_last_name_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employees_email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        employees_jobTitle_col.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));

        employees_tableView.setItems(employeesList);
    }

    public void employeesSelect() {
        employeeData employeeD = employees_tableView.getSelectionModel().getSelectedItem();
        int num = employees_tableView.getSelectionModel().getSelectedIndex();

        if (num - 1 < -1) {
            return;
        }

        employees_EmployeesID.setText(employeeD.getEmployeeID());
        employees_Password.setText(employeeD.getPassword());
        employees_Firstname.setText(employeeD.getFirstName());
        employees_Lastname.setText(employeeD.getLastName());
        employees_Email.setText(employeeD.getEmail());
        employees_jobTitle.setText(employeeD.getJobTitle());
    }

    public void employeesSave() {
        String insertEmployee = "INSERT INTO employees (employeeNumber,lastName,firstName,password,email,jobTitle) " +
                "VALUES(?,?,?,?,?,?)";

        connect = SQLconnect.connectTodb();
        try {

            Alert alert;
            if (employees_EmployeesID.getText().isEmpty() ||
                    employees_Lastname.getText().isEmpty() ||
                    employees_Firstname.getText().isEmpty() ||
                    employees_Password.getText().isEmpty() ||
                    employees_Email.getText().isEmpty() ||
                    employees_jobTitle.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank field!");
                alert.showAndWait();
            } else {

                String checkExist = "SElECT employeeNumber from employees" +
                        " WHERE employeeNumber = '" + employees_EmployeesID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkExist);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee with ID : '" + employees_EmployeesID.getText() + "' has already existed!");
                    alert.showAndWait();
                }
                prepare = connect.prepareStatement(insertEmployee);
                prepare.setString(1, employees_EmployeesID.getText());
                prepare.setString(2, employees_Lastname.getText());
                prepare.setString(3, employees_Firstname.getText());
                prepare.setString(4, employees_Password.getText());
                prepare.setString(5, employees_Email.getText());
                prepare.setString(6, employees_jobTitle.getText());

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("Sucessfully saved!");
                alert.showAndWait();

                employeesShowListData();
                employeeReset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void employeeUpdate() {
        String employeeUpdate = "Update employees SET lastName = '" + employees_Lastname.getText() + "'" +
                ",firstName = '" + employees_Firstname.getText() + "'" +
                ",password = '" + employees_Password.getText() + "'" +
                ",email = '" + employees_Email.getText() + "'" +
                ",jobTitle = '" + employees_jobTitle.getText() + "'" +
                "WHERE employeeNumber = '" + employees_EmployeesID.getText() + "'";

        connect = SQLconnect.connectTodb();
        try {
            Alert alert;
            if (employees_EmployeesID.getText().isEmpty() ||
                    employees_Lastname.getText().isEmpty() ||
                    employees_Firstname.getText().isEmpty() ||
                    employees_Password.getText().isEmpty() ||
                    employees_Email.getText().isEmpty() ||
                    employees_jobTitle.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank field!");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update employee ID : '" + employees_EmployeesID.getText() + "'");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(employeeUpdate);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Sucessfully updated!");
                    alert.showAndWait();

                    employeesShowListData();
                    employeeReset();
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void employeesDelete() {
        String deleteEmployee = "DELETE FROM employees" +
                " WHERE employeeNumber = '" + employees_EmployeesID.getText() + "'";

        connect = SQLconnect.connectTodb();

        try {
            Alert alert;
            if (employees_EmployeesID.getText().isEmpty() ||
                    employees_Lastname.getText().isEmpty() ||
                    employees_Firstname.getText().isEmpty() ||
                    employees_Password.getText().isEmpty() ||
                    employees_Email.getText().isEmpty() ||
                    employees_jobTitle.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all the blank field!");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete employee ID : '" + employees_EmployeesID.getText() + "'");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(deleteEmployee);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information message");
                    alert.setHeaderText(null);
                    alert.setContentText("Sucessfully deleted!");
                    alert.showAndWait();

                    employeesShowListData();
                    employeeReset();
                } else {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void employeeReset() {
        employees_EmployeesID.setText("");
        employees_Password.setText("");
        employees_Firstname.setText("");
        employees_Lastname.setText("");
        employees_Email.setText("");
        employees_jobTitle.setText("");
    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();

                Parent root = FXMLLoader.load(getClass().getResource("LoginDashboard.fxml"));
                Stage stage = new Stage();
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addProducts_form.setVisible(false);
            employees_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:linear-gradient(to right, #5c4dcc,#3521cc)");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            employees_btn.setStyle("-fx-background-color:transparent");
        } else if (event.getSource() == addProducts_btn) {
            addProducts_form.setVisible(true);
            dashboard_form.setVisible(false);
            employees_form.setVisible(false);

            addProducts_btn.setStyle("-fx-background-color:linear-gradient(to right, #5c4dcc,#3521cc)");
            dashboard_btn.setStyle("-fx-background-color:transparent");
            employees_btn.setStyle("-fx-background-color:transparent");

            addProductsShowData();


        } else if (event.getSource() == employees_btn) {
            employees_form.setVisible(true);
            dashboard_form.setVisible(false);
            addProducts_form.setVisible(false);

            employees_btn.setStyle("-fx-background-color:linear-gradient(to right, #5c4dcc,#3521cc)");
            addProducts_btn.setStyle("-fx-background-color:transparent");
            dashboard_btn.setStyle("-fx-background-color:transparent");

            employeesShowListData();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductsShowData();
        employeesShowListData();

        dashboardDisplayActiveEmployees();
    }

}

