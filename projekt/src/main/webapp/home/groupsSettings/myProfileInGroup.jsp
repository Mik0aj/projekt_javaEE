<%@ page import="com.database.Chats" %>
<%@ page import="com.database.ChatUsers" %><%--
  Created by IntelliJ IDEA.
  User: paswi
  Date: 09.01.2021
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<%@ include file="../../mainHTML/head.html" %>
<body>

<div class="wrapper">
    <!-- sidebar -->
    <!-- tu nie potrzebny -->

    <!-- content -->
    <div id="content">
        <!-- navbar -->
        <%@ include file="../../mainHTML/navbar.jsp" %>

        <!-- wiadomosci -->

        <h2>Ustawienia</h2>
        <h2>Grupa: ${sessionScope.groupID}
            <%
                out.print(Chats.getName((String) session.getAttribute("groupID")));
            %>
        </h2>

        <div class="content">
            <div class="row">

                <form action="MyProfileInGroup" method="post">
                    <div class="col-sm-4">
                        <div class="row-xl-6">
                            <input type="text" name="nick" placeholder="nick" onfocus="this.placeholder=''" onblur="this.placeholder='Login'">
                        </div>
                        <div class="row-xl-6">
                            <button type="submit" class="btn btn-primary">Zatwierdź zmiany</button>
                        </div>
                    </div>
                </form>

                <%
                    if (ChatUsers.isOwner((String) session.getAttribute("userId"), (String) session.getAttribute("groupID"))){
                        out.print("<form action=\"MyProfileInGroup\" method=\"post\">");
                        out.print("<div class=\"col-sm-4\">");
                        out.print("<div class=\"row-xl-4\">");
                        out.print("<h2 class=\"row-12\">Dodaj użytkownika do grupy.</h2>");
                        out.print("</div>");
                        out.print("<div class=\"row-xl-4\">");
                        out.print("<input type=\"text\" name=\"newUserID\" placeholder=\"newUserID\" onfocus=\"this.placeholder=''\" onblur=\"this.placeholder='Login'\">");
                        out.print("</div>");
                        out.print("<div class=\"row-xl-4\">");
                        out.print("<button type=\"submit\" class=\"btn btn-primary\">Dodaj</button>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</form>");

                    }
                %>




                <form action="MyProfileInGroup" method="post">
                    <div class="col-sm-4">>
                        <div class="row-xl-6">
                            <h2 class="row-12">Jeśli jesteś właścicielim grupy opuszczając ją usuwasz grupę.</h2>
                            <h2 class="row-12">Jeśli jesteś zwykłym użytkownikiem grupy twoje wiadomości zostają po opuszczeniu grupy</h2>
                        </div>
                        <div class="row-xl-6">
                            <button type="submit" name="leaveGroup" value="leaveGroup" class="btn btn-danger">Opuść grupę</button>
                        </div>
                    </div>
                </form>

            </div>
        </div>



    </div>

</div>



<%@ include file="../../mainHTML/links.html" %> <%--  to zawsze na koniec   --%>
</body>
</html>
