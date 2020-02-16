<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/admin/header.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/css/admin/header.css' />">
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
${param.head}
    <title>${param.title}</title>
</head>
<body>
<div id="hamburger">
    <div id="nav-open"><span></span></div>
</div>
<div class="image">
    <img src="<c:url value='/image/log.PNG' />" height="10%" width="30%">
</div>
<p id="caption">${param.caption}</p>
<div id="nav-content">
    <div class="hamburger-top"></div>
    <ul class="category">
        <li class="category-title">ログインユーザ:${sessionScope.aub.user_name}</li>
        <li class="category-title"><a href="AdminHomePageServlet">全てのタイムライン</a></li>
        <li class="category-title"><a href="#"><a href="ReportTimeLineServlet">通報されたタイムライン</a></a></li>
        <li class="category-title"><a href="ReportChatListServlet">通報されたチャット</a></li>
        <li class="category-title"><a href="UserListServlet">ユーザー設定</a></li>
        <li class="category-title"><a href="#">お店の申請許可</a></li>
        <li class="category-title"><a href="LogoutServlet">ログアウト</a></li>
    </ul>
</div>
${param.content}
</body>
</html>