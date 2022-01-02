<%-- 
    Document   : index
    Created on : Oct 7, 2019, 3:41:58 PM
    Author     : T.Z.B
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <form action="CrawlerServlet" method="POST">
            <input type="submit" value="Crawl Hobby Search" name="btnAction" />
        </form>
        <form action="CrawlerServlet" method="POST">
            <input type="submit" value="Crawl Supper Hobby" name="btnAction" />
        </form>
        <form action="CrawlerServlet" method="POST">
            <input type="submit" value="Stop" name="btnAction" />
        </form>
        <form action="ProcessServlet">
            <input type="submit" value="" name="btnAction" />
        </form>
    </body>
</html>
