package com.changePassword;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
    }

    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    //potem wstawić w osobną klasę i zapisywać do bazy danych
    public String generateResetCode() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 8;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        // reads form fields
        String email = request.getParameter("email");

        String code = generateResetCode();

        if(isEmailValid(email)){
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
            /*request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/Result.jsp").forward(request, response);*/
                System.out.println(resultMessage);
            }
        }
        else{
            session.setAttribute("emailError", "Niepoprawny format adresu email.");
            response.sendRedirect("insertEmailPage.jsp");
        }
    }
}