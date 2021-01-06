package com.strona.home;

import com.database.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class PreparePage {
    public static void prepareSidebar(HttpServletRequest request){
        // przygotowuje dane będące w lewym pasku (grupy)
        try {
            HttpSession session = request.getSession();
            Object login = session.getAttribute("login");
            String userLogin = (String) login;


            session.setAttribute("chats", ChatUsers.loadUserChats(User.getId(userLogin)));

            sidebarEnterCodeGroup(request, session, login);
        } catch (Exception e){}


    }

    public static void prepareMyGroupsContent(HttpServletRequest request){
        // wysyła dane grup których jesteśmy właścicielami
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");
        String userLogin = (String) login;

        session.setAttribute("ownChats", ChatUsers.loadOwnUserChats(User.getId(userLogin)));
    }

    public static void prepareMessages(HttpServletRequest request){
        // przygotowanie danych (wiadomości w danym czacie)
        String groupID = request.getParameter("groupID");
        ArrayList<Message> messages = ChatMessages.getChatMessages(groupID);
        HttpSession session = request.getSession();

        String userId = User.getId(session.getAttribute("login"));

        session.setAttribute("userId", userId);
        session.setAttribute("messages", messages);
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
