/*
*
*  Klasa umożliwia pobiebranie danych o
*  danym użytkowniku
*
*  większość metod powinna być statyczna
*  Kiedy znamy jedno pole rekordu powinniśmy onnaleźć pozostałe
*
*
* */

package com.database;


import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    @NotNull
    public static String getEmail(Object login) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String userEmail = "SELECT email FROM `users` WHERE login='"+ login +"'";

        String dbEmail = null;

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(userEmail);
            if(queryResult.next()){
                dbEmail = queryResult.getString("email");

            }
            else{
                databaseConnection.closeConnection();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dbEmail;
    }

    @NotNull
    public static String getLogin(Object email) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String userEmail = "SELECT login FROM `users` WHERE email='"+ email +"'";

        String dbLogin = null;

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(userEmail);
            if(queryResult.next()){
                dbLogin = queryResult.getString("login");

            }
            else{
                databaseConnection.closeConnection();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dbLogin;
    }

    @NotNull
    public static String getId(Object login) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String userEmail = "SELECT user_id FROM `users` WHERE login='"+ login +"'";

        String dbId = null;

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(userEmail);
            if(queryResult.next()){
                dbId = queryResult.getString("user_id");

            }
            else{
                databaseConnection.closeConnection();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return dbId;
    }

    public static boolean setEmail(String oldEmail, String newEmail) {
        // jeśli zmienimy adres email otrzmamy true
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String commend = "UPDATE `users` SET email = '"+ newEmail +"' WHERE email='"+ oldEmail +"'";

        try {
            Statement statement = connection.createStatement();
            statement.execute(commend);

            databaseConnection.closeConnection();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }




    //  To jeszcze nie wiem czy dobrze
    public static void deleteUser(Object login){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String deleteCommend = "DELETE FROM `users` WHERE login='"+ login +"'";

        try {
            Statement statement = connection.createStatement();
            statement.executeQuery(deleteCommend);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

}
