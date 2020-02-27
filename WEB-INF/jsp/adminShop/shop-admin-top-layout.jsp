<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/css/adminShop/shop-admin-top-layout.css' />">
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
    ${param.head}
</head>
<body>
${param.content}
</body>
</html>