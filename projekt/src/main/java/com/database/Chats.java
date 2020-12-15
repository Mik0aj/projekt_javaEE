/*
 *
 *  Klasa umożliwia pobiebranie, modyfikowanie,
 *  dodawanie danych o danym użytkowniku
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

public class Chats {
    @NotNull
    public static String getId(Object chatName) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String userEmail = "SELECT chat_id FROM `chats` WHERE name ='"+ chatName +"'";

        String dbId = null;

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(userEmail);
            if(queryResult.next()){
                dbId = queryResult.getString("chat_id");

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

    @NotNull
    public static String getIdByEnterCode(Object enterCode) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String userEmail = "SELECT chat_id FROM `chats` WHERE enter_code ='"+ enterCode +"'";

        String dbId = null;

        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(userEmail);
            if(queryResult.next()){
                dbId = queryResult.getString("chat_id");

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
}
