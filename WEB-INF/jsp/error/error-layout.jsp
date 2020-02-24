<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="<c:url value='/css/error/error.css' />">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/deviceJudge.js' />"></script>
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
    ${param.head}
</head>
<body>
    <div class="header">
        <center>
            <img src="<c:url value='/image/logo.PNG' />" alt="legame" width="50%" height="7%">
        </center>
    </div>
    <div class="error_message">
        <span>${param.errorMessage}</span>
    </div>
${param.content}
</body>
</html>