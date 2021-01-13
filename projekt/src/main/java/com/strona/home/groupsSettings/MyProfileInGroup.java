package com.strona.home.groupsSettings;

import com.database.ChatUsers;
import com.strona.home.PreparePage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MyProfileInGroup", urlPatterns = {"/MyProfileInGroup"})
public class MyProfileInGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher view;

        Object userID = session.getAttribute("userId");
        Object groupID = session.getAttribute("groupID");

        if (userID == null || groupID == null) {
            // pass
        } else {
            String nick = request.getParameter("nick");
            String leaveGroup = request.getParameter("leaveGroup");

            if (nick != null) {
                ChatUsers.setNick((String) userID, (String) groupID, nick);
            }
            if (leaveGroup != null) {
                ChatUsers.deleteChatUser((String) userID, (String) groupID);
                session.setAttribute("groupID", null);
                PreparePage.prepareSidebar(request);
            }


        }

        view = request.getRequestDispatcher("home/home.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Wyświetla ustawienia mojego profilu w grupie (zmiana niku, wyjście z grupy)

        HttpSession session = request.getSession();
        RequestDispatcher view;

        Object userID = session.getAttribute("userId");
        Object groupID = session.getAttribute("groupID");

        if (userID == null || groupID == null) {
            // jeśli nie ma którejś z informacji odsyła do serwletu home
            view = request.getRequestDispatcher("home");
        } else {
            view = request.getRequestDispatcher("home/groupsSettings/myProfileInGroup.jsp");
        }
        view.forward(request, response);

    }
}
