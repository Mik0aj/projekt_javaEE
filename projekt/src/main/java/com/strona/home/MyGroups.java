/*
 * Serwlet wyświetla grupy które stworzył użytkownik
 *
 *
 *
 *
 * */

package com.strona.home;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MyGroups", urlPatterns = {"/MyGroups"})
public class MyGroups extends HttpServlet {
    private void servletContent(HttpServletRequest request){
        // przygotowuje dane dla serwletu MyGroups
        PreparePage.prepareMyGroupsContent(request);
        PreparePage.prepareSidebar(request);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        servletContent(request);

        RequestDispatcher view = request.getRequestDispatcher("home/myGroups.jsp");
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");
        String userLogin = (String) login;
        PreparePage.prepareSidebar(request);

        // jak nie jesteśmy zalogowanie odsyła nas do loginPage.jsp
        RequestDispatcher view;
        if (login == null) {
            view = request.getRequestDispatcher("loginPage.jsp");
        } else {
            servletContent(request);
            view = request.getRequestDispatcher("home/myGroups.jsp");
        }

        view.forward(request, response);
    }
}
