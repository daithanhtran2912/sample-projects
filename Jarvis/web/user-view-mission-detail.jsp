<%-- 
    Document   : user-view-mission-detail
    Created on : Jul 10, 2018, 1:29:32 AM
    Author     : T.Z.B
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/view-mission-detail.css"/>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                        <input type="text" name="txtMissionName" value="${requestScope.MISSION_INFO.missionName}" readonly="true"/>
                        <br/>
                        Date: <br/>
                        <input type="text" name="txtDate" value="${requestScope.MISSION_INFO.date}" readonly="true"/>
                        <br/>
                        Locate: <br/>
                        <input type="text" name="txtLocate" value="${requestScope.MISSION_INFO.locate}" readonly="true"/>
                        <br/>
                        Description: <br/>
                        <input type="text" name="txtDescription" value="${requestScope.MISSION_INFO.description}" readonly="true"/>
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
                                    <input type="checkbox" name="txtMember" value="${username}" checked disabled="true"/>${username}
                                </div> 
                            </thanh:if>
                            <thanh:if test="${check == 0}">
                                <div class="check-box">
                                    <input type="checkbox" name="txtMember" value="${username}" disabled="true"/>${username}
                                </div> 
                            </thanh:if>
                        </thanh:forEach>
                    </div>
                    <thanh:if test="${requestScope.MISSION_STATUS != 'true'}">
                        <input type="hidden" name="txtUsername" value="${sessionScope.USER}"/>
                        <input type="hidden" name="key" value="LoadListMission"/>
                        <input type="submit" name="action" value="Submit Mission"/>
                    </thanh:if>
                </div>
            </div>
        </form>
    </body>
</html>
