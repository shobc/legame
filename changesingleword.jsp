<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>–¼‘O‚ð•ÏX</title>
</head>
<body>
<h1>ˆêŒ¾</h1>
<table>
    <form action="ChangeSingleWordServlet" method="post">
        <input type="text" name="single_word" value="${sessionScope.ub.single_word}">
        <input type="submit" value="•ÏX">
    </form>
</table>
</body>
</html>