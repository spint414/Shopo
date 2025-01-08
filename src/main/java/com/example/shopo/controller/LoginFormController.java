package com.example.shopo.controller;


import com.example.shopo.bo.custom.CashierBO;
import com.example.shopo.bo.custom.Impl.LoginBOImpl;
import com.example.shopo.bo.custom.LoginBO;
import com.example.shopo.dto.CashierDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {
    @FXML
    public TextField txtUserName;
    @FXML
    private PasswordField txtPassword;  // Add @FXML here
    @FXML
    private AnchorPane root;
    CashierBO cashierBO;


    public void LoginOnAction() throws IOException {

        String userName = txtUserName.getText().trim();
        String password = txtPassword.getText().trim();
        if (Pattern.compile("^(thilina)$").matcher(userName).matches()) {


        } else {
//            txtUserName.setFocusColor(Paint.valueOf("red"));
            txtUserName.requestFocus();


        }
        if (Pattern.compile("^(2259)$").matcher(userName).matches()) {
        } else {
//            txtPassword.setFocusColor(Paint.valueOf("red"));
            txtPassword.requestFocus();

        }
        if (userName.length() > 0 && password.length() > 0) {
            if (userName.equalsIgnoreCase("thilina")
                    && password.equals("2259")) {

                Stage window = (Stage) this.root.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Dashboard.fxml"))));
                window.centerOnScreen();

            } else {
                LoginBO loginBO = new LoginBOImpl();
                try {
                    CashierDTO cashierDTO = loginBO.getValidated(txtUserName.getText());
                    System.out.println(cashierDTO.getCastID()+" id from login form");
                    System.out.println(cashierDTO.getCastlogin() + " userName");
                    System.out.println(cashierDTO.getCastPassword() + " password");

                    if (cashierDTO.getCastlogin().equals(txtUserName.getText()) &&
                            cashierDTO.getCastPassword().equals(txtPassword.getText())) {

                        Stage window = (Stage) this.root.getScene().getWindow();
//                        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/CashierForm.fxml"))));
                        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/CashierForm.fxml"));
                        Parent parent =  fxmlLoader.load();
                        CashierFormController controller = fxmlLoader.getController();
                        System.out.println("sending data");
                        controller.setCashierID(cashierDTO.getCastID());
                        window.setScene(new Scene(parent));
                        window.centerOnScreen();
                        window.show();

                    } else {
                        System.out.println("waradi ukanno");
                    }
                } catch (NullPointerException e) {
                    System.out.println("user name waradi ballo");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
//                txtUserName.setFocusColor(Paint.valueOf("red"));
//                txtUserName.requestFocus();
//                String tilte = "Sign In";
//                String message = "Error Username " + "'" + txtUserName.getText() + "'" + ", and Password " + "'" + txtPassword.getText() + "'" + " Wrong";
//                tray.notification.TrayNotification tray = new TrayNotification();
//                AnimationType type = AnimationType.POPUP;
//
//                tray.setAnimationType(type);
//                tray.setTitle(tilte);
//                tray.setMessage(message);
//                tray.setNotificationType(NotificationType.ERROR);
//                tray.showAndDismiss(Duration.millis(3000));
            }
        } else {
            String tilte = "Sign In";
            String message = "Empty Username Or Password";
        }
    }


    public void btnCloaseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
