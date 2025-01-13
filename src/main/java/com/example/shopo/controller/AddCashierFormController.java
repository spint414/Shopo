package com.example.shopo.controller;

import com.example.shopo.bo.BOFactory;
import com.example.shopo.bo.custom.CashierBO;
import com.example.shopo.bo.custom.Impl.CashierIBOmpl;
import com.example.shopo.dto.CashierDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCashierFormController implements Initializable {
    @FXML
    public TextField txtPassword;
    @FXML
    public TextField txtUser;
    @FXML
    public Button setOnAction;
    @FXML
    public TextField txtRole;
    @FXML
    public TableView<CashierDTO> tblCashier;
    @FXML
    public TableColumn<CashierDTO, String> colCashUser;
    @FXML
    public TableColumn<CashierDTO, String> colCashPassword;
    @FXML
    public TableColumn<CashierDTO, String> colCashRole;

    CashierBO cashierBO = new CashierIBOmpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cashierBO = (CashierBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CASHIER);
        colCashUser.setCellValueFactory(new PropertyValueFactory<>("castUser"));
        colCashPassword.setCellValueFactory(new PropertyValueFactory<>("castPassword"));
        colCashRole.setCellValueFactory(new PropertyValueFactory<>("castRole"));
        loadAllCashier();
    }

    private void loadAllCashier() {
        try {
            ObservableList<CashierDTO> allCashier = cashierBO.getAllCashier();
            tblCashier.setItems(allCashier);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOnAction() {
        // Image selection logic removed, as it is no longer necessary
    }

    public void addOnAction(ActionEvent actionEvent) {
        try {
            boolean isAdded = cashierBO.addCashier(new CashierDTO(
                    txtUser.getText(),
                    txtPassword.getText(),
                    txtRole.getText()
            ));
            if (isAdded) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Cashier Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                loadAllCashier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Cashier Not Added", new ButtonType[]{ButtonType.OK})).show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        // Removed unnecessary search logic for fields no longer used (like txtCashierID, txtCashierName, etc.)
    }

    public void cashierUpdateOnAction(ActionEvent actionEvent) {
        try {
            // Only the user, password, and role are relevant
            CashierDTO cashierDTO = new CashierDTO(txtUser.getText(), txtPassword.getText(), txtRole.getText());
            boolean updateCashier = cashierBO.updateCashier(cashierDTO);
            if (updateCashier) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Cashier Updated Successfully", new ButtonType[]{ButtonType.OK})).show();
                loadAllCashier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Cashier Not Updated", new ButtonType[]{ButtonType.OK})).show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void cashierDeleteOnAction(ActionEvent actionEvent) {
        String user = txtUser.getText();
        try {
            boolean isDelete = cashierBO.deleteCashier(user);
            if (isDelete) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Cashier Deleted Successfully", new ButtonType[]{ButtonType.OK})).show();
                loadAllCashier();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Cashier Not Deleted", new ButtonType[]{ButtonType.OK})).show();
            }
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void tblCashierClick() {
        CashierDTO c = (CashierDTO) tblCashier.getSelectionModel().getSelectedItem();
        txtUser.setText(c.getCastUser());
        txtPassword.setText(c.getCastPassword());
        txtRole.setText(c.getCastRole());
    }
}
