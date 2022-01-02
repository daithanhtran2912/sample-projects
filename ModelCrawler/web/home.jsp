<%-- 
    Document   : home
    Created on : Oct 15, 2019, 4:15:01 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Model Crawler</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap.min.css" rel="stylesheet">

        <!-- Custom fonts for this template -->
        <link href="all.min.css" rel="stylesheet">
        <link href="simple-line-icons.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet"
              type="text/css">

        <!-- Custom styles for this template -->
        <link href="landing-page.min.css" rel="stylesheet">

    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-light bg-light static-top">
            <div class="container">
                <a class="navbar-brand" href="ProcessServlet">Model Crawler</a>
            </div>
        </nav>

        <!-- Masthead -->
        <header class="masthead text-white text-center">
            <div class="overlay"></div>
            <div class="container">
                <div class="row">
                    <div class="col-xl-9 mx-auto">
                        <h1 class="mb-5">"Scale model database"</h1>
                    </div>
                    <div class="col-md-10 col-lg-8 col-xl-7 mx-auto">
                        <form action="search.jsp" method="POST">
                            <div class="form-row">
                                <div class="col-12 col-md-9 mb-2 mb-md-0">
                                    <input type="text" class="form-control form-control-lg" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="Find your model..."/>
                                </div>
                                <div class="col-12 col-md-3">
                                    <input type="submit" class="btn-search btn-block btn-lg btn-primary" name="btnAction" value="Search" />
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </header>
        <!-- Icons Grid -->
        <section class="features-icons bg-light text-center">
            <h1 class="text-center">Top Products</h1>
            <div class="container">
                <c:import url="WEB-INF/home.xsl" var="xsldoc"/>
                <c:set var="list" value="${sessionScope.PRODUCT}"/>
                <c:forEach var="dto" items="${list}">
                    <x:transform xml="${dto}" xslt="${xsldoc}"/>
                </c:forEach>
            </div>
        </section>

    </body>

</html>
