<%-- 
    Document   : admin-show-list-armor
    Created on : Jul 5, 2018, 12:35:13 AM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/admin-show-list-armor.css"/>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script src="js/invalid-index.js"></script>
    </head>
    <body>

        <thanh:if test="${not empty requestScope.STATUS}">
            <div class="invalid" id="invalid-box">
                ${requestScope.STATUS}
                <a onclick="closeInvalidBox()" class="closebtn">&times;</a>
            </div>
        </thanh:if>


        <h1><font color="blue">Armor List</font></h1>
        <div class="search-form">
            <form action="MainController" method="POST">
                Armor Name: <input type="text" name="txtSearch" value=""/>
                <input type="hidden" name="key" value="LoadArmorList"/>
                <input type="submit" name="action" value="Find Armor"/>
            </form>
        </div>
        <thanh:set var="result" value="${requestScope.LIST_ARMOR}"/>
        <thanh:if test="${not empty result}">
            <table border="1" class="table-armor">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Suit Code</th>
                        <th>Suit Name</th>
                        <th>Available</th>
                        <th>Detail</th>
                        <th>Remove</th>

                    </tr>
                </thead>
                <tbody>
                    <thanh:forEach items="${requestScope.LIST_ARMOR}" var="suit" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${suit.suitCode}</td>
                            <td>${suit.suitName}</td>
                            <thanh:if test="${suit.available == 'True'}">
                                <td><font color="#a2ef44">Online</font></td>
                                </thanh:if>
                                <thanh:if test="${suit.available == 'False'}">
                                <td><font color="red">Offline</font></td>
                                </thanh:if>
                            <td>
                                <thanh:url var="viewArmorDetailLink" value="MainController">
                                    <thanh:param name="action" value="LoadArmorData"/>
                                    <thanh:param name="key" value="ArmorDetail"/>
                                    <thanh:param name="txtSuitCode" value="${suit.suitCode}"/>
                                </thanh:url>
                                <a href="${viewArmorDetailLink}">View</a>
                            </td>
                            <td>
                                <thanh:url var="removeArmorLink" value="MainController">
                                    <thanh:param name="action" value="Remove Armor"/>
                                    <thanh:param name="key" value="LoadArmorList"/>
                                    <thanh:param name="txtArmorCode" value="${suit.suitCode}"/>
                                </thanh:url>
                                <a href="${removeArmorLink}"><font color="#da0463">Remove</font></a>
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
