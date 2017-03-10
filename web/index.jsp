<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>WebChat - Авторизация</title>        
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body onload="datetime()">
        <div class="clock"> 
                <form name="form_clock">
                    <p><input id="inputdate" name="date" type="text" name="date" value="" size="12" disabled="true"></p>
                    <p><input id="inputtime" name="time" type="text" name="time" value="" size="12" disabled="true"></p>
                </form> 
        </div>
        <div class="page-wrapper">
            <center>
                <h1>Привествуем в WebChat!</h1><br><br><br>
                <h2>Авторизация</h2><br><br>
                <form name="userLogin" action="userLogin" method="POST">
                    <input placeholder="Ваш никнейм" type="text" name="user" value="" />
                    <br><br>
                    <input type="submit" value="Войти в чат-комнату" name="log in" /> 
                </form>
            </center>
        </div>
    </body>
</html>