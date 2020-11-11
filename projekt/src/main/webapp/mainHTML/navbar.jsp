<!--

Pasek menu

-->

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">

        <button type="button" id="sidebarCollapse" class="btn btn-info">
            <i class="fas fa-align-left"></i>
            <span>Lewy Sidebar</span>
        </button>
        <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fas fa-align-justify"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="nav navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">Page</a>
                </li>
                <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/">Grupy</a> <!-- Wyświetla grupy do których przynależy użytkownik -->
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/MyGroups">Moje grupy</a> <!-- Wyświetla grupy których użytkownik jest właścicielem-->
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/MyAccount">Moje konto</a>
                </li>
            </ul>
        </div>
    </div>
</nav>



<!--

Stary pasek

-->

<!--

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/projekt_war/">Discord2</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            # taki komentarz
            #<li class="nav-item active">
            #    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            #</li>

            <li class="nav-item">
                <a class="nav-link" href="/projekt_war/Login">Login</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#">Disabled</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>
</nav>

-->