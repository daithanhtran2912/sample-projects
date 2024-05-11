<%-- 
    Document   : user-view-suit-detail
    Created on : Jul 9, 2018, 11:29:04 PM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/view-armor-detail.css"/>
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

        <form action="MainController" method="POST" id="update-suit">
            <h2>Suit Detail</h2>
            <div class="suit-detail-container">
                <div class="suit-image">
                    <img src="${requestScope.SUIT_INFO.imageURL}"/>
                    <input type="text" name="txtImageURL" value="" id="img-url" placeholder="${requestScope.SUIT_INFO.imageURL}" readonly="true"/>
                </div>
                <div class="suit-detail">
                    Suit Code: <br/>
                    <input type="text" name="txtSuitCode" value="${requestScope.SUIT_INFO.suitCode}" readonly="true"/>
                    <br/>
                    Suit Name: <br/>
                    <input type="text" name="txtSuitName" value="${requestScope.SUIT_INFO.suitName}" readonly="true"/>
                    <br/>
                    Material: <br/>
                    <input type="text" name="txtMaterial" value="${requestScope.SUIT_INFO.material}" readonly="true"/>
                    <br/>
                    Weapon: <br/>
                    <input type="text" name="txtWeapon" value="${requestScope.SUIT_INFO.weapon}" readonly="true"/>
                    <br/>
                    Status: <br/>
                    <div class="radioBtn">
                        <thanh:if test="${requestScope.SUIT_STATUS == 'True'}">
                            <input type="submit" name="action" value="Return Suit"/>
                        </thanh:if>
                        <thanh:if test="${requestScope.SUIT_STATUS == 'False'}">
                            <input type="submit" name="action" value="Take Suit"/>
                        </thanh:if>
                        <br/>
                    </div>
                    <input type="hidden" name="key" value="UserArmorDetail"/>
                </div>
            </div>
        </form>
    </body>
</html>
