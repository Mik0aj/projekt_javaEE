<%--
  Created by IntelliJ IDEA.
  User: paswi
  Date: 08.11.2020
  Time: 16:20
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
                List<Chat> ownChatList =(ArrayList<Chat>)session.getAttribute("ownChats");

                for(Chat chat : ownChatList){
                    out.print("<h2><a href='#'>"+chat.getChatName()+"</a> Kod czatu: "+chat.getEnterCode()+"<h2>");
                }
                if(session.getAttribute("alreadyVisitedMyGroups")==null){
                    session.setAttribute("alreadyVisitedMyGroups", "yes");
                }
            %>

        </div>

    </div>
<%@ include file="../mainHTML/links.html" %> <%--  to zawsze na koniec   --%>
</body>
</html>
