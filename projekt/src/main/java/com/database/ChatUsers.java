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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChatUsers {

    public static void addCharUser(String userId, String chatId, String nick, boolean is_owner){

    }

    public static void addCharUser(String userId, String chatId, String nick){

    }
    public static boolean addCharUser(String userId, String chatId, boolean isOwner){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String commend;
        if (isOwner)
            commend = "INSERT INTO chat_users (user_id_fk, chat_id_fk, is_owner) VALUES ('"+userId+"', '"+chatId+"', 1);";
         else
            commend = "INSERT INTO chat_users (user_id_fk, chat_id_fk) VALUES ('"+userId+"', '"+chatId+"');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(commend);

            databaseConnection.closeConnection();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
    public static boolean addCharUser(String userId, String chatId){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String commend = "INSERT INTO chat_users (user_id_fk, chat_id_fk) VALUES ('"+userId+"', '"+chatId+"');";

        try {
            Statement statement = connection.createStatement();
            statement.execute(commend);

            databaseConnection.closeConnection();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
