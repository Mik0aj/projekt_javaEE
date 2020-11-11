package com.strona;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("loginPage.jsp");

        view.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        boolean everythingOK = true;
        if(!login.equals("admin")){
            everythingOK = false;
            session.setAttribute("loginError", "Niepoprawny login.");
        }
        if(!password.equals("admin")){
            everythingOK = false;
            session.setAttribute("passwordError", "Niepoprawne hasło.");
        }
        if(everythingOK){

            session.setAttribute("login", login);
            response.sendRedirect("mainPage.jsp");
        }
        else{
            response.sendRedirect("loginPage.jsp");
        }
    }
}
