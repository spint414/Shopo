package com.example.shopo.dao.custom.impl;

import com.example.shopo.dao.custom.QueryDAO;
import com.example.shopo.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public ArrayList<CustomEntity> getOrderDetailsFromOID(String oid) throws ClassNotFoundException, SQLException {
        return null;
    }
}
