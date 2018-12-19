<%-- 
    Document   : admin-show-all-member
    Created on : Jun 26, 2018, 10:44:25 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>J.A.R.V.I.S</title>
        <link rel="stylesheet" href="css/admin-show-all-member.css"/>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script src="js/invalid-index.js"></script>
    </head>
    <body>
        <!--status attribute for delete information (success or failed)-->
        <thanh:if test="${not empty requestScope.STATUS}">
            <div class="invalid" id="invalid-box">
                ${requestScope.STATUS}
                <a onclick="closeInvalidBox()" class="closebtn">&times;</a>
            </div>
        </thanh:if>
        <h1><font color="blue">Member List</font></h1>
        <div class="search-form">
            <form action="MainController" method="POST">
                Fullname: <input type="text" name="txtSearch" value=""/>
                <input type="hidden" name="key" value="LoadAllMember"/>
                <input type="submit" name="action" value="Find Member"/>
            </form>
        </div>
        <thanh:set var="result" value="${requestScope.ALLMEMBER}"/>
            <thanh:if test="${not empty result}" var="checkData">
                <table border="1" id="myTable" class="tablesorter">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>View</th>
                            <th>Delete</th>
                        </tr>
                    </thead>

                    <tbody>
                        <thanh:forEach items="${requestScope.ALLMEMBER}" var="member" varStatus="counter">
                            <thanh:if test="${sessionScope.USER != member.username}">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${member.username}</td>
                                    <td>${member.firstname}</td>
                                    <td>${member.lastname}</td>
                                    <td>${member.email}</td>
                                    <td>${member.role}</td>
                                    <td>
                                        <thanh:url var="viewLink" value="MainController">
                                            <thanh:param name="action" value="LoadData"/>
                                            <thanh:param name="txtUsername" value="${member.username}"/>
                                            <thanh:param name="key" value="ViewMemberDetail"/>
                                        </thanh:url>
                                        <a href="${viewLink}">View</a>
                                    </td>
                                    <td>
                                        <thanh:url var="removeLink" value="MainController">
                                            <thanh:param name="action" value="Remove Member"/>
                                            <thanh:param name="txtUsername" value="${member.username}"/>
                                            <thanh:param name="key" value="LoadAllMember"/>
                                        </thanh:url>
                                        <a href="${removeLink}">Delete</a>
                                    </td>
                                </tr>
                            </thanh:if>
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
