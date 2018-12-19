<%-- 
    Document   : user-show-list-armor
    Created on : Jul 9, 2018, 10:37:07 PM
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
                <input type="hidden" name="key" value="UserArmorList"/>
                <input type="submit" name="action" value="Find Armor"/>
            </form>
        </div>
        <thanh:set var="result" value="${requestScope.LIST_AVAIL_ARMOR}"/>
        <thanh:if test="${not empty result}">
            <table border="1" class="table-armor">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Suit Code</th>
                        <th>Suit Name</th>
                        <th>Material</th>
                        <th>Available</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody>
                    <thanh:forEach items="${requestScope.LIST_AVAIL_ARMOR}" var="suit" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${suit.suitCode}</td>
                            <td>${suit.suitName}</td>
                            <td>${suit.material}</td>
                            <thanh:if test="${suit.available == 'True'}">
                                <td>
                        <center>
                            <font color="#a2ef44">
                            Online
                            </font>
                        </center>
                    </td>
                    <td>
                        <thanh:url var="viewArmorDetailLink" value="MainController">
                            <thanh:param name="action" value="LoadArmorData"/>
                            <thanh:param name="key" value="UserArmorDetail"/>
                            <thanh:param name="txtSuitCode" value="${suit.suitCode}"/>
                        </thanh:url>
                        <a href="${viewArmorDetailLink}" style="color: #a2ef44;">
                            <center>View</center>
                        </a>
                    </td>
                </thanh:if>
                <thanh:if test="${suit.available == 'False'}">
                    <td>
                    <center>
                        <font color="red">Offline</font>
                    </center>
                </td>
                <td>
                    <a style="text-decoration: line-through; color: darkgrey;">
                        <center>View</center>
                    </a>
                </td>
            </thanh:if>
        </tr>
    </thanh:forEach>
</tbody>
</table>

</thanh:if>
<thanh:if test="${empty result}">
    <br/>
    <h1>
        <font color="red"> No record found!</font>
    </h1>
</thanh:if>
</body>
</html>
