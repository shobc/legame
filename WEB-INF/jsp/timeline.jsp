<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/timeline.css' />">
        <title>ÉzÅ[ÉÄ</title>
        <script>
            function ajaxLike(id,count){
                console.log($('#'+id));
                var Id = '#'+id;
                var count_judge = count;
                console.log(Id);
                console.log(count);
                $.ajax({
                    url: 'LikeServlet',
                    type: 'GET',
                    data: {timeline_id :id,likeJudge:count}
                }).done(function (result) {
                    if(count_judge==0){
                        $(Id).attr("src","<c:url value='/image/heart.png' />");
                        $(Id).attr("onclick","ajaxLike("+id+",1)");
                        var count = parseInt($("#c"+id).text());
                        $("#c"+id).text(count+1);
                    }else if(count_judge==1){
                        $(Id).attr("src","<c:url value='/image/white_heart.png' />");
                        $(Id).attr("onclick","ajaxLike("+id+",0)");
                        var count = parseInt($("#c"+id).text());
                        $("#c"+id).text(count-1);
                    }
                }).fail(function () {
                    console.log("ì«Ç›çûÇ›é∏îs");
                }).always(function (result) {
                });
            }
            var noticeFlag = 0;
            function timelineNotice(){
                $.ajax({
                    url: "AjaxTimelineNoticeServlet",
                    type: "GET",
                    data: {}
                }).done(function (result) {
                    if(noticeFlag===0){
                        if(result=='new'){
                            if($("#notice").text()!='new'){
                                $("#notice").append(result);
                                noticeFlag++;
                            }
                        }
                    }
                }).fail(function () {
                    console.log("ì«Ç›çûÇ›é∏îs");
                }).always(function (result) {
                });
            }
            setInterval(timelineNotice, 1000);
            function profilePage(action,id){
                $('<form/>',{action:action, method:"post"})
                    .append("<input type='hidden' name='id' value='"+id+"'>")
                    .appendTo($('body'))
                    .submit();
            }
        </script>
    </c:param>
    <c:param name="title">
        <div class="timelineTitle">
            <div id="noticeOuter">
                <span id="notice">${timelineNotice}</span>
                <a href="CommentNoticeServlet"><img src="<c:url value='/image/tuuti.png' />" height="25px" width="25px"></a>
            </div>
            <div>
                <center>
                    <img class="timeline_logo" src="<c:url value='/image/timeline.png' />" height="190px">
                </center>
            </div>
            <div>
                <a href="createtimeline"><img id="create_timeline" src="<c:url value='/image/plus.png' />" height="25px" width="25px"></a>
            </div>
        </div>
    </c:param>
    <c:param name="content">
        <div id="timeline">
            <c:forEach var="tll" items="${timelineList}">
                <div class="timelineContent">
                    <div class="container">
                        <div>
                            <c:choose>
                                <c:when test = "${tll.user_id==sessionScope.ub.user_id}">
                                    <a onclick="profilePage('ProfileMyPageServlet',${tll.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tll.top_picture}" height="10%">
                                    <span class="user_name">${tll.name}</span></a>
                                </c:when>
                                <c:otherwise>
                                    <a onclick="profilePage('ProfilePageServlet',${tll.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tll.top_picture}" height="10%"></a>
                                    <span class="user_name">${tll.name}</span></a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div>
                            <span>${tll.timeline_time}</span>
                        </div>
                    </div>
                    <div>
                        <c:forEach var="tt" items="${tll.timeline_picutre}">
                            <img class="sentence_image" src="data:image;base64,${tt.base64Image}" width="100%" height="100%"/>
                        </c:forEach>
                    </div>
                    <div class="timeline_sentence">
                        <p>${tll.timeline_sentence}</p>
                    </div>
                    <div class="comment_good">
                        <span>
                            <a href="CommentSearchServlet?timeline_id=${tll.timeline_id}">
                                <img class="comment" src="<c:url value='/image/comment.png' />" width="10%" height="40%"/>

                            </a>
                        </span>
                        <c:choose>
                            <c:when test = "${empty tll.timeline_like_id}">
<%--                                <button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">0</button>--%>
                                <img class="good" id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id},0)" src="<c:url value='/image/white_heart.png' />" width="10%" height="40%"/>
                                <span class="good_count" id="c${tll.timeline_id}">${tll.like_count}</span>
                            </c:when>
                            <c:otherwise>
<%--                                <button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">1</button>--%>
                                <img class="good" id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id},1)" src="<c:url value='/image/heart.png' />" width="10%" height="40%"/>
                                <span class="good_count" id="c${tll.timeline_id}">${tll.like_count}</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty timelineList}">
            <h2 id="noContent">Ç»Ç…Ç‡Ç†ÇËÇ‹ÇπÇÒ</h2>
        </c:if>
    </c:param>
</c:import>