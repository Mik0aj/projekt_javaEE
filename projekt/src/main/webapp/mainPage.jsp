<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Komunikator</title>
    <meta name="description" content="Strona główna komunikatora"/>
</head>
<body>

    <%
        if(session.getAttribute("login")==null){
            response.sendRedirect("loginPage.jsp");
        }
    %>

    <main>
        <header>
            <h1>Witamy ${login} na stronie głównej komunikatora.</h1>
        </header>
        <form action="Logout" method="post">
            <input type="submit" value="Wyloguj się">
        </form>
    </main>
</body>
</html>
