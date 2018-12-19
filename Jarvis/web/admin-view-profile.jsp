<%-- 
    Document   : admin-view-profile
    Created on : Jul 5, 2018, 1:05:02 PM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/admin-view-profile.css"/>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript">
            $(function () {
                $("#update-profile").validate({
                    rules: {
                        txtPassword: {
                            rangelength: [6, 20]
                        },
                        txtFirstname: {
                            required: true
                        },
                        txtLastname: {
                            required: true
                        },
                        txtEmail: {
                            email: true
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
        <form action="MainController" method="POST" id="update-profile">
            <h2>My Profile</h2>
            <div class="profile-detail-container">
                <div class="profile-image">
                    <img src="${sessionScope.ADMIN_INFO.imageURL}"/>
                    <input type="text" name="txtImageURL" value="" id="img-url" placeholder="${sessionScope.ADMIN_INFO.imageURL}"/>
                </div>
                <div class="profile-detail">
                    Username: <br/>
                    <input type="text" name="txtUsername" value="${sessionScope.ADMIN_INFO.username}" readonly="true"/>
                    <br/>
                    Password: <br/>
                    <input type="password" name="txtPassword" value=""/>
                    <br/>
                    Firstname: <br/>
                    <input type="text" name="txtFirstname" value="${sessionScope.ADMIN_INFO.firstname}"/>
                    <br/>
                    Lastname: <br/>
                    <input type="text" name="txtLastname" value="${sessionScope.ADMIN_INFO.lastname}"/>
                    <br/>
                    Email: <br/>
                    <input type="text" name="txtEmail" value="${sessionScope.ADMIN_INFO.email}"/>
                    <br/>
                    Role: <br/>
                    <div class="radioBtn">
                        <thanh:if test="${sessionScope.ADMIN_INFO.role == 'user'}">
                            <input type="radio" name="txtRole" value="user" checked id="cb-box-user" />User
                        </thanh:if>
                        <thanh:if test="${sessionScope.ADMIN_INFO.role == 'admin'}">
                            <input type="radio" name="txtRole" value="admin" checked id="cb-box-admin" />Admin
                        </thanh:if>
                        <br/>
                    </div>
                    <br/>
                    <input type="hidden" name="key" value="ViewAdminProfile"/>
                    <input type="submit" name="action" value="Update Profile"/>
                </div>
            </div>
        </form>
    </body>
</html>
