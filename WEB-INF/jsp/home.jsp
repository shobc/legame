<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/home.css' />">
        <script src="<c:url value='/js/home.js'/>"></script>
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
<%--                <h2>home</h2>--%>
                <img class="home_logo" src="<c:url value='/image/home_logo.png' />">
            </div>
            <!--通知・友達検索-->
            <div class="notification">
                <span class="noticeCount">${noticeCount}</span>
                <a href="NewFriendListServlet"><img class="plus" src="<c:url value='/image/plus.png' />" width="20px;" height="20px;"></a>
            </div>
        </div>
    </c:param>
    <c:param name="content">
        <div id="myProf">
            <a onclick="profilePage('ProfileMyPageServlet',${sessionScope.ub.user_id});return false;" href="#">
                <div class="overall_my_profile">
                    <div>
                        <img class="top_picture" src="data:image;base64,${sessionScope.ub.top_picture}" height="50%">
                    </div>
                    <div class="profile_info">
                        <div class="user_name">
                            <span>${sessionScope.ub.name}</span>
                        </div>
                        <div class="single_word">
                            <span >${sessionScope.ub.single_word}</span>
                        </div>
                    </div>
                </div>
            </a>
        </div>
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
                <div class="friend" onclick="profilePage('ProfilePageServlet',${fl.user_id});return false;">
                    <img class="top_picture" src="data:image;base64,${fl.top_picture}" height="20%">
                    <span class="profile">${fl.name}</span>
                </div>
            </c:forEach>
        </div>
    </c:param>
</c:import>