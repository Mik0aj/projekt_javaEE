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
}
