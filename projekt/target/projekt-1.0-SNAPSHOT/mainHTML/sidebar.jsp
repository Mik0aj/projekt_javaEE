<%@ page import="java.util.List" %>
<%@ page import="com.strona.chat.Chat" %>
<%@ page import="java.util.ArrayList" %>

<nav id="sidebar">
    <div class="sidebar-header">
        <h3>Discord2</h3>
    </div>

    <ul class="list-unstyled components">
        <%
            List<Chat> chatList =(ArrayList<Chat>)session.getAttribute("chats");

            for(Chat chat : chatList){
                out.print("<span method='GET'>");
                out.print("<li><a href='"+pageContext.getServletContext().getContextPath()+"/home?groupID="+chat.getChatID()+"'>"+chat.getChatName()+"</a></li>");
                out.print("</span>");
            }
        %>
    </ul>

    <ul class="list-unstyled CTAs">

        <li>
            <a class="download" onclick="showTextInput('GroupCode')">Wpisz kod grupy</a>
            <form method="post" action="${pageContext.request.contextPath}/MyGroups">
                <input id="GroupCode" name="groupCode" class="download" type="text" style="display: none; margin-bottom: 10pt"/>
                <button type="submit" class="btn btn-danger" style="display: none; margin-bottom: 10pt">Dodaj</button>
            </form>
        </li>
        <li>
            <a class="article" href="createChat.jsp">Nowa grupa</a>
        </li>
    </ul>
</nav>

<script>
    function showTextInput(input) {
        var x = document.getElementById(input);
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    }
</script>



<!--  Starsza wersja (kopia z Bootstrapa)

<nav id="sidebar">
    <div class="sidebar-header">
        <h3>Bootstrap Sidebar</h3>
    </div>

    <ul class="list-unstyled components">
        <p>Dummy Heading</p>
        <li class="active">
            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Home</a>
            <ul class="collapse list-unstyled" id="homeSubmenu">
                <li>
                    <a href="#">Home 1</a>
                </li>
                <li>
                    <a href="#">Home 2</a>
                </li>
                <li>
                    <a href="#">Home 3</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">About</a>
        </li>
        <li>
            <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Pages</a>
            <ul class="collapse list-unstyled" id="pageSubmenu">
                <li>
                    <a href="#">Page 1</a>
                </li>
                <li>
                    <a href="#">Page 2</a>
                </li>
                <li>
                    <a href="#">Page 3</a>
                </li>
            </ul>
        </li>
        <li>
            <a href="#">Portfolio</a>
        </li>
        <li>
            <a href="#">Contact</a>
        </li>
    </ul>

    <ul class="list-unstyled CTAs">
        <li>
            <a href="https://bootstrapious.com/tutorial/files/sidebar.zip" class="download">Download source</a>
        </li>
        <li>
            <a href="https://bootstrapious.com/p/bootstrap-sidebar" class="article">Back to article</a>
        </li>
    </ul>
</nav>

-->