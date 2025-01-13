package com.example.shopo.dao.custom.impl;

import com.example.shopo.dao.CrudUtil;
import com.example.shopo.dao.custom.CashierDAO;
import com.example.shopo.entity.Cashier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierDAOImpl implements CashierDAO {
    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        // Assuming you don't need to count rows for just the user/password/role, we can keep this as is or modify accordingly.
        return 0;
    }

    @Override
    public Cashier validate(String userName) throws SQLException, ClassNotFoundException {
        String sql = "SELECT username, password, role FROM users WHERE username=?";
        ResultSet resultSet = CrudUtil.executeQuery(sql, userName);
        if (resultSet.next()) {
            return new Cashier(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }

    @Override
    public boolean add(Cashier cashier) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        return CrudUtil.executeUpdate(sql, cashier.getCastUser(), cashier.getCastPassword(), cashier.getCastRole());
    }

    @Override
    public boolean delete(String userName) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM users WHERE username=?";
        return CrudUtil.executeUpdate(sql, userName);
    }

    @Override
    public boolean update(Cashier cashier) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE users SET username=?, password=?, role=? WHERE username=?";
        return CrudUtil.executeUpdate(sql, cashier.getCastUser(), cashier.getCastPassword(), cashier.getCastRole(), cashier.getCastUser());
    }

    @Override
    public Cashier search(String userName) throws ClassNotFoundException, SQLException {
        String sql = "SELECT username, password, role FROM users WHERE username=?";
        ResultSet rst = CrudUtil.executeQuery(sql, userName);
        if (rst.next()) {
            return new Cashier(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return null;
    }

    @Override
    public ObservableList<Cashier> getAll() throws ClassNotFoundException, SQLException {
        String sql = "SELECT username, password, role FROM users";
        ResultSet rst = CrudUtil.executeQuery(sql);
        ObservableList<Cashier> allCashier = FXCollections.observableArrayList();
        while (rst.next()) {
            allCashier.add(new Cashier(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return allCashier;
    }
}
