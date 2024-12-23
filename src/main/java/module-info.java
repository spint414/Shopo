module com.example.shopo.shopo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.shopo to javafx.fxml;
    exports com.example.shopo;
    exports com.example.shopo.controller;
    opens com.example.shopo.controller to javafx.fxml;
}