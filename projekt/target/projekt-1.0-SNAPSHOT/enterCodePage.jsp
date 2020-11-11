<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>

    <meta charset="utf-8"/>
    <title>Wprowadź kod</title>
    <meta name="description" content="Wprowadź kod by zresetować hasło"/>

    <link rel="stylesheet" href="enterCodePage/style.css" type="text/css"/>

    <%
        if(!(session.getAttribute("emailSent").equals("true"))){
            response.sendRedirect("insertEmailPage.jsp");
        }
    %>

</head>
<body>

    <div id="container">
        <div id="tekst">
            Kod resetujący hasło został wysłany na twojego maila. Wprowadź go poniżej.
        </div>
        <form action="VerifyCode" method="post">
            <input type="password" name="code" placeholder="Kod" onfocus="this.placeholder=''" onblur="this.placeholder='Kod'">
            <%
                if(session.getAttribute("codeError")!=null){
                    out.print("<div class='error'>"+session.getAttribute("codeError")+"</div>");
                }
            %>
            <input type="submit" value="Potwierdź kod">
            <% session.invalidate(); %>
        </form>
    </div>

</body>
</html>
