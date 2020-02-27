<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="windows-31j">
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css' />">
    <link rel="stylesheet" href="<c:url value='/css/admin/talk.css' />">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />"></script>
    <script src="<c:url value='/js/admin/deviceJudge.js' />"></script>
<%--    <script src="<c:url value='/js/admin/talk.js' />"></script>--%>
    <link rel="icon" href="<c:url value='/image/legame.ico' />" type="image/vnd.microsoft.icon">
    <title>通報されたチャットに対してのトーク</title>
</head>
<body>
<div class="footer">
    <a href="ReportChatListServlet">
        <img class="back" src="<c:url value='/image/back.png' />" height="10%" width="3%">
    </a>
    <div class="image">
        <img src="<c:url value='/image/logo.PNG' />" height="100%" width="30%">
    </div>
</div>

<div class="talk_list">
    <div class="line-bc">
        <c:forEach var="tl" items="${talkList}">
            <div class="balloon6">
                <div class="face_icon">
                    <img class="top_picture" src="data:image;base64,${tl.top_picture}">
                </div>
                <div class="chatting">
                    <div class="says">
                        <p>${tl.content}</p>
                    </div>
                    <div class="時間">${tl.date}</div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</div>
</body>
</html>