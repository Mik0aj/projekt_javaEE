package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;
    private final String databaseURL = "jdbc:mysql://mysql10.mydevil.net/m1450_Discord2";
    private final String databaseUser = "m1450_discord2";
    private final String databasePassword = "VNBD0P3RIyDQPqPAO2CI";
    private final String databaseDriver = "com.mysql.cj.jdbc.Driver";

    public Connection getConnection() {
        try{
            Class.forName(databaseDriver);
            connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
            System.out.println("Connected to Database.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
