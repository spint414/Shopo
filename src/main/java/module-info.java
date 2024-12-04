module com.example.shopo.shopo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.shopo.shopo to javafx.fxml;
    exports com.example.shopo.shopo;
}