module com.example.postgresbackupdemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.postgresbackupdemo to javafx.fxml;
    exports com.example.postgresbackupdemo;
}