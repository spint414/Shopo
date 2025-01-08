package com.example.shopo.bo.custom;

import com.example.shopo.bo.SuperBO;
import com.example.shopo.dto.CashierDTO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    CashierDTO getValidated(String userName) throws SQLException, ClassNotFoundException;
}
