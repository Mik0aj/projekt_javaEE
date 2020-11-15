<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8"/>
    <title>Zmień hasło</title>
    <meta name="description" content="Zmień hasło"/>

    <link rel="stylesheet" href="changePasswordPage/style.css" type="text/css"/>

    <%
        if(!(session.getAttribute("codeCorrect").equals("true"))){
            response.sendRedirect("enterCodePage.jsp");
        }
    %>

</head>
<body>

    <div id="container">
        <div id="tekst">
            Wpisz w oba pola nowe hasło.
        </div>
        <form action="ChangePassword" method="post">
            <input type="hidden" name="user_id" value="<%= session.getAttribute("user_id") %>" />
            <input type="password" name="password" placeholder="Nowe hasło" onfocus="this.placeholder=''" onblur="this.placeholder='Nowe hasło'">
            <%
                if(session.getAttribute("passwordError1")!=null){
                    out.print("<div class='error'>"+session.getAttribute("passwordError1")+"</div>");
                }
            %>
            <input type="password" name="repeatPassword" placeholder="Powtórz hasło" onfocus="this.placeholder=''" onblur="this.placeholder='Powtórz hasło'">
            <%
                if(session.getAttribute("passwordError2")!=null){
                    out.print("<div class='error'>"+session.getAttribute("passwordError2")+"</div>");
                }
            %>
            <input type="submit" value="Zmień hasło">
            <% session.invalidate(); %>
        </form>
    </div>

</body>
</html>
