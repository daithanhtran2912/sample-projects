<%-- 
    Document   : user-show-list-finished-mission
    Created on : Jul 13, 2018, 11:30:45 PM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/user-show-list-mission.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1><font color="blue">Mission List</font></h1>
        <div class="search-form">
            <form action="MainController" method="POST">
                Mission Name: <input type="text" name="txtSearch" value=""/>
                <input type="hidden" name="key" value="LoadFinishedMission"/>
                <input type="hidden" name="txtUsername" value="${sessionScope.USER}"/>
                <input type="submit" name="action" value="Find Mission"/>
            </form>
        </div>
        <thanh:set var="result" value="${requestScope.LIST_FINISHED_MISSION}"/>
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
                    </tr>
                </thead>
                <tbody>
                    <thanh:forEach items="${requestScope.LIST_FINISHED_MISSION}" var="mission" varStatus="counter">
                        <tr>
                            <td><center>${counter.count}</center></td>
                <td>${mission.missionId}</td>
                <td>${mission.missionName}</td>
                <td>${mission.locate}</td>
                <thanh:if test="${mission.status == 'true'}">
                    <td><center><font color="#a2ef44">Finished</font></center></td>
                    </thanh:if>
                    <thanh:if test="${mission.status == 'false'}">
                <td><center><font color="red">Not Yet</font></center></td>
                </thanh:if>
        <td>
            <thanh:url var="missionDetailLink" value="MainController">
                <thanh:param name="action" value="LoadMissionData"/>
                <thanh:param name="key" value="UserLoadMissionDetail"/>
                <thanh:param name="txtMissionStatus" value="${mission.status}"/>
                <thanh:param name="txtMissionId" value="${mission.missionId}"/>
            </thanh:url>
        <center><a href="${missionDetailLink}">View</a></center>
    </td>
</tr>
</thanh:forEach>
</tbody>
</table>

</thanh:if>
<thanh:if test="${empty result}">
    <br/>
    <h1><font color="red"> No mission found!</font></h1>
    </thanh:if>
</body>
</html>
