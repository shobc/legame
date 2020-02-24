<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/profile.css' />">
        <title>プロフィール</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">プロフィール</h2>
            <a class="back" href="NewFriendListServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <div class="overall_profile">
            <div>
                <div class="profile_image">
                    <div>
                        <img class="profile_top_picture" src="data:image;base64,${requestScope.fb.top_picture}" height="20%" width="80%">
                    </div>
                </div>
                <div class="user_name_div">
                    <div>
                        <span class="profile_user_name">${requestScope.fb.name}</span>
                    </div>
                </div>
                <div class="single_word_div">
                    <div class="single_word_inner">
                        <span class="single_word">${requestScope.fb.single_word}</span>
                    </div>
                </div>
                <div class="talk_block">
                    <div>
                        <div>
                            <a href="FriendAddServlet?friend_id=${requestScope.fb.user_id}">
                                <img class="add_friend_image" src="<c:url value='/image/add_friend.jpg' />" width="80px">
                            </a>
                        </div>
                        <div class="image_title">
                            <span>追加</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:param>
</c:import>