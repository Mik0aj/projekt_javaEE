package com.strona;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if(login.equals("admin") && password.equals("admin")){
            HttpSession session = request.getSession();
            session.setAttribute("login", login);
            response.sendRedirect("mainPage.jsp");
        }
        else{
            response.sendRedirect("loginPage.jsp");
        }
    }
}
