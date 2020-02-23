<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/timeline.css' />">
        <link rel="stylesheet" href="<c:url value='/css/profile.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick-theme.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick.css' />">
        <script src="<c:url value='/js/slick.min.js'/>"></script>
        <script src="<c:url value='/js/profile.js'/>"></script>
        <title>プロフィール</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">プロフィール</h2>
            <a class="back" href="#" onclick="accessJudge()"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <div class="overall_profile">
            <div>
                <div class="profile_image">
                    <div>
                        <img class="profile_top_picture" src="data:image;base64,${ub.top_picture}" height="20%" width="80%">
                    </div>
                </div>
                <div class="user_name_div">
                    <div>
                        <span class="profile_user_name">${ub.name}</span>
                    </div>
                </div>
                <div class="single_word_div">
                    <div class="single_word_inner">
                        <span class="single_word">${ub.single_word}</span>
                    </div>
                </div>
                <div class="talk_block">
                    <c:if test="${sessionScope.ub.user_id!=requestScope.ub.user_id}">
                        <div>
                            <div>
                                <a onclick="sendPost('RegisterChatServlet','friend_id',${ub.user_id})" href="#">
                                    <img src="<c:url value='/image/profile_talk.png' />" width="40px">
                                </a>
                            </div>
                            <div class="image_title">
                                <span>talk</span>
                            </div>
                        </div>
                        <div>
                            <div>
                                <a href="BlockUserServlet?user_id=${ub.user_id}">
                                    <img src="<c:url value='/image/block.png' />" width="40px">
                                </a>
                            </div>
                            <div class="image_title">
                                <span>block</span>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div id="timeline">
            <c:forEach var="tll" items="${timelineList}">
                <div class="timelineContent">
                    <div class="container">
                        <div class="profile">
                            <div>
                                <a onclick="profilePage('ProfileMyPageServlet',${tll.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tll.top_picture}" height="10%"></a>
                            </div>
                            <div class="user_name_div">
                                <span class="user_name">${tll.name}</span></a>
                            </div>
                        </div>
                        <div>
                            <span>${tll.timeline_time}</span>
                        </div>
                    </div>
                    <div class="images">
                        <div class="single">
                            <c:forEach var="tt" items="${tll.timeline_picutre}">
                                <div>
                                    <img class="sentence_image" src="data:image;base64,${tt.base64Image}" width="100%" height="100%"/>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="timeline_sentence">
                        <p>${tll.timeline_sentence}</p>
                    </div>
                    <div class="comment_good">
                            <span>
                                <a href="CommentSearchServlet?timeline_id=${tll.timeline_id}">
                                    <img class="comment" src="<c:url value='/image/comment.png' />" width="8%" height="38%"/>

                                </a>
                            </span>
                        <c:choose>
                            <c:when test = "${empty tll.timeline_like_id}">
                                <img class="good" id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id},0)" src="<c:url value='/image/white_heart.png' />" width="8%" height="40%"/>
                                <span class="good_count" id="c${tll.timeline_id}">${tll.like_count}</span>
                            </c:when>
                            <c:otherwise>
                                <img class="good" id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id},1)" src="<c:url value='/image/heart.png' />" width="8%" height="40%"/>
                                <span class="good_count" id="c${tll.timeline_id}">${tll.like_count}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty timelineList}">
            <div id="noContent">
                <h2>なにもありません</h2>
            </div>
        </c:if>
    </c:param>
</c:import>