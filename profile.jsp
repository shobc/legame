<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
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
        function sendPost(action,parameter,id){
            $('<form/>',{action:action, method:"post"})
                .append("<input type='hidden' name='"+parameter+"' value='"+id+"'>")
                .appendTo($('body'))
                .submit();
        }
    </script>
    <title>${ub.name}さんのプロフィール</title>
</head>
<body>
<a href="HomePageServlet">home</a>
<h1>${ub.name}さんのプロフィール</h1>
<table>
    <td style="width: 20%;height: 100%;"><img src="data:image;base64,${ub.top_picture}" height="20%" width="80%"></td>
    <td>${ub.name}</td>
    <td>${ub.single_word}</td>
    <c:if test="${sessionScope.ub.user_id!=requestScope.ub.user_id}">
        <td>    <a onclick="sendPost('RegisterChatServlet','friend_id',${ub.user_id})" href="#">チャットする</a></td>
        <td><a href="BlockUserServlet?user_id=${ub.user_id}">ブロック</a></td></tr>
    </c:if>
</table>

<table border="1">
    <tr><th>名前&写真</th><th>時間</th><th>投稿内容</th><th>コメントする</th><th>写真</th><th>いいね</th></tr>
    <c:forEach var="tll" items="${timelineList}">
        <tr>
            <td>${tll.name}<img src="data:image;base64,${tll.top_picture}" height="10%" width="100%"></td>
            <td>${tll.timeline_time}</td>
            <td>${tll.timeline_sentence}</td>
            <td><a href="CommentSearchServlet?timeline_id=${tll.timeline_id}">comment</a></td>
            <c:forEach var="tt" items="${tll.timeline_picutre}">
                <td><img src="data:image;base64,${tt.base64Image}" width="100%" height="10%"/></td>
            </c:forEach>
            <c:choose>
                <c:when test = "${empty tll.timeline_like_id}">
                    <td><span id="c${tll.timeline_id}">${tll.like_count}</span><button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">0</button></td>
                </c:when>
                <c:otherwise>
                    <td><span id="c${tll.timeline_id}">${tll.like_count}</span><button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">1</button></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>