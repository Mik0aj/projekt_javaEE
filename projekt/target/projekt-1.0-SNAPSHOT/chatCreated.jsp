<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8"/>
    <title>Chat utworzony</title>
    <meta name="description" content="Gratuacje! Utworzono nowy chat"/>

    <link rel="stylesheet" href="chatCreated/style.css" type="text/css"/>

</head>
<body>

<%
    if(session.getAttribute("login")==null){
        response.sendRedirect("loginPage.jsp");
    }
%>

<div id="container">
    <div id="opis">
        <div id="naglowek">
            Gratulacje ${sessionScope.login}!<br/>
            Utworzyłeś chat
        </div>
        <div>
            Poniżej znajduje się kod, któy możesz wysłać swoim znajomym, by mogli dołączyć do chatu.
        </div>
        <div id="kod">
            ${sessionScope.enterCode}
        </div>
    </div>
    <a href="home"><div id="przycisk">
        Przejdź do strony głównej
    </div></a>
</div>

</body>
</html>
