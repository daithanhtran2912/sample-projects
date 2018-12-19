<%-- 
    Document   : view-member-detail
    Created on : Jul 2, 2018, 4:59:33 PM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Member Detail</title>
        <link rel="stylesheet" href="css/view-member-detail.css"/>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript">
            $(function () {
                $("#update-member").validate({
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
        <form action="MainController" method="POST" id="update-member">
            <h2>Member Detail</h2>
            <div class="member-detail-container">
                <div class="member-image">
                    <img src="${requestScope.MEMBER_DETAIL.imageURL}"/>
                    <input type="text" name="txtImageURL" value="" id="img-url" placeholder="${requestScope.MEMBER_DETAIL.imageURL}"/>
                </div>
                <div class="member-detail">
                    Username: <br/>
                    <input type="text" name="txtUsername" value="${requestScope.MEMBER_DETAIL.username}" readonly="true"/>
                    <br/>
                    Password: <br/>
                    <input type="password" name="txtPassword" value=""/>
                    <br/>
                    Firstname: <br/>
                    <input type="text" name="txtFirstname" value="${requestScope.MEMBER_DETAIL.firstname}"/>
                    <br/>
                    Lastname: <br/>
                    <input type="text" name="txtLastname" value="${requestScope.MEMBER_DETAIL.lastname}"/>
                    <br/>
                    Email: <br/>
                    <input type="text" name="txtEmail" value="${requestScope.MEMBER_DETAIL.email}"/>
                    <br/>
                    Role: <br/>
                    <div class="radioBtn">
                        <thanh:if test="${requestScope.MEMBER_DETAIL.role == 'user'}">
                            <input type="radio" name="txtRole" value="user" checked id="cb-box-user"/>User
                        </thanh:if>
                        <thanh:if test="${requestScope.MEMBER_DETAIL.role != 'user'}">
                            <input type="radio" name="txtRole" value="user" id="cb-box-user"/>User
                        </thanh:if>
                        <thanh:if test="${requestScope.MEMBER_DETAIL.role == 'admin'}">
                            <input type="radio" name="txtRole" value="admin" checked id="cb-box-admin"/>Admin
                        </thanh:if>

                        <thanh:if test="${requestScope.MEMBER_DETAIL.role != 'admin'}">
                            <input type="radio" name="txtRole" value="admin" id="cb-box-admin"/>Admin
                        </thanh:if>
                        <br/>
                    </div>
                    <br/>
                    <input type="hidden" name="key" value="ViewMemberDetail"/>
                    <input type="submit" name="action" value="Update Member's Profile"/>
                </div>
            </div>
        </form>
    </body>
</html>
