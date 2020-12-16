package com.strona.home;

import com.database.ChatUsers;
import com.database.Chats;
import com.database.DatabaseConnection;
import com.database.User;
import com.strona.chat.Chat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sidebar {
    static DatabaseConnection databaseConnection = new DatabaseConnection();
    static String userID;
    static List<Chat> chatList = new ArrayList<>();

    public static void doPost(HttpServletRequest request) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Object login = session.getAttribute("login");
            String userLogin = (String) login;

            loadChats(userLogin);
            session.setAttribute("chats", chatList);

            if (request.getParameter("groupCode").length() > 0) {
                String groupCode = request.getParameter("groupCode");
                String groupId = Chats.getIdByEnterCode(groupCode);
                //System.out.println("dziala post, kod: " + groupCode + " ID grupy: " + groupId);
                String userId = User.getId(login.toString());

                //System.out.println("User: " + login + " id: " + userId);

                ChatUsers.addChatUser(userId, groupId);

                session.setAttribute("chats", ChatUsers.loadUserChats(userId));
            }
        } catch (Exception e){}


    }

    public static void loadChats(String login){
        Connection connection = databaseConnection.getConnection();

        chatList.clear();
        String getUserIDQuery = "SELECT user_id FROM users WHERE login='"+login+"';";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(getUserIDQuery);
            if(queryResult.next()){
                userID=queryResult.getString("user_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String loadChatsQuery = "SELECT chat_users.chat_id_fk, chats.name FROM chats INNER JOIN chat_users ON chats.chat_id=chat_users.chat_id_fk WHERE chat_users.user_id_fk="+userID+";";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(loadChatsQuery);
            while(queryResult.next()){
                Chat chat = new Chat();
                chat.setChatID(queryResult.getString("chat_id_fk"));
                chat.setChatName(queryResult.getString("name"));
                chatList.add(chat);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

}
