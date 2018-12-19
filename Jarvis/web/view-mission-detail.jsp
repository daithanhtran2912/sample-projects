<%-- 
    Document   : view-mission-detail
    Created on : Jul 7, 2018, 12:54:48 AM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/view-mission-detail.css"/>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script>
            $(function () {
                $("#update-mission").validate({
                    rules: {
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
        <form action="MainController" method="POST" id="update-mission">
            <h2>Misison Detail</h2>
            <div class="mission-detail-container">
                <div class="mission-detail">
                    <div class="left-detail">
                        Mission ID: <br/>
                        <input type="text" name="txtMissionId" value="${requestScope.MISSION_INFO.missionId}" readonly="true"/>
                        <br/>
                        Mission Name: <br/>
                        <input type="text" name="txtMissionName" value="${requestScope.MISSION_INFO.missionName}"/>
                        <br/>
                        Date: <br/>
                        <input type="text" name="txtDate" value="${requestScope.MISSION_INFO.date}"/>
                        <font color="red" size="4"><i>${requestScope.INVALID_DATE}</i></font>
                        <br/>
                        Locate: <br/>
                        <input type="text" name="txtLocate" value="${requestScope.MISSION_INFO.locate}"/>
                        <br/>
                        Description: <br/>
                        <input type="text" name="txtDescription" value="${requestScope.MISSION_INFO.description}"/>
                        <br/>
                        Status: <br/>
                        <div class="radioBtn">
                            <thanh:if test="${requestScope.MISSION_INFO.status == 'true'}">
                                <input type="radio" name="txtStatus" value="finished" checked "/>Finished
                                </thanh:if>
                                <thanh:if test="${requestScope.MISSION_INFO.status != 'true'}">
                                    <input type="radio" name="txtStatus" value="finished" "/>Finished
                            </thanh:if>
                            <thanh:if test="${requestScope.MISSION_INFO.status == 'false'}">
                                <input type="radio" name="txtStatus" value="not yet" checked "/>Not yet
                                </thanh:if>
                                <thanh:if test="${requestScope.MISSION_INFO.status != 'false'}">
                                    <input type="radio" name="txtStatus" value="not yet" "/>Not yet
                            </thanh:if>
                            <br/>
                        </div>
                        <br/>
                    </div>
                    Members: <br/>
                    <div class="right-detail">
                        <thanh:forEach items="${sessionScope.ALL_MEMBER}" var="username">
                            <thanh:set var="check" value="0"/>
                            <thanh:forEach items="${requestScope.LIST_MEMBER}" var="member">
                                <thanh:if test="${member == username}">
                                    <thanh:set var="check" value="1"/>
                                </thanh:if>                                
                            </thanh:forEach>
                            <thanh:if test="${check == 1}">
                                <div class="check-box">
                                    <input type="checkbox" name="txtMember" value="${username}" checked/>${username}
                                </div> 
                            </thanh:if>
                            <thanh:if test="${check == 0}">
                                <div class="check-box">
                                    <input type="checkbox" name="txtMember" value="${username}"/>${username}
                                </div> 
                            </thanh:if>
                        </thanh:forEach>
                    </div>
                    <input type="hidden" name="key" value="ViewMissionDetail"/>
                    <input type="submit" name="action" value="Update Mission"/>
                </div>
            </div>
        </form>
    </body>
</html>
