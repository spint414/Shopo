module Shopo {
    requires com.jfoenix;
    requires java.sql;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.base;
    requires java.desktop;
    requires jasperreports;
    exports com.example.shopo;
    exports com.example.shopo.controller to javafx.fxml;
}