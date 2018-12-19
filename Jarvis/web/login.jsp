<%-- 
    Document   : login
    Created on : Jun 24, 2018, 3:54:41 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#login").validate({
                    rules: {
                        txtUsername: {
                            required: true
                        },
                        txtPassword: {
                            required: true
                        }
                    }
                })
            });
        </script>
        <style>
            #login label.error{color: red; font-style: italic; font-size: 20px; margin-left: 30px; margin-top: -20px; }
        </style>
    </head>
    <body>
        <form action="MainController" method="POST" id="login">
            <div class="login-text">USER LOGIN</div>
            <div class="login-form">
                <label for="lb-username">Username:</label><br/>
                <input type="text" name="txtUsername" value="" placeholder="Enter Username..." class="login-content" id="lb-username"/><br/>

                <label for="lb-password">Password:</label><br/>
                <input type="password" name="txtPassword" value="" placeholder="Enter Password..." class="login-content" id="lb-password"/><br/>

                <input type="submit" name="action" value="Login"/>
            </div>
        </form>
    </body>
</html>
