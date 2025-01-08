package com.example.shopo.dao.custom;

import com.example.shopo.dao.SuperDAO;
import com.example.shopo.entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> getOrderDetailsFromOID(String oid) throws ClassNotFoundException, SQLException;
}
