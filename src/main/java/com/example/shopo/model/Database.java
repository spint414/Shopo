package com.example.shopo.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    Connection linkDatabase;
    String databaseName = "isrpfpwk_ShopM";
    String user = "isrpfpwk_ShopM";
    String password = "project2";
    String URL = "jdbc:mysql://103.97.126.29/" + databaseName;

    public Database(){}

    public Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            linkDatabase = DriverManager.getConnection(URL, user, password);
        } catch (Exception e) {
            System.out.println("Failed to connect to the database!");
        }

        return linkDatabase;
    }
}
