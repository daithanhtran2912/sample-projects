<%-- 
    Document   : view-armor-detail
    Created on : Jul 6, 2018, 2:42:34 PM
    Author     : T.Z.B
--%>

<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/view-armor-detail.css"/>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript">
            $(function () {
                $("#update-suit").validate({
                    rules: {
                        txtSuitName: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtMaterial: {
                            required: true,
                            rangelength: [1, 50]
                        },
                        txtWeapon: {
                            rangelength: [1, 520]
                        },
                        txtStatus: {
                            required: true
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

        <form action="MainController" method="POST" id="update-suit">
            <h2>Suit Detail</h2>
            <div class="suit-detail-container">
                <div class="suit-image">
                    <img src="${requestScope.ARMOR_INFO.imageURL}"/>
                    <input type="text" name="txtImageURL" value="" id="img-url" placeholder="${requestScope.ARMOR_INFO.imageURL}"/>
                </div>
                <div class="suit-detail">
                    Suit Code: <br/>
                    <input type="text" name="txtSuitCode" value="${requestScope.ARMOR_INFO.suitCode}" readonly="true"/>
                    <br/>
                    Suit Name: <br/>
                    <input type="text" name="txtSuitName" value="${requestScope.ARMOR_INFO.suitName}"/>
                    <br/>
                    Material: <br/>
                    <input type="text" name="txtMaterial" value="${requestScope.ARMOR_INFO.material}"/>
                    <br/>
                    Weapon: <br/>
                    <input type="text" name="txtWeapon" value="${requestScope.ARMOR_INFO.weapon}"/>
                    <br/>
                    Status: <br/>
                    <div class="radioBtn">
                        <thanh:if test="${requestScope.ARMOR_INFO.available == 'True'}">
                            <input type="radio" name="txtStatus" value="online" checked id="cb-box-user"/>Online
                        </thanh:if>
                        <thanh:if test="${requestScope.ARMOR_INFO.available != 'True'}">
                            <input type="radio" name="txtStatus" value="online" id="cb-box-user"/>Online
                        </thanh:if>
                        <thanh:if test="${requestScope.ARMOR_INFO.available == 'False'}">
                            <input type="radio" name="txtStatus" value="offline" checked id="cb-box-user"/>Offline
                        </thanh:if>
                        <thanh:if test="${requestScope.ARMOR_INFO.available != 'False'}">
                            <input type="radio" name="txtStatus" value="offline" id="cb-box-user"/>Offline
                        </thanh:if>
                        <br/>
                    </div>
                    <input type="hidden" name="key" value="ArmorDetail"/>
                    <input type="submit" name="action" value="Update Suit"/>
                </div>
            </div>
        </form>
    </body>
</html>
