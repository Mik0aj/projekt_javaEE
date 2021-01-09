<%@ page import="com.database.Message" %>
<%@ page import="com.database.User" %>
<%@ page import="com.database.ChatUsers" %><%--
  Created by IntelliJ IDEA.
  User: paswi
  Date: 03.11.2020
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
    <%@ include file="../mainHTML/head.html" %>
<body>

<div class="wrapper">
    <!-- sidebar -->
    <%@ include file="../mainHTML/sidebar.jsp" %>

    <!-- content -->
    <div id="content">
        <!-- navbar -->
        <%@ include file="../mainHTML/navbar.jsp" %>

        <!-- wiadomosci -->

        <%
            if (session.getAttribute("groupID") != null){
                out.print("<div class=\"row\">");
                out.print("<form method='GET' action=\"MyProfileInGroup\">");
                out.print("<button type=\"submit\" href=\"/MyProfileInGroup\" class=\"btn btn-success\">Ustawienia</button>\n");
                out.print("</form>");
                out.print("</div>");

                ArrayList<Message> messages =(ArrayList<Message>)session.getAttribute("messages");

                for(Message message : messages){
                    if (session.getAttribute("userId").toString().equals(message.getUser_id())) {
                        // jeśli to moja wiadomość

                        out.print("<div class=\"container\" style=\"border: 2px solid #dedede; border-radius: 5px; padding: 40px; margin-right: auto; margin-top: 10px;\">");
                        //out.print("<h2 style=\"float: left;\">"+User.getLoginByID(message.getUser_id())+"</h2>");
                        out.print("<h2 style=\"float: left;\">Ja</h2>");
                        out.print("<div style=\"float: right; position: relative; top:-22px;\">");
                        out.print("<p>"+message.getContent()+"</p>");
                        out.print("<p style=\"font-size: 12px;\">"+message.getSend_time()+"</p>");
                        out.print("</div>");
                        out.print("</div>");

                        //out.print("<h2>Ja</h2>");

                    } else {
                        //out.print("<div class=\"container\" style=\"border: 2px solid #dedede; border-radius: 5px; padding: 10px;\">");
                        out.print("<div class=\"container\" style=\"border: 2px solid #dedede; border-radius: 5px; padding: 40px; margin-right: auto; margin-top: 10px;\">");
                        out.print("<h2 style=\"float: right;\">"+ ChatUsers.getNick(message.getUser_id(), message.getChat_id())+"</h2>");
                        out.print("<div style=\"float: left; position: relative; top:-22px;\">");
                        out.print("<p>"+message.getContent()+"</p>");
                        out.print("<p style=\"font-size: 12px;\">"+message.getSend_time()+"</p>");
                        out.print("</div>");
                        out.print("</div>");

                        //out.print("<h2>User ktoś</h2>");
                    }


                    //out.print("<li><a href='"+pageContext.getServletContext().getContextPath()+"/home?groupID="+chat.getChatID()+"'>"+chat.getChatName()+"</a></li>");

                }

                out.print("<form action=\"home\" method=\"post\">");
                out.print("<input type=\"text\" name=\"message\" placeholder=\"message\" onfocus=\"this.placeholder=''\" onblur=\"this.placeholder='message'\">");
                out.print("<input type=\"submit\" value=\"Wyślij\">");
                out.print("</form>");



                /*
                out.print("<div class=\"container\" style=\"border: 2px solid #dedede; border-radius: 5px; padding: 40px; margin-right: auto; margin-top: 10px;\">");                        out.print("<h2 style=\"float: right;\">"+User.getLoginByID(message.getUser_id())+"</h2>");
                out.print("<div style=\"float: left; position: relative; top:-22px;\">");
                out.print("<input type=\"text\" id=\"message\" name=\"message\"><br><br>");
                out.print("<input type=\"submit\" formmethod=\"post\" value=\"Wyślij\">");
                out.print("</div>");
                out.print("</div>");
                */

                /*
                <form method="post" action="${pageContext.request.contextPath}/MyGroups">
                <input id="GroupCode" name="groupCode" class="download" type="text" style="display: none; margin-bottom: 10pt"/>
                <button type="submit" class="btn btn-danger" style="display: none; margin-bottom: 10pt">Dodaj</button>
                </form>
                */

                /*
                out.print("<div class=\"footer\">");
                out.print("<div class=\"container\">");
                out.print("<h2 class=\"text-muted\">Place sticky footer content here.</h2>");
                out.print("</div>");
                out.print("</div>");
                */

            }
        %>



        <!--
        <h2>User ktoś</h2>
        <p>Wiadomość1</p>
        <h2>User ktoś2</h2>
        <p>Wiadomość2</p>
        -->

    </div>

</div>



<%@ include file="../mainHTML/links.html" %> <%--  to zawsze na koniec   --%>
</body>
</html>
