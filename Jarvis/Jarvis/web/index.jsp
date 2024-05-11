<%-- 
    Document   : index
    Created on : Jun 24, 2018, 3:43:19 PM
    Author     : T.Z.B
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>J.A.R.V.I.S</title>
        <link rel="icon" type="image/png"  href="WEB-IMG/A_icon.png">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/index.js"></script>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script src="js/invalid-index.js"></script>
    </head>
    <body>
        <thanh:if test="${not empty requestScope.INVALID}">
            <div class="invalid" id="invalid-box">
                ${requestScope.INVALID}
                <a onclick="closeInvalidBox()" class="closebtn">&times;</a>
            </div>
        </thanh:if>
        <%@include file="index-sub-content.jsp" %>
        <%@include file="index-main-content.jsp" %>
    </body>
</html>
