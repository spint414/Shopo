package com.example.shopo.model;

import java.sql.*;

public class admin {
    public static String currentAdmin;
    public static String currentAdminRole;

    public admin() {
    }

    public static void setCurrentAdmin(String currentAdmin) {
        admin.currentAdmin = currentAdmin;
    }
    public static void setCurrentAdminRole(String currentAdminRole) {
        admin.currentAdminRole = currentAdminRole;
    }

    public static String getCurrentAdmin() {
        return currentAdmin;
    }
    public static String getCurrentAdminRole() {
        return currentAdminRole;
    }

    public static void ActiveStatus () {
        Database db = new Database();
        Connection connectDb = db.connect();
        String changeStatus = "UPDATE admin_account SET status = 'Active' WHERE admin_username = '" + admin.getCurrentAdmin() + "'";
        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(changeStatus);
        } catch (Exception e) {
        }
    }

    public static void OfflineStatus () {
        Database db = new Database();
        Connection connectDb = db.connect();
        String changeStatus = "UPDATE admin_account SET status = 'Offline' WHERE admin_username = '" + admin.getCurrentAdmin() + "'";
        try {
            Statement statement = connectDb.createStatement();
            statement.executeUpdate(changeStatus);
        } catch (Exception e) {
        }
    }
}
