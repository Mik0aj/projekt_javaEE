<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <title>Rejestracja</title>

    <link rel="stylesheet" href="registrationPage/style.css" type="text/css"/>

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>

    <main>
        <div id="container">
            <form action="Registration" method="post">
                <input type="text" name="login" placeholder="Login" onfocus="this.placeholder=''" onblur="this.placeholder='Login'">
                <%
                    if(session.getAttribute("loginError")!=null){
                        out.print("<div class='error'>"+session.getAttribute("loginError")+"</div>");
                    }
                %>
                <input type="text" name="email" placeholder="E-mail" onfocus="this.placeholder=''" onblur="this.placeholder='E-mail'">
                <%
                    if(session.getAttribute("emailError")!=null){
                        out.print("<div class='error'>"+session.getAttribute("emailError")+"</div>");
                    }
                %>
                <input type="password" name="password" placeholder="Hasło"onfocus="this.placeholder=''" onblur="this.placeholder='Hasło'">
                <%
                    if(session.getAttribute("passwordError1")!=null){
                        out.print("<div class='error'>"+session.getAttribute("passwordError1")+"</div>");
                    }
                %>
                <input type="password" name="repeatPassword" placeholder="Powtórz hasło"onfocus="this.placeholder=''" onblur="this.placeholder='Powtórz hasło'">
                <%
                    if(session.getAttribute("passwordError2")!=null){
                        out.print("<div class='error'>"+session.getAttribute("passwordError2")+"</div>");
                    }
                %>
                <label id="policyLabel"><input type="checkbox" name="policy"> Akceptuję <a href="#" id="policyLink">regulamin</a></label>
                <%
                    if(session.getAttribute("policyError")!=null){
                        out.print("<div class='error'>"+session.getAttribute("policyError")+"</div>");
                    }
                %>
                <div class="g-recaptcha" data-sitekey="6LcjhcQZAAAAAIFZDfYLbhyW5xY_ByX9fxNdLPeX"></div>
                <%
                    if(session.getAttribute("captchaError")!=null){
                        out.print("<div class='error'>"+session.getAttribute("captchaError")+"</div>");
                    }
                %>
                <input type="submit" name="registration" value="Zarejestruj się">
                <% session.invalidate(); %>
            </form>
        </div>
    </main>

</body>
</html>
