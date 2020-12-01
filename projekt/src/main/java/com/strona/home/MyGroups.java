/*
 * Serwlet wyświetla grupy które stworzył użytkownik
 *
 *
 *
 *
 * */

package com.strona.home;

import com.database.DatabaseConnection;
import com.strona.chat.Chat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MyGroups", urlPatterns = {"/MyGroups"})
public class MyGroups extends HttpServlet {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    String userID;
    List<Chat> ownChatList = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");
        String userLogin = (String) login;

        // jak nie jesteśmy zalogowanie odsyła nas do loginPage.jsp
        RequestDispatcher view;
        if (login == null) {
            view = request.getRequestDispatcher("loginPage.jsp");
        } else {
            if(session.getAttribute("alreadyVisitedMyGroups")==null) {
                loadChats(userLogin);
                session.setAttribute("ownChats", ownChatList);
                for(Chat chat : ownChatList){
                    System.out.print(chat.getChatName());
                    System.out.println(chat.getEnterCode());
                }
            }
            view = request.getRequestDispatcher("home/myGroups.jsp");
        }

        view.forward(request, response);
    }

    public void loadChats(String login){
        Connection connection = databaseConnection.getConnection();

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
        String loadChatsQuery = "SELECT user"+userID+"_chats.chat_id, chats.name, chats.enter_code FROM chats INNER JOIN user"+userID+"_chats ON chats.chat_id=user"+userID+"_chats.chat_id WHERE is_owner=1;";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(loadChatsQuery);
            while(queryResult.next()){
                Chat chat = new Chat();
                chat.setChatID(queryResult.getString("chat_id"));
                chat.setChatName(queryResult.getString("name"));
                chat.setEnterCode(queryResult.getString("enter_code"));
                ownChatList.add(chat);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        databaseConnection.closeConnection();
    }
}
