package com.example.shopo.bo.custom.Impl;

import com.example.shopo.bo.custom.LoginBO;
import com.example.shopo.dao.DAOFactory;
import com.example.shopo.dao.custom.CashierDAO;
import com.example.shopo.dto.CashierDTO;
import com.example.shopo.entity.Cashier;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {
    CashierDAO cashierDAO = (CashierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CASHIER);

    @Override
    public CashierDTO getValidated(String userName) throws SQLException, ClassNotFoundException {
//        Cashier validate = usersDAO.validate(userName);
//        return new UsersDTO(validate.getUser_name(),validate.getUser_password(),validate.getPosition());
        Cashier cus = cashierDAO.validate(userName);
        return new CashierDTO(cus.getCastUser(),cus.getCastRole(),cus.getCastPassword());
    }
}
