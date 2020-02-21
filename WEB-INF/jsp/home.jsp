<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/home.css' />">
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
        <title>ホーム</title>
    </c:param>
    <c:param name="title">
        <div class="top">
            <!-- ハンバーガーアイコン・メニュー -->
            <div id="nav-drawer">
                <input id="nav-input" type="checkbox" class="nav-unshown">
                <label id="nav-open" for="nav-input"><span></span></label>
                <label class="nav-unshown" id="nav-close" for="nav-input"></label>
                <!-- ハンバーガーアイコンの中身-->
                <div id="nav-content">
                    <p class="line"><a class="kugiri" href="profile-setting">プロフィール設定</a></p>
                    <p class="line"><a class="kugiri" href="BlockUserListServlet">ブロックリスト</a></p>
                    <p class="line"><a class="kugiri" href="change-password">パスワード変更</a></p>
                    <div class="account_danger">
                        <p><a class="kugiri" href="deletesession">ログアウト</a></p>
                        <p><a class="kugiri" href="DeleteUserAccountServlet">アカウント削除</a></p>
                    </div>
                </div>
            </div>
            <div>
                <h2>home</h2>
            </div>
            <!--通知・友達検索-->
            <div class="notification">
                <span class="noticeCount">${noticeCount}</span>
<%--                <img class="rogo" src="img/kuro.PNG" width="100px;" height="30px;">--%>
                <a href="NewFriendListServlet"><img class="plus" src="<c:url value='/image/plus.png' />" width="20px;" height="20px;"></a>
<%--                <img src="img/tuuti.png" width="40px;" height="40px;">--%>
            </div>
        </div>
    </c:param>
    <c:param name="content">
        <div id="myProf">
                <div class="overall_my_profile">
                    <a onclick="profilePage('ProfilePageServlet',${sessionScope.ub.user_id});return false;" href="#">
                        <img class="imgmaru" src="data:image;base64,${sessionScope.ub.top_picture}" height="50%" style="border-style:none;">
                        <span class="my_profile">
                        ${sessionScope.ub.name}<br>
                            <span class="single_word">${sessionScope.ub.single_word}</span>
                    </span>
                    </a>
                </div>
        </div>

        <!-- 友達リスト -->
        <script>
            $(function(){
                $("#container").click(function(){
                    $("#frinedList").slideToggle(400);  //スライドイン０，４秒
                    setTimeout(function(){
                        $("#up , #down").toggleClass("active passive");
                    },250);
                });
            });
        </script>

        <!-- 友達リストドロップダウン -->
        <div id="container">
            <div>
                <p class="titleDrop">友達リスト</p>
            </div>
            <div class="triangle">
                <img id="up" class="active" src="<c:url value='/image/triangle.png' />" width="60%" height="50%">
                <img id="down" class="passive" src="<c:url value='/image/reverseTriangle.png' />" width="60%" height="50%">
            </div>
        </div>

        <!-- 友達一人に対するプロフィール -->
        <div id="frinedList">
            <c:forEach var="fl" items="${friendList}">
                <div class="friend">
                    <a onclick="profilePage('ProfilePageServlet',${fl.user_id});return false;" href="#" >
                        <img class="imgmaru" src="data:image;base64,${fl.top_picture}" height="20%"  style="border-style:none;">
                        <span class="profile">${fl.name}</span>
                    </a>
                </div>
            </c:forEach>
        </div>
    </c:param>
</c:import>