package com.example.shopo.dao.custom;

import com.example.shopo.dao.CrudDAO;
import com.example.shopo.entity.Orders;

import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<Orders,String> {
    String getOrderLastId()throws Exception;
    int getRowCount()throws ClassNotFoundException, SQLException;
}
