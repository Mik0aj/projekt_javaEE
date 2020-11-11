<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html lang="pl">
<%@ include file="/mainHTML/head.html" %>
<body>

<div class="wrapper">

    <!-- content -->
    <div id="content">
        <!-- navbar -->
        <%@ include file="/mainHTML/navbar.jsp" %>

        <!-- Login -->
        <div class="container row" style="margin: auto; text-align: center">
            <!-- class="sm" style="margin: auto; text-align: center" -->
            <div class="col-4"></div>
            <div class="col-4" id="containerLogin" style="margin: auto;">
                <form action="Login" method="post">
                    <div class="row">
                        <input type="text" name="login" placeholder="Login" onfocus="this.placeholder=''" onblur="this.placeholder='Login'">
                        <%
                            if(session.getAttribute("loginError")!=null){
                                out.print("<div class='error'>"+session.getAttribute("loginError")+"</div>");
                            }
                        %>
                    </div>

                    <div class="row">
                        <input type="password" name="password" placeholder="Hasło" onfocus="this.placeholder=''" onblur="this.placeholder='Hasło'">
                        <%
                            if(session.getAttribute("passwordError")!=null){
                                out.print("<div class='error'>"+session.getAttribute("passwordError")+"</div>");
                            }
                        %>
                    </div>

                    <div class="row">
                        <p id="zapomniane"><a href="insertEmailPage.jsp" id="przypomnienie">Nie pamiętasz hasła?</a></p>
                    </div>

                    <div class="row">
                        <input type="submit" value="Zaloguj się">
                    </div>

                    <div class="row">
                        <p id="brakKonta">Nie masz konta? <a href="registrationPage.jsp" id="rejestracja">Zarejestruj się.</a></p>
                        <% session.invalidate(); %>
                    </div>

                </form>
            </div>
            <div class="col-4"></div>


        </div>

    </div>

</div>



<%@ include file="/mainHTML/links.html" %> <%--  to zawsze na koniec   --%>
</body>
</html>














<!-- Bartka -->
<!--

<html lang="pl">

<head>

    <meta charset="utf-8"/>
    <title>Logowanie</title>
    <meta name="description" content="Formularz logowania do komunikatora"/>
    <meta http-equiv="X-Ua-Compatible" content="IE=edge,chrome=1">

    <link rel="stylesheet" href="loginPage/style.css" type="text/css"/>

</head>

<body>



</body>

</html>
-->