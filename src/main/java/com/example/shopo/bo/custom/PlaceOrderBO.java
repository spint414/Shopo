package com.example.shopo.bo.custom;

import com.example.shopo.bo.SuperBO;
import com.example.shopo.dto.CashierDTO;
import com.example.shopo.dto.CustomDTO;
import com.example.shopo.dto.ItemDTO;
import com.example.shopo.dto.OrdersDTO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PlaceOrderBO extends SuperBO {
    ObservableList<CashierDTO> getAllCashier() throws ClassNotFoundException, SQLException;

    ObservableList<ItemDTO> getAllItem() throws ClassNotFoundException, SQLException;

    CashierDTO searchCashier(String id) throws Exception;

    ItemDTO searchItem(String itemCode) throws Exception;

    boolean placeOrder(OrdersDTO dto) throws ClassNotFoundException, SQLException, Exception;

    ObservableList<CustomDTO> searchOrderFromID(String id) throws ClassNotFoundException, SQLException, Exception;

    int getRowCount()throws ClassNotFoundException,SQLException;

   // boolean updateStock(ArrayList<ItemDTO> orderItems) throws SQLException, ClassNotFoundException;

}
