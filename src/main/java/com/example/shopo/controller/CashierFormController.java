package com.example.shopo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierFormController implements Initializable {
    public Pane context;
    private String cashierID;

    public void setCashierID(String cashierID) {
        System.out.println(cashierID + " setter methood");
        this.cashierID = cashierID;

    }

    private void setUi(String location) throws IOException {
        context.getChildren().clear();
        context.getChildren().add(FXMLLoader.load(this.getClass().
                getResource("/view/" + location + ".fxml")));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            btnPlaceOrderOnAction();
//            new FadeIn(context).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnPlaceOrderOnAction() throws IOException {
//        setUi("PlaceOrderForm");
        context.getChildren().clear();
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("../view/PlaceOrderForm.fxml"));
        Parent parent = fxmlLoader.load();
        PlaceOrderController controller = fxmlLoader.getController();
        System.out.println(cashierID + " cashierForm cashier id");
        controller.setCashierID(cashierID);
        context.getChildren().add(parent);
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AddCustomerForm");
    }

    public void btnAboutOnAction(ActionEvent actionEvent) throws IOException {
        setUi("AboutForm");
    }


    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
//        Stage stage = (Stage) context.getScene().getWindow();
//        stage.close();
//
//        Stage dashBoardScene = new Stage();
//        dashBoardScene.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"))));
//
//        dashBoardScene.show();
//    }
        Stage stage = (Stage) context.getScene().getWindow();
        stage.close();
    }
}
