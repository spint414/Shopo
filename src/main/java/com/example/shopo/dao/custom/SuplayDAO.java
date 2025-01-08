package com.example.shopo.dao.custom;

import com.example.shopo.dao.CrudDAO;
import com.example.shopo.entity.Suplay;

import java.sql.SQLException;

public interface SuplayDAO extends CrudDAO<Suplay,String> {
    String getSuplayLastId()throws Exception;
    int getRowCount()throws ClassNotFoundException, SQLException;

}
