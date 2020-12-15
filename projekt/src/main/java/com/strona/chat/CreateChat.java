package com.strona.chat;

import com.changePassword.GenerateCode;
import com.database.ChatUsers;
import com.database.Chats;
import com.database.DatabaseConnection;
import com.database.User;

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

@WebServlet("/CreateChat")
public class CreateChat extends HttpServlet {

    ChatUsers chatUsers = new ChatUsers();
    String enterChatCode;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //String login = request.getParameter("userLogin");
        String login = request.getParameter("userLogin");
        //String login = (String) session.getAttribute("login");
        String chatName = request.getParameter("chatName");

        if(chatName.length()<3 || chatName.length()>20){
            session.setAttribute("chatNameError", "Nazwa chatu musi mieć pomiędzy 3 a 20 liter.");
            //session.setAttribute("login", userLogin);
            session.setAttribute("login", login);
            response.sendRedirect("createChat.jsp");
        }
        else{
            enterChatCode = chatUsers.generateCode();
            chatUsers.createNewChat(chatName, enterChatCode);
            chatUsers.addChatUser(User.getId(login), Chats.getId(chatName), true);
            session.setAttribute("login", login);
            session.setAttribute("enterCode", enterChatCode);
            response.sendRedirect("chatCreated.jsp");
        }
    }
}
