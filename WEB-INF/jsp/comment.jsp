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
        <title>ÉRÉÅÉìÉg</title>
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
                var Id = "#"+id;
                var count_judge = count;
                console.log(count_judge);
                $.ajax({
                    url: "LikeServlet",
                    type: "GET",
                    data: {timeline_id :id,likeJudge:count_judge}
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
            var reply_user_name = null;
            var user_id;
            function reply(name,id) {
                if($("#message").val().indexOf(reply_user_name)){
                    $("#message").val("@"+name+" "+$("#message").val());
                    reply_user_name = "@"+name+" ";
                    user_id = id;
                }
            }
            function commentSend(){
                if(!$("#message").val().indexOf(reply_user_name)){
                    console.log(1);
                    $('<form/>',{action:"CommentAddServlet", method:"post"})
                        .append("<input type='hidden' name='timeline_id' value='${tlb.timeline_id}'>")
                        .append("<input type='hidden' name='comment' value='"+$("#message").val().substring(reply_user_name.length)+"'>")
                        .append("<input type='hidden' name='reply_user_id' value='"+user_id+"'>")
                        .appendTo($('body'))
                        .submit();
                }else{
                    console.log(12);
                    $('<form/>',{action:"CommentAddServlet", method:"post"})
                        .append("<input type='hidden' name='timeline_id' value='${tlb.timeline_id}'>")
                        .append("<input type='hidden' name='comment' value='"+$("#message").val()+"'>")
                        .appendTo($('body'))
                        .submit();
                }
            }
            function ajaxLikeComment(tid,cid,count){
                var Id = "#"+(tid+cid)+"comment";
                var id = (tid+cid);
                var timeline_id = tid;
                var comment_id = cid;
                var count_judge = count;
                $.ajax({
                    url: "CommentLikeServlet",
                    type: "POST",
                    data: {timeline_id :tid,comment_id :cid,likeJudge:count_judge}
                }).done(function (result) {
                    if(count_judge===0){
                        $(Id).attr("src","<c:url value='/image/heart.png' />");
                        $(Id).attr("onclick","ajaxLikeComment("+timeline_id+","+comment_id+",1)");
                        var count = parseInt($("#c"+id).text());
                        $("#c"+id).text(count+1);
                    }else if(count_judge===1){
                        $(Id).attr("src","<c:url value='/image/white_heart.png' />");
                        $(Id).attr("onclick","ajaxLikeComment("+timeline_id+","+comment_id+",0)");
                        var count = parseInt($("#c"+id).text());
                        $("#c"+id).text(count-1);
                    }
                }).fail(function () {
                    alert("ì«Ç›çûÇ›é∏îs");
                }).always(function (result) {
                });
            }
            function profilePage(action,id){
                $('<form/>',{action:action, method:"post"})
                    .append("<input type='hidden' name='id' value='"+id+"'>")
                    .appendTo($('body'))
                    .submit();
            }
            function ajaxReportTimeLine(id){
                $.ajax({
                    url: "AjaxReportTimeLineServlet",
                    type: "GET",
                    data: {timeline_id :id}
                }).done(function (result) {
                }).fail(function () {
                    alert("ì«Ç›çûÇ›é∏îs");
                }).always(function (result) {
                });
            }
        </script>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">ÉRÉÅÉìÉg</h2>
            <a class="back" href="TimeLineServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <div class="timelineContent">
            <div class="container">
                <div>
                    <c:choose>
                        <c:when test = "${tlb.user_id==sessionScope.ub.user_id}">
                            <a onclick="profilePage('ProfileMyPageServlet',${tlb.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tlb.top_picture}" height="10%"></a>
                                <span class="user_name">${tlb.name}</span>
                        </c:when>
                        <c:otherwise>
                            <a onclick="profilePage('ProfilePageServlet',${tlb.user_id});return false;" href="#"><img class="top_picture" src="data:image;base64,${tlb.top_picture}" height="10%"></a>
                            <span class="user_name">${tlb.name}</span>
                        </c:otherwise>
                    </c:choose>
                    <span>${tlb.timeline_time}</span>
                </div>
                <div>
                    <c:if test="${tlb.user_id==sessionScope.ub.user_id}">
                        <span><a href="TimeLineDeleteServlet?timeline_id=${tlb.timeline_id}">çÌèú</a></span>
                    </c:if>
                    <c:if test="${tlb.user_id!=sessionScope.ub.user_id}">
                        <span><a onclick="ajaxReportTimeLine(${tlb.timeline_id})">í ïÒ</a></span>
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
                            <p class="reply_user_name">${'@'+= ca.reply_user_name}</p>
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
                                        <span class="reply_btn" onclick="reply('${ca.name}',${ca.user_id})">ï‘êMÇ∑ÇÈ</span>
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
                    <textarea id='message' class="text_area"></textarea>
                </div>
                <div>
                    <img class="btn-flat-border" onclick="commentSend()" src="<c:url value='/image/send.png' />"/>
                </div>
            </div>
        </div>
    </c:param>
</c:import>