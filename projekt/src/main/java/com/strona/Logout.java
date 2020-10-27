package com.strona;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // przekierowujemu na /login poniewaz uzytkownik nie jest zalogowany
        RequestDispatcher view = request.getRequestDispatcher("loginPage.jsp");

        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        RequestDispatcher view = request.getRequestDispatcher("loginPage.jsp");
        view.forward(request, response);
    }
}
