<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="pl">
<head>
    <%@ include file="/mainHTML/head.html" %>

    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>

<div class="wrapper">
    <!-- sidebar -->

    <!-- content -->
    <div id="content">
        <!-- navbar -->
        <%@ include file="/mainHTML/navbar.jsp" %>

        <div class="wrapper" style="text-align: center; margin-left: auto; margin-right: auto">
            <div id="container" class="row" style="text-align: center; margin-left: auto; margin-right: auto">
                <form action="Registration" method="post" style="text-align: center">
                    <div class="col-12">
                        <input type="text" name="login" placeholder="Login" onfocus="this.placeholder=''" onblur="this.placeholder='Login'">
                        <%
                            if(session.getAttribute("loginError")!=null){
                                out.print("<div class='error'>"+session.getAttribute("loginError")+"</div>");
                            }
                        %>
                    </div>

                    <div class="col-12">
                        <input type="text" name="email" placeholder="E-mail" onfocus="this.placeholder=''" onblur="this.placeholder='E-mail'">
                        <%
                            if(session.getAttribute("emailError")!=null){
                                out.print("<div class='error'>"+session.getAttribute("emailError")+"</div>");
                            }
                        %>
                    </div>

                    <div class="col-12">
                        <input type="password" name="password" placeholder="Hasło"onfocus="this.placeholder=''" onblur="this.placeholder='Hasło'">
                        <%
                            if(session.getAttribute("passwordError1")!=null){
                                out.print("<div class='error'>"+session.getAttribute("passwordError1")+"</div>");
                            }
                        %>
                    </div>

                    <div class="col-12">
                        <input type="password" name="repeatPassword" placeholder="Powtórz hasło"onfocus="this.placeholder=''" onblur="this.placeholder='Powtórz hasło'">
                        <%
                            if(session.getAttribute("passwordError2")!=null){
                                out.print("<div class='error'>"+session.getAttribute("passwordError2")+"</div>");
                            }
                        %>
                    </div>

                    <div class="col-12">
                        <label id="policyLabel"><input type="checkbox" name="policy"> Akceptuję <a href="#" id="policyLink">regulamin</a></label>
                        <%
                            if(session.getAttribute("policyError")!=null){
                                out.print("<div class='error'>"+session.getAttribute("policyError")+"</div>");
                            }
                        %>
                    </div>

                    <div class="col-12">
                        <div class="g-recaptcha" data-sitekey="6LcjhcQZAAAAAIFZDfYLbhyW5xY_ByX9fxNdLPeX"></div>
                        <%
                            if(session.getAttribute("captchaError")!=null){
                                out.print("<div class='error'>"+session.getAttribute("captchaError")+"</div>");
                            }
                        %>
                    </div>

                    <div class="col-12">
                        <input type="submit" name="registration" value="Zarejestruj się">
                        <% session.invalidate(); %>
                    </div>

                </form>
            </div>
        </div>

    </div>

</div>



<%@ include file="/mainHTML/links.html" %> <%--  to zawsze na koniec   --%>
</body>

</html>
