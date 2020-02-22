<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/comment-notice.css' />">
        <title>通知</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">通知</h2>
            <a class="back" href="TimeLineServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>

        <div class="notice_list">
            <c:forEach var="cn" items="${commentNotice}">
                <a class ="Link" href="CommentSearchServlet?timeline_id=${cn.timeline_id}">
                    <div class="DivLink">
                        <div>
                            <img class="top_picture" src="data:image;base64,${cn.top_picture}" height="10%">
                        </div>
                        <div class="notice">
                            <p>${cn.name}さんからコメントがありました</p>
                            <p>${cn.timeline_time}</p>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </c:param>
</c:import>