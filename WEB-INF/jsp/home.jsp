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
                    alert("�ǂݍ��ݎ��s");
                }).always(function (result) {
                });
            }
            setInterval(FriendNotice, 1000);
        </script>
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
                        <p><a class="kugiri" href="deletesession">���O�A�E�g</a></p>
                        <p><a class="kugiri" href="DeleteUserAccountServlet">�A�J�E���g�폜</a></p>
                    </div>
                </div>
            </div>
            <div>
                <h2>home</h2>
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
                <div class="overall_my_profile">
                    <a onclick="profilePage('ProfileMyPageServlet',${sessionScope.ub.user_id});return false;" href="#">
                        <img class="imgmaru" src="data:image;base64,${sessionScope.ub.top_picture}" height="50%" style="border-style:none;">
                        <span class="my_profile">
                        ${sessionScope.ub.name}<br>
                            <span class="single_word">${sessionScope.ub.single_word}</span>
                    </span>
                    </a>
                </div>
        </div>

        <!-- �F�B���X�g -->
        <script>
            $(function(){
                $("#container").click(function(){
                    $("#frinedList").slideToggle(400);  //�X���C�h�C���O�C�S�b
                    setTimeout(function(){
                        $("#up , #down").toggleClass("active passive");
                    },250);
                });
            });
        </script>

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