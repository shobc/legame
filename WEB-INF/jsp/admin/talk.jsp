<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/css/admin/talk.css' />">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/admin/talk.js' />"></script>
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
    <title>通報されたチャットに対してのトーク</title>
</head>
<body>
<div class="footer">
    <a class="back" href="ReportChatListServlet">戻る</a>
    <div class="image">
        <img src="<c:url value='/image/log.PNG' />" height="100%" width="30%">
    </div>
</div>

<div class="talk">
    <c:forEach var="tl" items="${talkList}">
        <div class="container">
            <div class="profile">
                <img class="top_picture" src="data:image;base64,${tl.top_picture}" height="10%"><br>
                <span>${tl.search_id}</span>
                <span class="date">${tl.date}</span>
            </div>
            <div class="chatting">
                <div class="says">
                    <span>${tl.content}</span>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>