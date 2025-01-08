package com.example.shopo.controller;

import com.example.shopo.bo.BOFactory;
import com.example.shopo.bo.custom.SuplayBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.example.shopo.dto.SuplayDTO;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddSuplyController implements Initializable {
    public JFXTextField txtSupId;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtCompanyName;
    public JFXTextField txtPhoneNo;
    public JFXButton btnSave;
    public TableView<SuplayDTO> tblSup;
    public TableColumn<Object, Object> colSupId;
    public TableColumn<Object, Object> colComName;
    public TableColumn<Object, Object> colAddress;
    public TableColumn<Object, Object> colPhoneNo;
    public TableColumn<Object, Object> colEmail;
    SuplayBO suplayBO;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        suplayBO = (SuplayBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SUPLAY);
        colSupId.setCellValueFactory(new PropertyValueFactory<>("suplayerID"));
        colComName.setCellValueFactory(new PropertyValueFactory<>("suplayerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("suplayerAddress"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("suplayerPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("suplayerEmail"));
        setTxtcustID();
        loadAllSuplay();
    }

    public void setTxtcustID() {
        try {
            int id = this.suplayBO.getRowCount();
            if (id == 0) {
                this.txtSupId.setText("S001");
            }

            if (id > 0 && id <= 8) {
                this.txtSupId.setText("S00" + (id + 1));
            }

            if (id >= 9 && id <= 98) {
                this.txtSupId.setText("S0" + (id + 1));
            }

            if (id >= 99) {
                this.txtSupId.setText("S" + (id + 1));
            }
        } catch (ClassNotFoundException | SQLException var2) {
            var2.printStackTrace();
        }
        //customer Count Code
    }

    public void addOnAction() {
        try {
            boolean isAdded = suplayBO.addSuplay(new SuplayDTO(
                    txtSupId.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    txtCompanyName.getText(),
                    txtPhoneNo.getText()));
            String tilte;
            String message;
            if (isAdded) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Suplayer Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Suplayer Is Added";
                loadAllSuplay();

            } else {
                (new Alert(Alert.AlertType.ERROR, "Suplayer Not Added", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Suplayer Is Not Added";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Suplayer Is Already On The Sever";
            String message = "Suplayer Is Not Added";
        }
    }


    public void searchOnAction() {
        try {
            String suplayID = txtSupId.getText();
            SuplayDTO searchSaplay = suplayBO.searchSuplay(suplayID);
            if (searchSaplay != null) {
                txtSupId.setText(searchSaplay.getSuplayerID());
                txtCompanyName.setText(searchSaplay.getSuplayerName());
                txtAddress.setText(searchSaplay.getSuplayerAddress());
                txtPhoneNo.setText(searchSaplay.getSuplayerPhone());
                txtEmail.setText(searchSaplay.getSuplayerEmail());

                String tilte = "Suplayer Searched ";
                String message = "Suplayer Is " + "" + txtCompanyName.getText() + "";

            } else {
                String tilte = "Searched Customer Not Found";
                String message = "Try Again";
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        //Customer Search Is Over(With Notification)
    }

    public void updateOnAction() {
        try {
            String SuplayerID = txtSupId.getText();
            String SuplayerName = txtCompanyName.getText();
            String SuplayerAddress = txtAddress.getText();
            String SuplayerPhone = txtPhoneNo.getText();
            String SuplayerEmail = txtEmail.getText();
            SuplayDTO suplayDTO = new SuplayDTO(SuplayerID, SuplayerName, SuplayerAddress, SuplayerPhone, SuplayerEmail);
            boolean updateCustomer = suplayBO.updateSuplay(suplayDTO);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            if (updateCustomer) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Suplayer Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Suplayer Is Updated";
                loadAllSuplay();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Supalyer Not Update", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Un Successful";
                message = "Customer Is Not Updated";
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //Customer Update Is Over(With Notification)
    }


    public void deleteOnAction() {
        String ID = txtSupId.getText();
        try {
            boolean isDelete = suplayBO.deleteSuplay(ID);
            String tilte;
            String message;
            TrayNotification tray = new TrayNotification();
            if (isDelete) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Suplayer Delete Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Delete Success";
                message = "Suplayer Is Deleted";
                loadAllSuplay();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Suplayer Not Delete", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Suplayer Not Found";
                message = "Sorry";
            }
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        //Customer Delete Is Over(With Notification)
    }

    private void loadAllSuplay() {
        try {
            ObservableList<SuplayDTO> allSuplay = suplayBO.getAllSuplay();
            tblSup.setItems(allSuplay);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void tblSuplayClick(MouseEvent mouseEvent) {
        SuplayDTO c = tblSup.getSelectionModel().getSelectedItem();
        txtSupId.setText(c.getSuplayerID());
        txtAddress.setText(c.getSuplayerAddress());
        txtEmail.setText(c.getSuplayerEmail());
        txtCompanyName.setText(c.getSuplayerName());
        txtPhoneNo.setText(c.getSuplayerPhone());

    }
}

