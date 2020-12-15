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

@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    String userID;
    List<Chat> chatList = new ArrayList<>();

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
            if(session.getAttribute("alreadyVisitedHome")==null){
                loadChats(userLogin);
                session.setAttribute("chats", chatList);
            }
            view = request.getRequestDispatcher("home/home.jsp");
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
