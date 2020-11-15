package com.strona;

import com.database.RegistrationDAO;
import com.strona.verify.VerifyReCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
    private VerifyReCaptcha verifyReCaptcha = new VerifyReCaptcha();
    private RegistrationDAO registrationDAO = new RegistrationDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String reCaptcha = request.getParameter("g-recaptcha-response");
        String policy = request.getParameter("policy");

        boolean everythingOK = true;
        HttpSession session = request.getSession();

        //sprawdzenie czy login ma odpowiednią ilość znaków
        if(login.length()<4 || login.length()>20){
            everythingOK = false;
            session.setAttribute("loginError", "Login musi składać się z od 4 do 20 znaków.");
        }

        //sprawdzenie czy login jest już w bazie danych
        if(registrationDAO.isLoginNotAvailable(login)){
            everythingOK = false;
            session.setAttribute("loginError", "Ten login jest już zajęty.");
        }

        //sprawdzenie czy email jest w poprawnej formie
        if(!isEmailValid(email)){
            everythingOK = false;
            session.setAttribute("emailError", "Niepoprawny format adresu email.");
        }

        //sprawdzenie czy email jest już w bazie danych
        if(registrationDAO.isEmailNotAvailable(email)){
            everythingOK = false;
            session.setAttribute("emailError", "Podany adres email jest już w bazie danych.");
        }

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

        //sprawdzenie czy zaznaczono checkboxa
        if(policy==null){
            everythingOK = false;
            session.setAttribute("policyError", "Wymagane zaznaczenie regulaminu.");
        }

        //sprawdzenie czy zaznaczono captche
        if(!verifyReCaptcha.verify(reCaptcha)){
            everythingOK = false;
            session.setAttribute("captchaError", "Wymagane zaznaczenie reCaptchy.");
        }

        if(everythingOK){
            registrationDAO.addUser(login, password, email);
            response.sendRedirect("loginPage.jsp");
        }
        else{
            response.sendRedirect("registrationPage.jsp");
        }
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
}
