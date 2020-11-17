/*
*
*
* Wyświetla
*
*
* */


package com.strona.home;

import com.database.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MyAccount", urlPatterns = "/MyAccount")
public class MyAccount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object login = session.getAttribute("login");

        // jak nie jesteśmy zalogowanie odsyła nas do loginPage.jsp
        RequestDispatcher view;
        if (login == null) {
            view = request.getRequestDispatcher("loginPage.jsp");
        } else {
            String dbEmail = User.getEmail(login);
            session.setAttribute("userEmail", dbEmail);
            view = request.getRequestDispatcher("home/myAccount/myAccount.jsp");
        }
        view.forward(request, response);
    }

}
