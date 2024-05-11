<%-- 
    Document   : add-new-armor
    Created on : Jul 5, 2018, 2:32:43 AM
    Author     : T.Z.B
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/add-new-armor.css"/>
        <link rel="stylesheet" href="css/invalid-index.css"/>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <script type="text/javascript" src="js/jquery.validate.js"></script>
        <script type="text/javascript" src="js/additional-methods.js"></script>
        <script type="text/javascript" src="js/invalid-index.js"></script>
        <script>
            $(function () {
                $("#add-suit").validate({
                    rules: {
                        txtSuitCode: {
                            required: true,
                            rangelength: [1, 10]
                        },
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
        <form action="MainController" method="POST" id="add-suit">
            <h2>Add New Suit</h2>
            <div class="suit-detail-container">
                <div class="suit-image">
                    <img src="image/Iron_Man_3_Concept_Art_by_Andy_Park_06.jpg"/>
                    <input type="text" name="txtImageURL" value="" id="img-url" placeholder="Image URL..."/>
                </div>
                <div class="suit-detail">
                    Suit Code: <br/>
                    <input type="text" name="txtSuitCode" value="" />
                    <font color="#6900ff">${requestScope.DUPLICATE_CODE}</font><br/>
                    Suit Name: <br/>
                    <input type="text" name="txtSuitName" value="" />
                    <br/>
                    Material: <br/>
                    <input type="text" name="txtMaterial" value=""/>
                    <br/>
                    Weapon: <br/>
                    <input type="text" name="txtWeapon" value=""/>
                    <br/>
                    <input type="hidden" name="key" value="AddNewArmor"/>
                    <input type="submit" name="action" value="Add New Armor"/>
                </div>
            </div>
        </form>
    </body>
</html>
