package com.example.shopo.controller;


import com.example.shopo.db.DBConnection;
import com.example.shopo.dto.PaymentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class DashboardFormController implements Initializable {
    public Label txtTotalCustomers;
    public Label TotalOrders;
    public Label TotalSales;
    public Label SoldItem;
    public PaymentDTO paymentDTOS;
    public AreaChart areaChart;

    @FXML
    private javafx.scene.chart.PieChart PieChart;
    private void initInfo() throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT COUNT(customer_id) FROM customers")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            txtTotalCustomers.setText(String.valueOf(customerCount));
        }

    }
    private void orders () throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT COUNT(order_id) FROM orders")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            TotalOrders.setText(String.valueOf(customerCount));
        }
    }
    private void sales () throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT \n" +
                                "    SUM(total_amount)\n" +
                                "FROM\n" +
                                "    orders")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            TotalSales.setText(String.valueOf(customerCount));
        }
    }
    private void soldItem () throws SQLException, ClassNotFoundException {
        ResultSet set = DBConnection.getInstance().
                getConnection().
                prepareStatement
                        ("SELECT \n" +
                                "    SUM(quantity)\n" +
                                "FROM\n" +
                                "    order_details")
                .executeQuery();
        if (set.next()) {
            int customerCount = set.getInt(1);
            SoldItem.setText(String.valueOf(customerCount));
        }
    }

    public void loadPieChart() throws SQLException, ClassNotFoundException {
        String query = "SELECT EXTRACT(MONTH FROM order_date) AS month, COUNT(order_id) AS order_count " +
                "FROM orders " +
                "GROUP BY EXTRACT(MONTH FROM order_date) " +
                "ORDER BY EXTRACT(MONTH FROM order_date)";

        ResultSet resultSet = DBConnection.getInstance().getConnection().prepareStatement(query).executeQuery();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        while (resultSet.next()) {
            int month = resultSet.getInt("month");
            int orderCount = resultSet.getInt("order_count");
            String monthName = getMonthName(month); // Helper method to get the month name
            pieChartData.add(new PieChart.Data(monthName, orderCount));
        }

        PieChart.setData(pieChartData);
    }

    private String getMonthName(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "Unknown";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            orders ();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            sales ();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            soldItem ();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            loadPieChart();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    }

