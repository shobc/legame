<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>タイムライン</title>
    <script>
        function ajaxLike(id){
            var Id = "#"+id;
            $.ajax({
                url: "LikeServlet",
                type: "GET",
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
                alert("読み込み失敗");
            }).always(function (result) {
            });
        }
        function profilePage(action,id){
            $('<form/>',{action:action, method:"post"})
                .append("<input type='hidden' name='id' value='"+id+"'>")
                .appendTo($('body'))
                .submit();
        }
    </script>
</head>
<body>
<h1>選択されたタイムライン</h1>
<table border="1">
    <tr>
        <c:choose>
            <c:when test = "${tlb.user_id==sessionScope.ub.user_id}">
                <td><a onclick="profilePage('ProfileMyPageServlet',${tlb.user_id});return false;" href="#">${tlb.name}<img src="data:image;base64,${tlb.top_picture}" height="10%"></a></td>
            </c:when>
            <c:otherwise>
                <td><a onclick="profilePage('ProfilePageServlet',${tlb.user_id});return false;" href="#">${tlb.name}<img src="data:image;base64,${tlb.top_picture}" height="10%"></a></td>
            </c:otherwise>
        </c:choose>
        <td>${tlb.timeline_time}</td>
        <td>${tlb.timeline_sentence}</td>
        <c:choose>
            <c:when test = "${empty tlb.timeline_like_id}">
                <td><span  id="c${tlb.timeline_id}">${tlb.like_count}</span><button id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id})">0</button></td>
            </c:when>
            <c:otherwise>
                <td><span  id="c${tlb.timeline_id}">${tlb.like_count}</span><button id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id})">1</button></td>
            </c:otherwise>
        </c:choose>
        <c:if test="${tlb.user_id==sessionScope.ub.user_id}">
            <td><a href="TimeLineDeleteServlet?timeline_id=${tlb.timeline_id}">削除</a></td>
        </c:if>

    </tr>
    <tr><th>名前&写真</th><th>時間</th><th>コメント</th><th>いいね</th><th>削除</th></tr>
</table>
<h1>コメント一覧</h1>
<table border="1">
    <tr><th>名前&写真</th><th>時間</th><th>コメント</th><th>いいね</th><th>返信</th><th>返信ユーザー</th></tr>
    <c:forEach var="ca" items="${commentArray}">
        <tr>
            <c:choose>
                <c:when test = "${ca.user_id==sessionScope.ub.user_id}">
                    <td><a onclick="profilePage('ProfileMyPageServlet',${ca.user_id});return false;" href="#">${ca.name}<img src="data:image;base64,${ca.top_picture}" height="10%"></a></td>
                </c:when>
                <c:otherwise>
                    <td><a onclick="profilePage('ProfilePageServlet',${ca.user_id});return false;" href="#">${ca.name}<img src="data:image;base64,${ca.top_picture}" height="10%"></a></td>
                </c:otherwise>
            </c:choose>
            <td>${ca.comment_time}</td>
            <td>${ca.comment_sentence}</td>
            <c:choose>
                <c:when test = "${empty ca.comment_like_id}">
                    <td><span  id="c${ca.timeline_id+ca.comment_id}">${ca.comment_like_count}</span><button id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id})">0</button></td>
                </c:when>
                <c:otherwise>
                    <td><span  id="c${ca.timeline_id+ca.comment_id}">${ca.comment_like_count}</span><button id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id})">1</button></td>
                </c:otherwise>
            </c:choose>
            <c:if test = "${ca.user_id!=sessionScope.ub.user_id}">
                <td><button onclick="reply('${ca.name}',${ca.user_id})">返信</button></td>
            </c:if>
            <td>${ca.reply_user_name}</td>
        </tr>
    </c:forEach>
</table>
<form action="CommentAddServlet" method="post" id="form">
    <input type="hidden" value="${tlb.timeline_id}" name="timeline_id">
    <input type="text" id="comment" name="comment"><br>
    <input type="submit" value="コメント">
</form>
</body>
</html>