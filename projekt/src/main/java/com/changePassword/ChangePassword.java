package com.changePassword;

import com.database.DatabaseConnection;
import com.database.PBKDF2_Algorithm;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private PBKDF2_Algorithm algorithm = new PBKDF2_Algorithm();

    public void changePassword(String user_id, String password){
        Connection connection = databaseConnection.getConnection();
        String hashedPassword="";
        try {
            hashedPassword = algorithm.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        String changePasswordQuery = "UPDATE users SET password='"+hashedPassword+"' WHERE user_id='"+user_id+"'";
        try {
            Statement statement = connection.createStatement();
            statement.execute(changePasswordQuery);
            System.out.println("Password changed");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        System.out.println(user_id);

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
            changePassword(user_id, password);
            session.setAttribute("passwordChanged", "true");
            response.sendRedirect("passwordChangedPage.jsp");
        }
        else{
            session.setAttribute("codeCorrect", "true");
            response.sendRedirect("changePasswordPage.jsp");
        }

    }
}
