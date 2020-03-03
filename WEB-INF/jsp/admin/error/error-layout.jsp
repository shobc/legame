<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <link rel="stylesheet" href="<c:url value='/css/admin/error/error.css' />">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/admin/deviceJudge.js' />"></script>
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
    ${param.head}
</head>
<body>
<div class="image">
    <img src="<c:url value='/image/logo.PNG' />" height="10%" width="30%">
</div>
<div class="error_message">
    <h1>${param.errorMessage}</h1>
</div>
${param.content}
</body>
</html>