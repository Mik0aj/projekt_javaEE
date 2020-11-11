<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8"/>
    <title>Wprowadź email</title>
    <meta name="description" content="Wprowadź email by zresetować hasło"/>

    <link rel="stylesheet" href="insertEmailPage/style.css" type="text/css"/>

</head>
<body>

    <div id="container">
        <div id="opis">
            <div id="naglowek">
                Zapomniałeś hasła?
            </div>
            <div>
                Nie ma problemu. Podaj poniżej email, którego użyłeś do rejestracji, a wyślemy kod resetujący hasło na podanego emaila.
            </div>
        </div>
        <form action="SendEmail" method="post">
            <input type="text" name="email" placeholder="Email" onfocus="this.placeholder=''" onblur="this.placeholder='Email'">
            <%
                if(session.getAttribute("emailError")!=null){
                    out.print("<div class='error'>"+session.getAttribute("emailError")+"</div>");
                }
            %>
            <input type="submit" value="Wyślij kod">
            <% session.invalidate(); %>
        </form>
    </div>

</body>
</html>
