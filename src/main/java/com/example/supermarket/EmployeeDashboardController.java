package com.example.supermarket;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

import static com.example.supermarket.EmployeeDashboardDataModel.*;

public class EmployeeDashboardController {

    @FXML
    private TableColumn<PurchaseData, Integer> col_customerId;

    @FXML
    private TableColumn<PurchaseData, String> col_brand;

    @FXML
    private TableColumn<PurchaseData, Double> col_price;

    @FXML
    private TableColumn<PurchaseData, String> col_productName;

    @FXML
    private TableColumn<PurchaseData, Integer> col_quantity;

    @FXML
    private Label employee_id;

    @FXML
    private Button minimizeButton;

    @FXML Button logout;

    @FXML
    private Button purchase_addBtn;

    @FXML
    private Button purchase_clearBtn;

    @FXML
    private TextField customerId;

    @FXML
    private ComboBox<String> purchase_brand;

    @FXML
    private Button purchase_payBtn;

    @FXML
    private ComboBox<String> purchase_productName;

    @FXML
    private Spinner<Integer> purchase_quantity;

    @FXML
    private Button purchase_receiptBtn;

    @FXML
    private TableView<PurchaseData> purchase_tableView;

    @FXML
    private Label purchase_total;

    @FXML
    private AnchorPane titlePane;

    @FXML
    public void initialize() {
        setEmployeeId();
        setDragAndDrop();
        setMinimize();
        setPurchaseListData();
        initPurchaseBrand();
        initPurchaseQuantitySpinner();
        setPurchase_addBtn();
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

    public void setPurchaseListData() {
        ObservableList<PurchaseData> purchaseList = loadPurchaseListDataSQL();
        col_customerId.setCellValueFactory(item -> item.getValue().customerIdProperty());
        col_brand.setCellValueFactory(item -> (item.getValue().brandProperty()));
        col_productName.setCellValueFactory(item -> (item.getValue().productNameProperty()));
        col_quantity.setCellValueFactory(item -> (item.getValue().quantityProperty()));
        col_price.setCellValueFactory(item -> (item.getValue().priceProperty()));

        purchase_tableView.setItems(purchaseList);
    }

    public void initPurchaseQuantitySpinner() {
        purchase_quantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,0));
        purchase_quantity.setEditable(true);
        purchase_quantity.getEditor().textProperty().addListener((Observable, oldValue, newValue) -> {
            if (oldValue.matches("0")) {
                purchase_quantity.getEditor().setText(newValue.replaceAll("0", ""));
            }
            if (!newValue.matches("\\d*")) {
                purchase_quantity.getEditor().setText(newValue.replaceAll("\\D", ""));
            }
        });
        purchase_quantity.valueProperty().addListener((Observable, oldValue, newValue) -> {
            if (newValue == null) {
                purchase_quantity.getValueFactory().setValue(oldValue);
            }
        });
    }

    public void initPurchaseBrand() {
        ObservableList<String> brandList = getBrandListSQL();
        purchase_brand.setItems(brandList);
        purchase_brand.valueProperty().addListener((Observable, oldValue, newValue) -> {
            ObservableList<String> productList = searchBrandSQL(newValue);
            purchase_productName.setItems(null);
            purchase_productName.setItems(productList);
        });
    }

    public void setPurchase_addBtn() {
        purchase_addBtn.setOnAction(actionEvent -> {
            //update purchase list and table
            if (!verifyCustomerId()) {
                alert(Alert.AlertType.ERROR, "Error message", null, "Customer ID must be integer");
                return;
            }

            ObservableList<PurchaseData> purchaseList = FXCollections.observableArrayList();
            PurchaseData purchaseData = new PurchaseData();
            purchaseData.setCustomerId(Integer.parseInt(customerId.getText()));
            purchaseData.setBrand(purchase_brand.getValue());
            purchaseData.setProductName(purchase_productName.getValue());
            purchaseData.setQuantity(purchase_quantity.getValue());
            purchaseData.setPrice(getProductPriceSQL(purchase_productName.getValue()));

            purchaseList.add(purchaseData);
            purchase_tableView.setItems(purchaseList);
            //update sql table
            //update total
        });
    }

    private void alert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private boolean verifyCustomerId() {
        return customerId.getText().matches("\\d*") && !customerId.getText().isEmpty();
    }
}
