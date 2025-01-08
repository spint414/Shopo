package com.example.shopo.dao.custom;

import com.example.shopo.TM.dtmTM;
import com.example.shopo.dao.CrudDAO;
import com.example.shopo.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item,String> {
    String getItemLastId() throws Exception;

    int getRowCount() throws ClassNotFoundException, SQLException;

    boolean updateStock(dtmTM orderDetail) throws ClassNotFoundException, SQLException;
}
