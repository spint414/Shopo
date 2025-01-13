package com.example.shopo.controller;


import com.example.shopo.bo.custom.CashierBO;
import com.example.shopo.bo.custom.Impl.LoginBOImpl;
import com.example.shopo.bo.custom.LoginBO;
import com.example.shopo.db.DBConnection;
import com.example.shopo.dto.CashierDTO;
import com.example.shopo.util.SceneUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {
    @FXML
    public TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane root;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnClose;
    @FXML
    private Label lblErrorMessage;

    public void loginOnAction(ActionEvent actionEvent) {

        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();

        if (!userName.isEmpty() && !password.isEmpty()) {
            try {
                // Connect to the database
                Connection connection = DBConnection.getInstance().getConnection();

                // SQL query to validate username, password, and role
                String query = "SELECT role FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    // If a match is found, check the role
                    String role = resultSet.getString("role");
                    Stage window = (Stage) this.root.getScene().getWindow();

                    if ("admin".equalsIgnoreCase(role)) {
                        // Navigate to Dashboard for admin
                        SceneUtils.loadScene(window, "/view/Dashboard.fxml");
                    } else {
                        // Navigate to CashierForm for non-admin
                        SceneUtils.loadScene(window, "/view/CashierForm.fxml");
                    }
                } else {
                    // Username or password is incorrect
                    lblErrorMessage.setText("Invalid username or password");
                    lblErrorMessage.setVisible(true);
                    txtUserName.requestFocus();
                }

                // Close the ResultSet and PreparedStatement
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Database connection error: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Handle empty username or password, show the error message
            lblErrorMessage.setText("Username or Password cannot be empty");
            lblErrorMessage.setVisible(true);
        }
    }

    public void closeOnAction(ActionEvent actionEvent) {
        // Show a confirmation dialog before closing
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Click OK to exit or Cancel to stay.");

        // Wait for user response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            }
        });
    }
}
