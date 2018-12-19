<%-- 
    Document   : admin
    Created on : Jun 27, 2018, 9:35:48 AM
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
        <link rel="stylesheet" href="css/admin.css">
        <script src="js/admin.js"></script>
    </head>
    <body>

        <div class="header">
            <div class="header-box">
                <img src="WEB-IMG/Jarvis-logo.png"/>
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
                <div class="admin-info">			
                    <p id="admin-name">${sessionScope.USER}</p><br/>
                    <!--J.A.R.V.I.S-->
                    <p id="admin-status"><i class="fa fa-circle text-success"></i>"Online"</p>
                    <a class="admin-img"><img src="WEB-IMG/triangle.png"/></a>
                </div>

                <ul>
                    <li class="dashboard-menu">
                        <img src="WEB-IMG/shield.png" style="width: 40px;">
                        <a class="click-open" onClick="changePlus(this)" href="#">
                            Avengers
                            <span class="plus">+</span>
                        </a>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="showAllMemberLink" value="MainController">
                                    <thanh:param name="action" value="LoadData"/>
                                    <thanh:param name="key" value="LoadAllMember"/>
                                </thanh:url>
                                <a href="${showAllMemberLink}">Team Members</a>
                            </li>
                            <li>
                                <thanh:url var="addNewMemberLink" value="MainController">
                                    <thanh:param name="action" value="LoadData"/>
                                    <thanh:param name="key" value="AddNewMember"/>
                                </thanh:url>
                                <a href="${addNewMemberLink}">Add New Member</a>
                            </li>
                        </ul>
                    </li>

                    <li class="dashboard-menu">
                        <img src="WEB-IMG/avengers.png" style="width: 40px;">
                        <a class="click-open" onClick="changePlus(this)" href="#">
                            Missions
                            <span class="plus">+</span>
                        </a>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="showMissionList" value="MainController">
                                    <thanh:param name="action" value="LoadMissionData"/>
                                    <thanh:param name="key" value="LoadMissionList"/>
                                </thanh:url>
                                <a href="${showMissionList}">Mission List</a>
                            </li>
                            <li>
                                <thanh:url var="addNewMission" value="MainController">
                                    <thanh:param name="action" value="LoadMissionData"/>
                                    <thanh:param name="key" value="Add New Mission"/>
                                </thanh:url>
                                <a href="${addNewMission}">Create New Mission</a>
                            </li>
                        </ul>
                    </li>

                    <li class="dashboard-menu">
                        <img src="WEB-IMG/Iron Man.png" style="width: 40px;">
                        <a class="click-open" onClick="changePlus(this)" href="#">
                            Mr.Stark's Armors
                            <span class="plus">+</span>
                        </a>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="showArmorList" value="MainController">
                                    <thanh:param name="action" value="LoadArmorData"/>
                                    <thanh:param name="key" value="LoadArmorList"/>
                                </thanh:url>
                                <a href="${showArmorList}">Armor List</a>
                            </li>
                            <li>
                                <thanh:url var="addNewArmorLink" value="MainController">
                                    <thanh:param name="action" value="LoadArmorData"/>
                                    <thanh:param name="key" value="AddNewArmor"/>
                                </thanh:url>
                                <a href="${addNewArmorLink}">Add New Armor</a>
                            </li>
                        </ul>
                    </li>

                    <li class="dashboard-menu">
                        <img src="WEB-IMG/iron_man_2_arc_reactor_pattern_by_sanjl-d5npmsm.png" style="width: 40px;">
                        <a class="click-open" onClick="changePlus(this)" href="#">
                            My Profile
                            <span class="plus">+</span>
                        </a>
                        <ul class="open-item">
                            <li>
                                <thanh:url var="viewProfileLink" value="MainController">
                                    <thanh:param name="action" value="ViewProfile"/>
                                    <thanh:param name="key" value="ViewAdminProfile"/>
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
                <thanh:if test="${param.key == 'LoadAllMember'}" >
                    <%@include file="admin-show-all-member.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'ViewMemberDetail'}">
                    <%@include file="view-member-detail.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'AddNewMember'}">
                    <%@include file="add-new-member.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'LoadMissionList'}">
                    <%@include file="admin-show-list-mission.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'Add New Mission'}">
                    <%@include file="add-new-mission.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'LoadArmorList'}">
                    <%@include file="admin-show-list-armor.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'AddNewArmor'}">
                    <%@include file="add-new-armor.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'ViewAdminProfile'}">
                    <%@include file="admin-view-profile.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'ArmorDetail'}">
                    <%@include file="view-armor-detail.jsp" %>
                </thanh:if>
                <thanh:if test="${param.key == 'ViewMissionDetail'}">
                    <%@include file="view-mission-detail.jsp" %>
                </thanh:if>
                
            </div>
        </div>
    </body>
</html>
