<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/admin/deviceJudge.js' />"></script>
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
    <img src="<c:url value='/image/logo.PNG' />" height="10%" width="30%">
</div>
<p id="caption">${param.caption}</p>
<div id="nav-content">
    <div class="hamburger-top"></div>
    <li class="category-title user">���O�C�����[�U:${sessionScope.aub.user_name}</li>
    <ul class="category">
        <li class="category-title"><a href="AdminHomePageServlet">�S�Ẵ^�C�����C��</a></li>
        <li class="category-title"><a href="#"><a href="ReportTimeLineServlet">�ʕ񂳂ꂽ�^�C�����C��</a></a></li>
        <li class="category-title"><a href="ReportChatListServlet">�ʕ񂳂ꂽ�`���b�g</a></li>
        <li class="category-title"><a href="UserListServlet">���[�U�[�ݒ�</a></li>
        <li class="category-title"><a href="#">���X�̐\������</a></li>
        <li class="category-title logout"><img src="<c:url value='/image/logout.png' />"id="logoutImage" height="3%" width="9%"><a href="LogoutServlet">���O�A�E�g</a></li>
    </ul>
</div>
${param.content}
</body>
</html>