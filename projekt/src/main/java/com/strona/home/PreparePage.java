package com.strona.home;

import com.database.ChatUsers;
import com.database.Chats;
import com.database.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PreparePage {
    public static void prepareSidebar(HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            Object login = session.getAttribute("login");
            String userLogin = (String) login;


            session.setAttribute("chats", ChatUsers.loadUserChats(User.getId(userLogin)));

            sidebarEnterCodeGroup(request, session, login);
        } catch (Exception e){}


    }

    public static void prepareMyGroupsContent(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");
        String userLogin = (String) login;

        session.setAttribute("ownChats", ChatUsers.loadOwnUserChats(User.getId(userLogin)));
    }

    private static void sidebarEnterCodeGroup(HttpServletRequest request, HttpSession session, Object login) {
        // jeśli został wpowadzony kod
        if (request.getParameter("groupCode").length() > 0) {
            String groupCode = request.getParameter("groupCode");
            String groupId = Chats.getIdByEnterCode(groupCode);
            //System.out.println("dziala post, kod: " + groupCode + " ID grupy: " + groupId);
            String userId = User.getId(login.toString());

            //System.out.println("User: " + login + " id: " + userId);

            ChatUsers.addChatUser(userId, groupId);

            session.setAttribute("chats", ChatUsers.loadUserChats(userId));
        }
    }

}
