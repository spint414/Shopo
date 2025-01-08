package com.example.shopo.dao.custom;

import com.example.shopo.dao.CrudDAO;
import com.example.shopo.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    String getCustomerLastId()throws Exception;
    int getRowCount()throws ClassNotFoundException, SQLException;
}
