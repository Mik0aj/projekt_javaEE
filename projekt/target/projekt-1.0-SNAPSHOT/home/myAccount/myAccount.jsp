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


        <div class="row">
            <div class="col-sm-4">
                <h2>login</h2>
                <p>${sessionScope.login}</p>
            </div>
            <div class="col-sm-4">
                <h2>email</h2>
                <p>${sessionScope.userEmail}</p>
            </div>
            <div class="col-sm-4">
                <div>
                    <form method="post" action="${pageContext.request.contextPath}/MyAccount">
                        <button type="submit" name="deleteAccount" value="delete" class="btn btn-danger">Usuń konto</button>
                    </form>

                </div>
            </div>
        </div>
        <!-- informacje o profilu -->



    </div>
</div>

</body>
</html>
