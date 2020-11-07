<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8"/>
    <title>Zmień hasło</title>
    <meta name="description" content="Zmień hasło"/>

    <link rel="stylesheet" href="passwordChangedPage/style.css" type="text/css"/>

    <%
        if(!(session.getAttribute("passwordChanged").equals("true"))){
            response.sendRedirect("changePasswordPage.jsp");
        }
    %>

</head>
<body>

    <div id="container">
        <div id="opis">
            <div id="naglowek">
                Gratulacje!
            </div>
            <div id="tekst">
                Poprawnie zmieniono hasło. Możesz się teraz zalogować na swoje konto.
            </div>
        </div>
        <input type="submit" value="Do strony logowania" onclick="location.href='loginPage.jsp'">
        <% session.invalidate(); %>
    </div>

</body>
</html>
