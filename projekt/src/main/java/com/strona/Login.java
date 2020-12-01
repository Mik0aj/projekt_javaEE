package com.strona;

import com.database.LoginDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet {

    private LoginDAO loginDAO = new LoginDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("loginPage.jsp");

        view.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        if(loginDAO.verifyLogin(login, password)){
            session.setAttribute("login", login);
            //RequestDispatcher view = request.getRequestDispatcher("home.jsp");
            response.sendRedirect("home");
            //view.forward(request, response);
        }
        else{
            session.setAttribute("error", "Niepoprawny login lub hasło.");
            response.sendRedirect("loginPage.jsp");
        }
    }
}
