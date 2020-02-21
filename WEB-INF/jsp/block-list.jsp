<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/block-list.css' />">
        <title>ブロックリスト</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">ブロックリスト</h2>
            <a class="back" href="HomePageServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <div id="block_list">
            <c:forEach var="bl" items="${blocklist}">
                <div class="block">
                    <div>
                        <img class="imgmaru" src="data:image;base64,${bl.top_picture}" height="20%">
                        <span class="user_name">${bl.name}</span>
                    </div>
                    <div class="judge_friend">
                        <span><a href="ReleaseFriendServlet?friend_id=${bl.user_id}">解除</a></span>
                        <span><a href="DeleteFriendServlet?friend_id=${bl.user_id}">削除</a></span>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty blocklist}">
            <div class="block_judge">
                <h3>ブロックしているユーザーがいません</h3>
            </div>
        </c:if>
    </c:param>
</c:import>