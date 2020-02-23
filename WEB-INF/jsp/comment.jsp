<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/slick-theme.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick.css' />">
        <link rel="stylesheet" href="<c:url value='/css/comment.css' />">
        <script src="<c:url value='/js/slick.min.js'/>"></script>
        <script src="<c:url value='/js/comment.js'/>"></script>
        <title>コメント</title>
        <script>
            var timeline_id =' ${tlb.timeline_id}';
            var heard_url = "<c:url value='/image/heart.png' />";
            var white_heard_url = "<c:url value='/image/white_heart.png' />";
            function commentSend(){
                if(!$("#message").val().indexOf(reply_user_name)){
                    $('<form/>',{action:"CommentAddServlet", method:"post"})
                        .append("<input type='hidden' name='timeline_id' value='"+timeline_id+"'>")
                        .append("<input type='hidden' name='comment' value='"+$("#message").val().substring(reply_user_name.length)+"'>")
                        .append("<input type='hidden' name='reply_user_id' value='"+user_id+"'>")
                        .appendTo($('body'))
                        .submit();
                }else{
                    $('<form/>',{action:"CommentAddServlet", method:"post"})
                        .append("<input type='hidden' name='timeline_id' value='"+timeline_id+"'>")
                        .append("<input type='hidden' name='comment' value='"+$("#message").val()+"'>")
                        .appendTo($('body'))
                        .submit();
                }
            }
        </script>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">コメント</h2>
            <a class="back" href="TimeLineServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <div class="timelineContent">
            <div class="container">
                <div class="top_picture_div">
                    <div>
                        <c:choose>
                            <c:when test = "${tlb.user_id==sessionScope.ub.user_id}">
                                <a onclick="profilePage('ProfileMyPageServlet',${tlb.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tlb.top_picture}" height="10%"></a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="profilePage('ProfilePageServlet',${tlb.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tlb.top_picture}" height="10%"></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="user_name_time">
                        <div>
                            <span class="user_name">${tlb.name}</span>
                        </div>
                        <div>
                            <span>${tlb.timeline_time}</span>
                        </div>
                    </div>
                </div>
                <div class="delete_report">
                    <c:if test="${tlb.user_id==sessionScope.ub.user_id}">
                        <span><a href="TimeLineDeleteServlet?timeline_id=${tlb.timeline_id}">削除</a></span>
                    </c:if>
                    <c:if test="${tlb.user_id!=sessionScope.ub.user_id}">
                        <span><a onclick="ajaxReportTimeLine(${tlb.timeline_id})">通報</a></span>
                    </c:if>
                </div>
            </div>
            <div class="image">
                <div class="single">
                    <c:forEach var="tt" items="${tlb.timeline_picutre}">
                        <div class="sentence_image">
                            <img  src="data:image;base64,${tt.base64Image}" width="100%" height="300px"/>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="timeline_sentence">
                <p>${tlb.timeline_sentence}</p>
            </div>
            <div class="comment_good">
                <c:choose>
                    <c:when test = "${empty tlb.timeline_like_id}">
                        <img class="good" id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id},0)" src="<c:url value='/image/white_heart.png' />" width="8%" height="40%"/>
                        <span class="good_count" id="c${tlb.timeline_id}">${tlb.like_count}</span>
                    </c:when>
                    <c:otherwise>
                        <img class="good" id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id},1)" src="<c:url value='/image/heart.png' />" width="8%" height="40%"/>
                        <span class="good_count" id="c${tlb.timeline_id}">${tlb.like_count}</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="comments">
            <c:forEach var="ca" items="${commentArray}">
                <div class="balloon6">
                    <div class="face_icon">
                        <c:choose>
                            <c:when test = "${ca.user_id==sessionScope.ub.user_id}">
                                <a onclick="profilePage('ProfileMyPageServlet',${ca.user_id});return false;" href="#"><img src="data:image;base64,${ca.top_picture}"></a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="profilePage('ProfilePageServlet',${ca.user_id});return false;" href="#"><img src="data:image;base64,${ca.top_picture}"></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="chatting">
                        <div class="says">
                            <span class="comment_user_name">${ca.name}</span>
                            <p class="reply_user_name">${ca.reply_user_name}</p>
                            <p>${ca.comment_sentence}
                                <div class="reply" >
                                    <c:choose>
                                        <c:when test = "${empty ca.comment_like_id}">
                                            <img class="comment_like" id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id},0)" src="<c:url value='/image/white_heart.png' />" width="8%" height="30%"/>
                                            <span class="comment_good_count" id="c${ca.timeline_id+ca.comment_id}">${ca.comment_like_count}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <img class="comment_like" id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id},1)" src="<c:url value='/image/heart.png' />" width="8%" height="30%"/>
                                            <span  class="comment_good_count" id="c${ca.timeline_id+ca.comment_id}">${ca.comment_like_count}</span>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test = "${ca.user_id!=sessionScope.ub.user_id}">
                                        <span class="reply_btn" onclick="reply('${ca.name}',${ca.user_id})">返信する</span>
                                    </c:if>
                                </div>
                            </p>
                        </div>
                        <div class="time">
                                ${ca.comment_time}
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="footer">
            <div class="input">
                <div class="sendMessage">
                    <textarea id='message' class="text_area" maxlength="50" required></textarea>
                </div>
                <div>
                    <img class="btn-flat-border" onclick="commentSend()" src="<c:url value='/image/send.png' />"/>
                </div>
            </div>
        </div>
    </c:param>
</c:import>