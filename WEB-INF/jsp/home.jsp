<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/home.css' />">
        <script src="<c:url value='/js/home.js'/>"></script>
        <title>�z�[��</title>
    </c:param>
    <c:param name="title">
        <div class="top">
            <!-- �n���o�[�K�[�A�C�R���E���j���[ -->
            <div id="nav-drawer">
                <input id="nav-input" type="checkbox" class="nav-unshown">
                <label id="nav-open" for="nav-input"><span></span></label>
                <label class="nav-unshown" id="nav-close" for="nav-input"></label>
                <!-- �n���o�[�K�[�A�C�R���̒��g-->
                <div id="nav-content">
                    <p class="line"><a class="kugiri" href="profile-setting">�v���t�B�[���ݒ�</a></p>
                    <p class="line"><a class="kugiri" href="BlockUserListServlet">�u���b�N���X�g</a></p>
                    <p class="line"><a class="kugiri" href="change-password">�p�X���[�h�ύX</a></p>
                    <div class="account_danger">
                        <p><a class="kugiri" href="DeleteSessionServlet">���O�A�E�g</a></p>
                        <p><a class="kugiri" href="DeleteUserAccountServlet">�A�J�E���g�폜</a></p>
                    </div>
                </div>
            </div>
            <div>
<%--                <h2>home</h2>--%>
                <img class="home_logo" src="<c:url value='/image/home_logo.png' />">
            </div>
            <!--�ʒm�E�F�B����-->
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
        <!-- �F�B���X�g�h���b�v�_�E�� -->
        <div id="container">
            <div>
                <p class="titleDrop">�F�B���X�g</p>
            </div>
            <div class="triangle">
                <img id="up" class="active" src="<c:url value='/image/triangle.png' />" width="60%" height="50%">
                <img id="down" class="passive" src="<c:url value='/image/reverseTriangle.png' />" width="60%" height="50%">
            </div>
        </div>

        <!-- �F�B��l�ɑ΂���v���t�B�[�� -->
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