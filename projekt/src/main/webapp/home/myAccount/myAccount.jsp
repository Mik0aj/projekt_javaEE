<%--
  Created by IntelliJ IDEA.
  User: paswi
  Date: 08.11.2020
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <%@ include file="../../mainHTML/head.html" %>
<body>

<div class="wrapper">


    <!-- content -->
    <div id="content">
        <!-- navbar -->
        <%@ include file="../../mainHTML/navbar.jsp" %>

        <!-- informacje o profilu -->
        <h2>email</h2>
        <p>${sessionScope.userEmail}</p>

    </div>
</div>

</body>
</html>
