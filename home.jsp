<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>profile</title>
    <script>
        function profilePage(action,id){
            $('<form/>',{action:action, method:"post"})
                .append("<input type='hidden' name='id' value='"+id+"'>")
                .appendTo($('body'))
                .submit();
        }
        var noticeFlag = 0;
        function FriendNotice(){
            $.ajax({
                url: "AjaxFriendNoticeServlet",
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
        setInterval(FriendNotice, 1000);
    </script>
</head>
<body>
<p><a href="deletesession">ログアウト</a></p>
<p><a href="DeleteUserAccountServlet">アカウントを削除</a></p>
<div id="noticeOuter">
    <p id="notice">${noticeCount}</p>
    <a href="NewFriendListServlet">友達検索</a>
</div>
<p><a href="profilesetting">プロフィール設定</a></p>
<h1>profile</h1>
<table>
    <tr><td>id</td><td>name</td><td>comment</td><td>picture</td></tr>
    <tr><td> ${sessionScope.ub.search_id}</td><td> ${sessionScope.ub.name}</td><td> ${sessionScope.ub.single_word}</td><td><a onclick="profilePage('ProfileMyPageServlet',${sessionScope.ub.user_id});return false;" href="#"><img src="data:image;base64,${sessionScope.ub.top_picture}" height="20%" width="10%" style="border: solid;"></a></td></tr>
</table>
</video>
<h1>友達リスト</h1>
    <table border="1">
        <tr><td>picture</td><td>name</td><td>comment</td></tr>
        <c:forEach var="fl" items="${friendList}">
        <tr>
            <td style="width: 20%;height: 100%;"><a onclick="profilePage('ProfilePageServlet',${fl.user_id});return false;" href="#">プロフィールへ<img src="data:image;base64,${fl.top_picture}" height="20%" width="80%" style="border: solid;"></a></td>
            <td>${fl.name}</td>
            <td>${fl.single_word}</td>
        </c:forEach>
    </table>
<a href="ChatPageServlet">チャット画面</a>
<a href="app.jsp">app</a>
<a href="TimeLineServlet">タイムライン</a>
</body>
</html>
