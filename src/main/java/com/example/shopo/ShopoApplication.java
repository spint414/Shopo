package com.example.shopo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class ShopoApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Parent root;
    double xOffset, yOffset;

    @Override
    public void start(Stage primaryStage) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(ShopoApplication.class.getResource("view/DashboardForm.fxml")));
            Scene scene = new Scene(root);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            root.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}