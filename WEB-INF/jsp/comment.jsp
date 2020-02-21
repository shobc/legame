<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/comment.css' />">
        <title>ÉRÉÅÉìÉg</title>
        <script>
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
            var i = 0;
            function reply(name,id) {
                if(i===0){
                    $("#comment").before("<span>@"+name+"</span><br>").before("<input type='hidden' name='reply_user_id' value="+id+">");
                    i++;
                }
            }

            function ajaxLikeComment(tid,cid){
                var Id = "#"+(tid+cid)+"comment";
                var id = (tid+cid);
                $.ajax({
                    url: "CommentLikeServlet",
                    type: "POST",
                    data: {timeline_id :tid,comment_id :cid,likeJudge:$(Id).text()}
                }).done(function (result) {
                    if($(Id).text()==="0"){
                        $(Id).text("1");
                        var count = parseInt($("#c"+id).text());
                        $("#c"+id).text(count+1);
                    }else if($(Id).text()==="1"){
                        $(Id).text("0");
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
                <c:forEach var="tt" items="${tlb.timeline_picutre}">
                    <img src="data:image;base64,${tt.base64Image}" width="100%" height="100%"/>
                </c:forEach>
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
    </c:param>
</c:import>