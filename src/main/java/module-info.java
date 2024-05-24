module com.example.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    requires org.controlsfx.controls;

    opens com.example.supermarket to javafx.fxml;
    exports com.example.supermarket;
}