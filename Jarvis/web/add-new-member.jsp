<%-- 
    Document   : add-new-member
    Created on : Jul 3, 2018, 1:21:46 AM
    Author     : T.Z.B
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Member</title>
        <link rel="stylesheet" href="css/add-new-member.css"/>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript">
            $(function () {
                $("#add-member").validate({
                    rules: {
                        txtUsername: {
                            required: true,
                            rangelength: [6, 20]
                        },
                        txtPassword: {
                            required: true,
                            rangelength: [6, 20]
                        },
                        txtConfirmPassword: {
                            equalTo: "#txtPassword"
                        },
                        txtFirstname: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtLastname: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtEmail: {
                            email: true,
                            rangelength: [1, 100]
                        },
                        txtRole: {
                            required: true
                        }
                    }
                })
            });
        </script>
    </head>
    <body>
        <thanh:if test="${not empty requestScope.STATUS}">
            <div class="invalid" id="invalid-box">
                ${requestScope.STATUS}
                <a onclick="closeInvalidBox()" class="closebtn">&times;</a>
            </div>
        </thanh:if>
        <form action="MainController" method="POST" id="add-member">
            <h2>Add New Member</h2>
            <div class="member-detail-container">
                <div class="member-image">
                    <img src="image/avengers_trilogy_poster_by_tclarke597-dbv7gy2.png"/>
                    <input type="text" name="txtImageURL" value="" id="img-url" placeholder="Image URL..."/>
                </div>
                <div class="member-detail">
                    Username: <br/>
                    <input type="text" name="txtUsername" value="" />
                    <i><font color="red" size="4">${requestScope.DUPLICATE_CODE}</font></i><br/>
                    Password: <br/>
                    <input type="password" name="txtPassword" value="" id="txtPassword"/>
                    <br/>
                    Confirm Password: <br/>
                    <input type="password" name="txtConfirmPassword" value=""/>
                    <br/>
                    Firstname: <br/>
                    <input type="text" name="txtFirstname" value=""/>
                    <br/>
                    Lastname: <br/>
                    <input type="text" name="txtLastname" value=""/>
                    <br/>
                    Email: <br/>
                    <input type="text" name="txtEmail" value=""/>
                    <br/>
                    Role: <br/>
                    <div class="radioBtn">
                        <input type="radio" name="txtRole" value="user" checked id="cb-box-user"/>User
                        <input type="radio" name="txtRole" value="admin" id="cb-box-admin"/>Admin
                        <br/>
                    </div>
                    <input type="hidden" name="key" value="AddNewMember"/>
                    <input type="submit" name="action" value="Create New Member"/>
                </div>
            </div>
        </form>
    </body>
</html>
