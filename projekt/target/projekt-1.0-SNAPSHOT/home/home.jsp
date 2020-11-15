<%--
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
        <h2>User ktoś</h2>
        <p>Wiadomość1</p>
        <h2>User ktoś2</h2>
        <p>Wiadomość2</p>

    </div>

</div>



<%@ include file="../mainHTML/links.html" %> <%--  to zawsze na koniec   --%>
</body>
</html>
