package com.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChatMessages {
    // klasa umożliwia dodawanie pobieranie wiadomości

    //private DatabaseConnection databaseConnection = new DatabaseConnection();

    public static void createNewMessage(String chatID, String userID, String content){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String addNewChatIntoChatsTableQuery = "INSERT INTO chat_messages (chat_id_fk, user_id_fk, content) VALUES ('"+chatID+"', '"+userID+"', '"+content+"');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addNewChatIntoChatsTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }


    public static ArrayList<Message> getChatMessages(String chatID){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        ArrayList<Message> chatMessages = new ArrayList();

        String loadChatsQuery = "SELECT message_id_pk, chat_id_fk, user_id_fk, content, send_time FROM chat_messages WHERE chat_id_fk="+chatID+";";;
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(loadChatsQuery);
            while(queryResult.next()){
                Message message = new Message(
                        queryResult.getString("message_id_pk"),
                        queryResult.getString("chat_id_fk"),
                        queryResult.getString("user_id_fk"),
                        queryResult.getString("content"),
                        queryResult.getString("send_time"));


                chatMessages.add(message);
                //userChats.add(queryResult.getString("name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        databaseConnection.closeConnection();

        return chatMessages;
    }
}

