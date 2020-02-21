<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/search-friend.css' />">
    </c:param>
    <c:param name="content">
        <div class="titleStart">
            <div>
                <a class="back" href="HomePageServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
            </div>
            <div>
                <h2>友達検索</h2>
            </div>
            <div>
                <span><a href="ShowFriendQRServlet"><img src="<c:url value='/image/qr.png' />" width="50px"></a></span>
            </div>
        </div>
        <div class="overall_search_input">
            <form method="post" action="FriendSearchServlet">
                <div class="search_input">
                    <div class="cp_iptxt">
                        <label class="ef">
                            <input type="search" name="id" min="100" step="10" placeholder="IDを入力" required>
                        </label>
                    </div>
                    <div>
                        <input type="submit" class="submit_box" value="検索">
                    </div>
                </div>
            </form>
        </div>

        <c:if test="${not empty requestScope.fb.user_id}">
            <div class="search_friend">
                <div>
                    <img class="imgmaru" src="data:image;base64,${requestScope.fb.top_picture}" height="20%">
                    <span class="new_friend_name">${requestScope.fb.name}</span>
                </div>
                <div class="judge_new_friend">
                    <span><a href="FriendAddServlet?friend_id=${requestScope.fb.user_id}">追加</a></span>
                </div>
            </div>
        </c:if>
        <div class="acquaintance">
            <span>知り合いかも？</span>
        </div>
        <div class="container">
            <c:forEach var="nfl" items="${newFrinedList}">
                <div class="new_friend">
                    <div>
                        <img class="imgmaru" src="data:image;base64,${nfl.top_picture}" height="20%">
                        <span class="new_friend_name">${nfl.name}</span>
                    </div>
                    <div class="judge_new_friend">
                        <span class="profile"><a href="NewFriendInfoServlet?friend_id=${nfl.user_id}">詳細</a></span>
                        <span class="profile"><a href="FriendAddServlet?friend_id=${nfl.user_id}">追加</a></span>
                        <span class="profile"><a href="NoFriendBlockServlet?friend_id=${nfl.user_id}">ブロック</a></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:param>
</c:import>