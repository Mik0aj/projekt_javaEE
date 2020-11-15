package com.changePassword;

import com.database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/VerifyCode")
public class VerifyCode extends HttpServlet {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public String isCodeInDatabase(String code){
        Connection connection = databaseConnection.getConnection();
        String user_id=" ";
        String isCodeinDatabaseQuery = "SELECT code, user_id FROM user_verifying_code WHERE code='"+code+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(isCodeinDatabaseQuery);
            if (queryResult.next()) {
                return user_id = queryResult.getString("user_id");
            }
            else{
                return " ";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return " ";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String code = request.getParameter("code");
        String user_id = isCodeInDatabase(code);

        if(!user_id.equals(" ")){
            session.setAttribute("codeCorrect", "true");
            session.setAttribute("user_id", user_id);
            response.sendRedirect("changePasswordPage.jsp");
        }
        else{
            session.setAttribute("emailSent", "true");
            session.setAttribute("codeError", "Kod niepoprawny. Wprowadź ponownie.");
            response.sendRedirect("enterCodePage.jsp");
        }
    }
}
