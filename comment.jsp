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
            console.log("ajax");
            console.log(id);
            console.log(Id);
            console.log($(Id).text());
            $.ajax({
                url: "LikeServlet",
                type: "GET",
                data: {timeline_id :id,likeJudge:$(Id).text()}
            }).done(function (result) {
                if($(Id).text()==="0"){
                    console.log("LIKE");
                    $(Id).text("1");
                }else if($(Id).text()==="1"){
                    console.log("NOT LIKE");
                    $(Id).text("0");
                }
            }).fail(function () {
                alert("読み込み失敗");
            }).always(function (result) {
            });
        }
        function ajaxLikeComment(tid,cid){
            var Id = "#"+(tid+cid)+"comment";
            console.log("ajax");
            console.log(tid);
            console.log(cid);
            console.log(Id);
            console.log($(Id).text());
            $.ajax({
                url: "CommentLikeServlet",
                type: "POST",
                data: {timeline_id :tid,comment_id :cid,likeJudge:$(Id).text()}
            }).done(function (result) {
                if($(Id).text()==="0"){
                    console.log("LIKE");
                    $(Id).text("1");
                }else if($(Id).text()==="1"){
                    console.log("NOT LIKE");
                    $(Id).text("0");
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
<tr><th>名前&写真</th><th>時間</th><th>コメント</th><th>いいね</th></tr>
    <tr>
        <td><a onclick="profilePage('ProfilePageServlet',${tlb.user_id});return false;" href="#">${tlb.name}<img src="${tlb.top_picture}"></a></td>
        <td>${tlb.timeline_time}</td>
        <td>${tlb.timeline_sentence}</td>
        <c:choose>
            <c:when test = "${empty tlb.timeline_like_id}">
                <td><button id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id})">0</button></td>
            </c:when>
            <c:otherwise>
                <td><button id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id})">1</button></td>
            </c:otherwise>
        </c:choose>
    </tr>
</table>
<h1>コメント一覧</h1>
<table border="1">
    <tr><th>名前&写真</th><th>時間</th><th>コメント</th><th>いいね</th></tr>
    <c:forEach var="ca" items="${commentArray}">
        <tr>
            <td><a onclick="profilePage('ProfilePageServlet',${ca.user_id});return false;" href="#">${ca.name}<img src="${ca.top_picture}"></a></td>
            <td>${ca.comment_time}</td>
            <td>${ca.comment_sentence}</td>
            <c:choose>
                <c:when test = "${empty ca.comment_like_id}">
                    <td><button id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id})">0</button></td>
                </c:when>
                <c:otherwise>
                    <td><button id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id})">1</button></td>
                </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<form action="CommentAddServlet" method="post">
    <input type="hidden" value="${tlb.timeline_id}" name="timeline_id">
    <input type="text" name="comment"><br>
    <input type="submit" value="コメント">
</form>
</body>
</html>