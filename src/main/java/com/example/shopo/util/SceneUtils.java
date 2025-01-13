package com.example.shopo.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneUtils {
    public static void loadScene(Stage window, String fxmlPath) throws IOException {
        // Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(SceneUtils.class.getResource(fxmlPath));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        window.setScene(scene);
        window.centerOnScreen();

        // Apply the mouse drag functionality to the new root of the scene
        AnchorPane root = (AnchorPane) scene.getRoot();
        addMouseDragEvent(window, root);

        // Show the new scene
        window.show();
    }

    private static void addMouseDragEvent(Stage window, AnchorPane root) {
        // Add mouse drag event handlers to the root (AnchorPane)
        final double[] xOffset = {0};
        final double[] yOffset = {0};

        root.setOnMousePressed(event -> {
            xOffset[0] = event.getSceneX();
            yOffset[0] = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            window.setX(event.getScreenX() - xOffset[0]);
            window.setY(event.getScreenY() - yOffset[0]);
        });
    }
}
