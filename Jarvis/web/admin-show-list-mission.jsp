<%-- 
    Document   : admin-show-list-mission
    Created on : Jul 4, 2018, 9:48:21 PM
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
        <script src="js/invalid-index.js"></script>
        <link rel="stylesheet" href="css/admin-show-list-mission.css"/>
    </head>
    <body>

        <thanh:if test="${not empty requestScope.STATUS}">
            <div class="invalid" id="invalid-box">
                ${requestScope.STATUS}
                <a onclick="closeInvalidBox()" class="closebtn">&times;</a>
            </div>
        </thanh:if>

        <h1><font color="blue">Mission List</font></h1>
        <div class="search-form">
            <form action="MainController" method="POST">
                Mission Name: <input type="text" name="txtSearch" value=""/>
                <input type="hidden" name="key" value="LoadMissionList"/>
                <input type="submit" name="action" value="Find Mission"/>
            </form>
        </div>
        <thanh:set var="result" value="${requestScope.LIST_MISSION}"/>
        <thanh:if test="${not empty result}">
            <table border="1" class="table-mission">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Mission ID</th>
                        <th>Mission Name</th>
                        <th>Locate</th>
                        <th>Status</th>
                        <th>Detail</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <thanh:forEach items="${requestScope.LIST_MISSION}" var="mission" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${mission.missionId}</td>
                            <td>${mission.missionName}</td>
                            <td>${mission.locate}</td>
                            <thanh:if test="${mission.status == 'true'}">
                                <td><font color="#a2ef44">Finished</font></td>
                            </thanh:if>
                            <thanh:if test="${mission.status == 'false'}">
                                <td><font color="red">Not Yet</font></td>
                            </thanh:if>
                            <td>
                                <thanh:url var="viewMissionLink" value="MainController">
                                    <thanh:param name="action" value="LoadMissionData"/>
                                    <thanh:param name="key" value="ViewMissionDetail"/>
                                    <thanh:param name="txtMissionId" value="${mission.missionId}"/>
                                </thanh:url>
                                <a href="${viewMissionLink}">View</a>
                            </td>
                            <td>
                                <thanh:url var="deleteMissionLink" value="MainController">
                                    <thanh:param name="action" value="Remove Mission"/>
                                    <thanh:param name="key" value="LoadMissionList"/>
                                    <thanh:param name="txtMissionId" value="${mission.missionId}"/>
                                </thanh:url>
                                <a href="${deleteMissionLink}">Remove</a>
                            </td>
                        </tr>
                    </thanh:forEach>
                </tbody>
            </table>

        </thanh:if>
        <thanh:if test="${empty result}">
            <br/>
            <h1><font color="red"> No record found!</font></h1>
            </thanh:if>
    </body>
</html>
