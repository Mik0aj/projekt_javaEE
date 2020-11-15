package com.changePassword;

import com.database.DatabaseConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;

    private EmailUtility emailUtility = new EmailUtility();
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private GenerateCode generateCode = new GenerateCode();

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    public boolean isEmailInDatabase(String email)
    {
        Connection connection = databaseConnection.getConnection();
        String verifyEmailQuery = "SELECT * FROM `users` WHERE email='"+email+"'";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyEmailQuery);
            if(queryResult.next()){
                databaseConnection.closeConnection();
                return true;
            }
            else{
                databaseConnection.closeConnection();
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String addCodeToDatabase(String email){
        Connection connection = databaseConnection.getConnection();
        String code="";
        boolean isOK = false;
        while(!isOK){
            code = generateCode.generateResetCode();
            String addVeryfingCodeValueQuery = "UPDATE user_verifying_code SET code='"+code+"' WHERE user_id=(SELECT user_id FROM users WHERE email='"+email+"')";
            try {
                Statement statement = connection.createStatement();
                statement.execute(addVeryfingCodeValueQuery);
                isOK = true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                isOK = false;
            }
        }
        databaseConnection.closeConnection();
        return code;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // reads form fields
        String email = request.getParameter("email");

        if(isEmailInDatabase(email)){
            String code = addCodeToDatabase(email);

            String subject = "Resetowanie hasła";
            String content = "Twój kod do zresetowania hasła: "+code;

            String resultMessage = "";

            try {
                emailUtility.sendEmail(host, port, user, pass, email, subject,
                        content);
                resultMessage = "E-mail was sent successfully";
                session.setAttribute("emailSent", "true");
                response.sendRedirect("enterCodePage.jsp");
            } catch (Exception ex) {
                ex.printStackTrace();
                resultMessage = "There were an error: " + ex.getMessage();
            } finally {
                System.out.println(resultMessage);
            }
        }
        else{
            session.setAttribute("emailError", "Podanego adresu email nie ma w bazie danych użytkowników.");
            response.sendRedirect("insertEmailPage.jsp");
        }
    }
}