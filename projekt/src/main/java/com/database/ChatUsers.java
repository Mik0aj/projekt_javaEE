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

import com.changePassword.GenerateCode;
import com.strona.chat.Chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChatUsers {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private String enterChatCode;

    public static void addChatUser(String userId, String chatId, String nick, boolean is_owner){

    }

    public static void addChatUser(String userId, String chatId, String nick){

    }

    public String generateCode(){
        Connection connection = databaseConnection.getConnection();
        GenerateCode generateCode = new GenerateCode();
        String chatCode="0000000000";
        boolean isOK = false;
        while(!isOK){
            chatCode = generateCode.generateChatCode();
            String checkIfCodeExistQuery = "SELECT * FROM chats WHERE enter_code='"+chatCode+"';";
            try {
                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(checkIfCodeExistQuery);
                if (queryResult.next()) {
                    isOK=false;
                }
                else{
                    return chatCode;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        databaseConnection.closeConnection();
        return chatCode;
    }

    public void createNewChat(String chatName, String enterChatCode){
        Connection connection = databaseConnection.getConnection();

        String addNewChatIntoChatsTableQuery = "INSERT INTO chats (enter_code, name) VALUES ('"+enterChatCode+"', '"+chatName+"');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addNewChatIntoChatsTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    public static boolean addChatUser(String userId, String chatId, boolean isOwner){
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
    public static boolean addChatUser(String userId, String chatId){
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

    public static ArrayList<Chat> loadUserChats(String userID){
        // Wszystkie czaty do których przynalaży użytkownik
        ArrayList<Chat> userChats = new ArrayList();
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        String loadChatsQuery = "SELECT chat_users.chat_id_fk, chats.name, chats.enter_code FROM chats INNER JOIN chat_users ON chats.chat_id=chat_users.chat_id_fk WHERE chat_users.user_id_fk="+userID+";";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(loadChatsQuery);
            while(queryResult.next()){
                Chat chat = new Chat();
                chat.setChatID(queryResult.getString("chat_id_fk"));
                chat.setChatName(queryResult.getString("name"));
                chat.setEnterCode(queryResult.getString("enter_code"));
                userChats.add(chat);
                //userChats.add(queryResult.getString("name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        databaseConnection.closeConnection();

        return userChats;
    }
}
