<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8"/>
    <title>Utwórz chat</title>
    <meta name="description" content="Utwórz nowy chat do konwersacji ze znajomymi"/>

    <link rel="stylesheet" href="createChat/style.css" type="text/css"/>

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
            Utwórz nowy chat ${sessionScope.login}
        </div>
        <div>
            Wpisz poniżej nazwę nowego chatu, a następnie kliknij "Utwórz", a otrzymasz kod, który umożliwi innym dołączenie do chatu.
        </div>
    </div>
    <form action="CreateChat" method="post">
        <input type="hidden" name="userLogin" value=${sessionScope.login}>
        <input type="text" name="chatName" placeholder="Nazwa chatu" onfocus="this.placeholder=''" onblur="this.placeholder='Nazwa chatu'">
        <%
            if(request.getAttribute("chatNameError")!=null){
                out.print("<div id='chatNameError' class='error'>"+request.getAttribute("chatNameError")+"</div>");
            }
        %>
        <input type="submit" value="Utwórz chat">
        <% session.invalidate(); %>
    </form>
</div>

</body>
</html>
