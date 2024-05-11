<%-- 
    Document   : add-new-mission
    Created on : Jul 4, 2018, 10:50:02 PM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <link rel="stylesheet" href="css/add-new-mission.css"/>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script>
            $(function () {
                $("#add-mission").validate({
                    rules: {
                        txtMissionId: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtMissionName: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtDate: {
                            required: true,
                            date: true
                        },
                        txtLocate: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtDescription: {
                            rangelength: [1, 520]
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
        <h2>Add New Mission</h2>
        <form action="MainController" method="POST" id="add-mission">
            <div class="mission-detail-container">
                <div class="mission-detail">
                    <div class="left-detail">
                        Mission ID: <br/>
                        <input type="text" name="txtMissionId" value="" />
                        <font color="red" size="4"><i>${requestScope.DUPLICATE_CODE}</i></font>
                        <br/>
                        Mission Name: <br/>
                        <input type="text" name="txtMissionName" value=""/>
                        <br/>
                        Date: <br/>
                        <input type="text" name="txtDate" value="" placeholder="Ex: 2018-29-12 (yyyy-MM-dd)"/>                        
                        <font color="red" size="4"><i>${requestScope.INVALID_DATE}</i></font>
                        <br/>
                        Locate: <br/>
                        <input type="text" name="txtLocate" value=""/>
                        <br/>
                        Description: <br/>
                        <input type="text" name="txtDescription" value=""/>
                        <br/>
                    </div>
                    Members: <br/>
                    <div class="right-detail">
                        <thanh:forEach items="${sessionScope.ALL_MEMBER}" var="username">
                            <div class="check-box">
                                <input type="checkbox" name="txtUsername" value="${username}"/>${username}
                            </div>
                        </thanh:forEach>
                    </div>
                    <input type="hidden" name="key" value="Add New Mission"/>
                    <input type="submit" name="action" value="Create New Mission"/>
                </div>
            </div>
        </form>
    </body>
</html>
