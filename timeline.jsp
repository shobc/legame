<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        function ajaxLike(id){
            console.log($('#'+id).text());
            var Id = '#'+id;
            $.ajax({
                url: 'LikeServlet',
                type: 'GET',
                data: {timeline_id :id,likeJudge:$(Id).text()}
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
                alert("読み込み失敗");
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
                alert("読み込み失敗");
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
    <title>タイムライン</title>
</head>
<body>
<div id="noticeOuter">
    <p id="notice">${timelineNotice}</p>
    <a href="CommentNoticeServlet">通知</a>
</div>
<a href="HomePageServlet">home</a>
<p><a href="createtimeline">投稿する</a></p>
    <table border="1">
        <tr><th>名前&写真</th><th>時間</th><th>投稿内容</th><th>コメントする</th><th>写真</th><th>いいね</th></tr>
        <c:forEach var="tll" items="${timelineList}">
            <tr>
                <c:choose>
                    <c:when test = "${tll.user_id==sessionScope.ub.user_id}">
                        <td><a onclick="profilePage('ProfileMyPageServlet',${tll.user_id});return false;" href="#">${tll.name}<img src="data:image;base64,${tll.top_picture}" height="10%"></a></td>
                    </c:when>
                    <c:otherwise>
                        <td><a onclick="profilePage('ProfilePageServlet',${tll.user_id});return false;" href="#">${tll.name}<img src="data:image;base64,${tll.top_picture}" height="10%"></a></td>
                    </c:otherwise>
                </c:choose>
                <td>${tll.timeline_time}</td>
                <td>${tll.timeline_sentence}</td>
                <td><a href="CommentSearchServlet?timeline_id=${tll.timeline_id}">comment</a></td>
                <c:forEach var="tt" items="${tll.timeline_picutre}">
                    <td><img src="data:image;base64,${tt.base64Image}" width="100%" height="10%"/></td>
                </c:forEach>
                <c:choose>
                    <c:when test = "${empty tll.timeline_like_id}">
                        <td><span  id="c${tll.timeline_id}">${tll.like_count}</span><button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">0</button></td>
                    </c:when>
                    <c:otherwise>
                        <td><span  id="c${tll.timeline_id}">${tll.like_count}</span><button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">1</button></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</body>
</html>