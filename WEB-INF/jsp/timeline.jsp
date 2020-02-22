<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/timeline.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick-theme.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick.css' />">
        <script src="<c:url value='/js/slick.min.js'/>"></script>
        <title>�z�[��</title>
        <script>
            $(function(){
                $('.single').slick({
                    autoplay: false,
                    accessibility:false,
                    arrows:false,
                    dots: true,
                    infinite: false,
                });
            });
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
                    console.log("�ǂݍ��ݎ��s");
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
                    console.log("�ǂݍ��ݎ��s");
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
                <a href="create-timeline"><img id="create_timeline" src="<c:url value='/image/plus.png' />" height="25px" width="25px"></a>
            </div>
        </div>
    </c:param>
    <c:param name="content">
        <div id="main">
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
                                    <img class="comment" src="<c:url value='/image/comment.png' />" width="10%" height="40%"/>

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
                    <h2>�Ȃɂ�����܂���</h2>
                </div>
            </c:if>
        </div>
        <script src="<c:url value='/js/index.umd.min.js' />"></script>
        <script>
            PullToRefresh.setPointerEventsMode(true);
            PullToRefresh.init({
                mainElement: '#main',
                onRefresh: function() {
                    window.location.reload();
                }
            });
        </script>
    </c:param>
</c:import>