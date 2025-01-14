package com.example.shopo.bo.custom.Impl;

import com.example.shopo.TM.dtmTM;
import com.example.shopo.bo.custom.PlaceOrderBO;
import com.example.shopo.dao.DAOFactory;
import com.example.shopo.dao.custom.*;
import com.example.shopo.dao.custom.impl.PaymentDAOImpl;
import com.example.shopo.db.DBConnection;
import com.example.shopo.dto.*;
import com.example.shopo.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CashierDAO cashierDAO = (CashierDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CASHIER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderdetailDAO orderdetailDAO = (OrderdetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    PaymentDAO paymentDAO = new PaymentDAOImpl();

    public static String getRandomNumberString() {
        {
            Random rand = new Random();
            int rand_int1 = rand.nextInt(100000);
            return String.valueOf(rand_int1);
        }
    }

    @Override
    public ObservableList<CashierDTO> getAllCashier() throws ClassNotFoundException, SQLException {
        ObservableList<Cashier> all = cashierDAO.getAll();
        ObservableList<CashierDTO> allCashier = FXCollections.observableArrayList();
        for (Cashier ID : all) {
            CashierDTO dto = new CashierDTO(ID.getCastUser(), ID.getCastPassword(), ID.getCastRole());
            allCashier.add(dto);
        }
        return allCashier;
    }

    @Override
    public ObservableList<ItemDTO> getAllItem() throws ClassNotFoundException, SQLException {
        ObservableList<Item> all = itemDAO.getAll();
        ObservableList<ItemDTO> allItem = FXCollections.observableArrayList();
        for (Item ID : all) {
            ItemDTO dto = new ItemDTO(ID.getItemCode(), ID.getSuplayerID(), ID.getDescription(), ID.getPackSize(), ID.getUnitPrice(), ID.getQtyOnHand(), ID.getBatchID());
            allItem.add(dto);
        }
        return allItem;
    }

    @Override
    public CashierDTO searchCashier(String id) throws Exception {
        Cashier search = cashierDAO.search(id);
        return new CashierDTO(search.getCastUser(), search.getCastPassword(), search.getCastRole());
    }

    @Override
    public ItemDTO searchItem(String itemCode) throws Exception {
        Item search = itemDAO.search(itemCode);
        return new ItemDTO(search.getItemCode(), search.getSuplayerID(), search.getDescription(), search.getPackSize(), search.getUnitPrice(), search.getQtyOnHand(), search.getBatchID());
    }

    @Override
    public boolean placeOrder(OrdersDTO dto) throws ClassNotFoundException, SQLException, Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Orders orders = new Orders(dto.getOrderID(), dto.getOrderDate(), dto.getOrderTime(), dto.getCustName(), dto.getCustPhoneNo(), dto.getCustAddress(), dto.getCustEmail(), dto.getCastID());
        boolean add = ordersDAO.add(orders);
        try {
            if (add) {

                boolean isOrderDetailsAdded= addOrderDetail(dto);
                if (isOrderDetailsAdded) {
                    boolean isPaymentAdded = paymentDAO.add(new Payment(getRandomNumberString(), dto.getCustName(), dto.getAmount(), dto.getDiscount(), dto.getOrderID()));
                    if (isPaymentAdded) {
                        boolean isUpdateStock = updateStock(dto.getAllOrderDetail());
                        if (isUpdateStock) {
                            connection.commit();
                            return true;
                        }
                    }
                }

                connection.commit();
                return true;

            } else {
                connection.rollback();
                return false;

            }
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean addOrderDetail(OrdersDTO dto) throws SQLException, ClassNotFoundException {
        for (dtmTM ord : dto.getAllOrderDetail()) {
            Orderdetail orderTable = new Orderdetail(dto.getOrderID(), ord.getCode(), ord.getQTY(), ord.getPrice());
            System.out.println("");
            boolean isAddedOrderDetails = orderdetailDAO.add(orderTable);
            if (!isAddedOrderDetails) {
                return false;
            }
        }
        return true;
    }

    private boolean updateStock(ArrayList<dtmTM> orderItems) throws SQLException, ClassNotFoundException {
        for (dtmTM orderDetail : orderItems) {
            boolean isUpdateStock = itemDAO.updateStock(orderDetail);
            if (!isUpdateStock) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ObservableList<CustomDTO> searchOrderFromID(String id) throws ClassNotFoundException, SQLException, Exception {
        ObservableList<CustomEntity> od = (ObservableList<CustomEntity>) queryDAO.getOrderDetailsFromOID(id);
        ObservableList<CustomDTO> allDetails = FXCollections.observableArrayList();
        for (CustomEntity C : od) {
            CustomDTO dto = new CustomDTO(C.getOrderID(), C.getItemCode(), C.getOrderQTY(), C.getUnitPrice(), C.getDiscount(), C.getOrderDate(), C.getOrderTime(), C.getCustName(), C.getCustPhoneNo(), C.getCustAddress(), C.getCustEmail(), C.getCastID());
            allDetails.add(dto);
        }
        return allDetails;
    }

    @Override
    public int getRowCount() throws ClassNotFoundException, SQLException {
        return ordersDAO.getRowCount();
    }
}
