<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="pl">

<head>

    <meta charset="utf-8"/>
    <title>Logowanie</title>
    <meta name="description" content="Formularz logowania do komunikatora"/>
    <meta http-equiv="X-Ua-Compatible" content="IE=edge,chrome=1">

    <link rel="stylesheet" href="loginPage/style.css" type="text/css"/>

</head>

<body>

<main>
    <div id="container">
        <form action="Login" method="post">
            <input type="text" name="login" placeholder="Login" onfocus="this.placeholder=''" onblur="this.placeholder='Login'">
            <input type="password" name="password" placeholder="Hasło" onfocus="this.placeholder=''" onblur="this.placeholder='Hasło'">
            <p id="zapomniane"><a href="#" id="przypomnienie">Nie pamiętasz hasła?</a></p>
            <input type="submit" value="Zaloguj się">
            <p id="brakKonta">Nie masz konta? <a href="#" id="rejestracja">Zarejestruj się.</a></p>
        </form>
    </div>
</main>

</body>

</html>