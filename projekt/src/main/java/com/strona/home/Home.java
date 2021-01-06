package com.strona.home;

import com.database.ChatMessages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher view;

        String message = request.getParameter("message");
        System.out.println(message);
        if (message.length() > 0) { // jeśli jest wiadomość

            ChatMessages.createNewMessage(
                    session.getAttribute("groupID").toString(),
                    session.getAttribute("userId").toString(),
                    message);

            PreparePage.prepareMessages(request);

            //response.setHeader("groupID", session.getAttribute("groupID").toString());
            doGet(request,response);
        } else {
            // pass

        }

        PreparePage.prepareSidebar(request);

        view = request.getRequestDispatcher("home/home.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");

        // jak nie jesteśmy zalogowanie odsyła nas do loginPage.jsp
        RequestDispatcher view;
        if (login == null) {
            view = request.getRequestDispatcher("loginPage.jsp");
        } else {

            String groupID = request.getParameter("groupID");
            // jeśli mamy wybraną grupę
            if (groupID != null) {
                PreparePage.prepareMessages(request);
                session.setAttribute("groupID", groupID);
            } else {
                System.out.println("Nie wybrano grupy");
            }

            PreparePage.prepareSidebar(request);
            view = request.getRequestDispatcher("home/home.jsp");



        }
        view.forward(request, response);
    }
}
