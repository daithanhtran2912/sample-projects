<%-- 
    Document   : user
    Created on : Jul 9, 2018, 12:43:31 AM
    Author     : T.Z.B
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="thanh" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>J.A.R.V.I.S</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="icon" type="image/png"  href="WEB-IMG/A_icon.png">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/user.css"/>
        <script type="text/javascript" src="js/admin.js"></script>
    </head>
    <body>
        <div class="header">
            <div class="header-box">
                <img src="WEB-IMG/Jarvis-logo.png" alt="header-logo"/>
                <div class="log-out">
                    <thanh:url var="logOutLink" value="MainController">
                        <thanh:param name="action" value="Log Out"/>
                    </thanh:url>
                    <a href="${logOutLink}">Log Out</a>
                </div>
            </div>
        </div>


        <!--Begin left side-->
        <div class="body">	
            <div class="dashboard">
                <div class="user-info">			
                    <p id="user-name">${sessionScope.USER}</p><br/>
                    <p id="user-status"><i class="fa fa-circle text-success"></i>"Online"</p>
                    <a class="user-img"><img src="WEB-IMG/shield.png"/></a>
                </div>

                <ul>

                    <li class="dashboard-menu">
                        <img src="WEB-IMG/avengers-black.png" style="width: 40px;">
                        <a class="click-open" onClick="changePlus(this)" href="#">
                            Missions
                            <span class="plus">+</span>
                        </a>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="missionListLink" value="MainController">
                                    <thanh:param name="action" value="Load User Data"/>
                                    <thanh:param name="key" value="LoadListMission"/>
                                    <thanh:param name="txtUsername" value="${sessionScope.USER}"/>
                                </thanh:url>
                                <a href="${missionListLink}">On-going Missions</a>
                            </li>
                        </ul>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="finishedMissionLink" value="MainController">
                                    <thanh:param name="action" value="Load User Data"/>
                                    <thanh:param name="key" value="LoadFinishedMission"/>
                                    <thanh:param name="txtUsername" value="${sessionScope.USER}"/>
                                </thanh:url>
                                <a href="${finishedMissionLink}">Finished Missions</a>
                            </li>
                        </ul>
                    </li>
                    <thanh:if test="${sessionScope.USER == 'ironman'}">
                        <li class="dashboard-menu">
                            <img src="WEB-IMG/Iron Man-black.png" style="width: 40px;">
                            <a class="click-open" onClick="changePlus(this)" href="#">
                                Hall of Armors
                                <span class="plus">+</span>
                            </a>
                            <ul class="open-item">
                                <li>
                                    <thanh:url var="armorListLink" value="MainController">
                                        <thanh:param name="action" value="LoadArmorData"/>
                                        <thanh:param name="key" value="UserArmorList"/>
                                    </thanh:url>
                                    <a href="${armorListLink}">Armor List</a>
                                </li>
                            </ul>
                        </li>
                    </thanh:if>


                    <li class="dashboard-menu">
                        <img src="WEB-IMG/hawkeye-bow-and-arrow.png" style="width: 40px;">
                        <a class="click-open" onClick="changePlus(this)" href="#">
                            My Profile
                            <span class="plus">+</span>
                        </a>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="viewProfileLink" value="MainController">
                                    <thanh:param name="action" value="ViewProfile"/>
                                    <thanh:param name="key" value="viewUserProfile"/>
                                    <thanh:param name="txtUsername" value="${sessionScope.USER}"/>
                                </thanh:url>
                                <a href="${viewProfileLink}">View Profile</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!--End left side-->
            <div class="right-frame">
                <thanh:if test="${param.key == 'viewUserProfile'}" >
                    <%@include file="user-view-profile.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'LoadListMission'}" >
                    <%@include file="user-show-list-mission.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'UserArmorList'}" >
                    <%@include file="user-show-list-armor.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'UserArmorDetail'}" >
                    <%@include file="user-view-suit-detail.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'UserLoadMissionDetail'}" >
                    <%@include file="user-view-mission-detail.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'LoadFinishedMission'}" >
                    <%@include file="user-show-list-finished-mission.jsp" %>
                </thanh:if>
            </div>

        </div>
    </body>
</html>
