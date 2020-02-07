<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        function ajaxLike(id){
            var Id = "#"+id;
            console.log("ajax");
            console.log(id);
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
<a href="CommentNoticeServlet">${timelineNotice}通知</a>
<a href="HomePageServlet">home</a>
<p><a href="createtimeline">投稿する</a></p>
    <table border="1">
        <tr><th>名前&写真</th><th>時間</th><th>投稿内容</th><th>コメントする</th><th>写真</th><th>いいね</th></tr>
        <c:forEach var="tll" items="${timelineList}">
            <tr>
                <td><a onclick="profilePage('ProfilePageServlet',${tll.user_id});return false;" href="#">${tll.name}<img src="${tll.top_picture}"></a></td>
                <td>${tll.timeline_time}</td>
                <td>${tll.timeline_sentence}</td>
                <td><a href="CommentSearchServlet?timeline_id=${tll.timeline_id}">comment</a></td>
                <c:forEach var="tt" items="${tll.timeline_picutre}">
                    <td><img src="data:image;base64,${tt.base64Image}" width="240" height="300"/></td>
                </c:forEach>
                <c:choose>
                    <c:when test = "${empty tll.timeline_like_id}">
                        <td><button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">0</button></td>
                    </c:when>
                    <c:otherwise>
                        <td><button id="${tll.timeline_id}" onclick="ajaxLike(${tll.timeline_id})">1</button></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</body>
</html>