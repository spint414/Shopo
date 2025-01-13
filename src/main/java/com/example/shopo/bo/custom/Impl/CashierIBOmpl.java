package com.example.shopo.bo.custom.Impl;

import com.example.shopo.bo.custom.CashierBO;
import com.example.shopo.dao.DAOFactory;
import com.example.shopo.dao.custom.CashierDAO;
import com.example.shopo.dto.CashierDTO;
import com.example.shopo.entity.Cashier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CashierIBOmpl implements CashierBO {
    CashierDAO cashierDAO = (CashierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CASHIER);

    @Override
    public boolean addCashier(CashierDTO cashier) throws ClassNotFoundException, SQLException {
        return cashierDAO.add(new Cashier(cashier.getCastUser(), cashier.getCastPassword(), cashier.getCastRole()));
    }

    @Override
    public boolean deleteCashier(String user) throws ClassNotFoundException, SQLException {
        return cashierDAO.delete(user);
    }

    @Override
    public boolean updateCashier(CashierDTO cashier) throws ClassNotFoundException, SQLException {
        return cashierDAO.update(new Cashier(cashier.getCastUser(), cashier.getCastPassword(), cashier.getCastRole()));
    }

    @Override
    public CashierDTO searchCashier(String user) throws ClassNotFoundException, SQLException {
        Cashier search = cashierDAO.search(user);
        return new CashierDTO(search.getCastUser(), search.getCastPassword(), search.getCastRole());
    }

    @Override
    public ObservableList<CashierDTO> getAllCashier() throws ClassNotFoundException, SQLException {
        ObservableList<Cashier> all = cashierDAO.getAll();
        ObservableList<CashierDTO> allCashier = FXCollections.observableArrayList();
        for (Cashier cashier : all) {
            CashierDTO dto = new CashierDTO(cashier.getCastUser(), cashier.getCastPassword(), cashier.getCastRole());
            allCashier.add(dto);
        }
        return allCashier;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        return cashierDAO.getRowCount();
    }
}
