<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/search-friend.css' />">
        <script src="<c:url value='/js/search-friend.js' />"></script>
    </c:param>
    <c:param name="content">
        <div class="titleStart">
            <div>
                <a class="back" href="HomePageServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
            </div>
            <div>
                <h2>�F�B����</h2>
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
                            <input type="search" class="not" id="search_id" name="id" min="100" step="10" placeholder="ID�����" required>
                        </label>
                    </div>
                    <div>
                        <input type="image" src="<c:url value='/image/search.png' />" class="submit_box" width="40px">
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
                    <span><a href="FriendAddServlet?friend_id=${requestScope.fb.user_id}">�ǉ�</a></span>
                </div>
            </div>
        </c:if>
        <div class="acquaintance">
            <span>�m�荇�������H</span>
        </div>
        <div class="container">
            <c:forEach var="nfl" items="${newFrinedList}">
                <div class="new_friend">
                    <div>
                        <img class="imgmaru" src="data:image;base64,${nfl.top_picture}" height="20%">
                        <span class="new_friend_name">${nfl.name}</span>
                    </div>
                    <div class="judge_new_friend">
                        <span class="profile"><a href="NewFriendInfoServlet?friend_id=${nfl.user_id}">�ڍ�</a></span>
                        <span class="profile"><a href="FriendAddServlet?friend_id=${nfl.user_id}">�ǉ�</a></span>
                        <span class="profile"><a href="NoFriendBlockServlet?friend_id=${nfl.user_id}">�u���b�N</a></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:param>
</c:import>