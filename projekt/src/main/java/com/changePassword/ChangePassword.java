package com.changePassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");

        boolean everythingOK = true;

        //sprawdzenie czy hasło ma od 8 do 20 znaków
        if(password.length()<8 || password.length()>25){
            everythingOK = false;
            session.setAttribute("passwordError1", "Hasło powinno mieć od 8 do 25 znaków.");
        }

        //sprawdzenie czy oba hasła są takie same
        if(!repeatPassword.equals(password)){
            everythingOK = false;
            session.setAttribute("passwordError2", "Hasła nie są takie same.");
        }

        if(everythingOK){
            session.setAttribute("passwordChanged", "true");
            response.sendRedirect("passwordChangedPage.jsp");
        }
        else{
            session.setAttribute("codeCorrect", "true");
            response.sendRedirect("changePasswordPage.jsp");
        }

    }
}
