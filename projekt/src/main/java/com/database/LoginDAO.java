package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    PBKDF2_Algorithm hashAlgorithm = new PBKDF2_Algorithm();

    public boolean verifyLogin(String login, String password) {
        Connection connection = databaseConnection.getConnection();
        String verifyLoginQuery = "SELECT login, password FROM `users` WHERE login='"+login+"'";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginQuery);
            if(queryResult.next()){
                String dbPassword = queryResult.getString("password");
                if(hashAlgorithm.validatePassword(password, dbPassword)){
                    databaseConnection.closeConnection();
                    return true;
                }
                else{
                    databaseConnection.closeConnection();
                    return false;
                }
            }
            else{
                databaseConnection.closeConnection();
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
