package com.strona.home;

import com.database.ChatUsers;
import com.database.Chats;
import com.database.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Sidebar {
    public static void doPost(HttpServletRequest request) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            if (request.getParameter("groupCode").length() > 0) {
                String groupCode = request.getParameter("groupCode");
                String groupId = Chats.getIdByEnterCode(groupCode);
                //System.out.println("dziala post, kod: " + groupCode + " ID grupy: " + groupId);
                Object login = session.getAttribute("login");
                String userId = User.getId(login.toString());

                //System.out.println("User: " + login + " id: " + userId);

                ChatUsers.addChatUser(userId, groupId);

                session.setAttribute("chats", ChatUsers.loadUserChats(userId));
            }
        } catch (Exception e){}


    }
}
