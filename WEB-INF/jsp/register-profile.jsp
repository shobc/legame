<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"
        isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/darkroom.css' />" />
        <link rel="stylesheet" href="<c:url value='/css/register-profile.css' />" />
        <script src="<c:url value='/js/register-profile.js' />"></script>
        <title>�v���t�B�[���ݒ�</title>
    </c:param>
    <c:param name="content">
        <div class="caption">
            <h3>�v���t�B�[���ݒ�</h3>
        </div>
        <span>���K�{</span>
        <form action="RegisterProfileServlet" method="post">
            <input type="hidden" name="user_id" value="${sessionScope.user_id}">
            <div>
                <label for="input" class="l_input">�����O</label>
                <input class="input" type="text" id="user_name" maxlength="10" name="name" required>
                <div class="text_underline"></div>
                <span class="name_error"></span>
                <span class="setting">10�����ȓ�</span>
            </div>
            <div class="textStart">
                <label for="input" class="l_input">������ID</label>
                <input class="input" id="search_id" type="text" maxlength="20" name="id" required>
                <div class="text_underline"></div>
                <span class="error_e">${pageContext.exception.message}</span>
                <span class="error"></span>
                <span class="setting">8�����ȏ�20�����ȓ�</span>
            </div>
            <div class="textStart">
                <label for="input" class="l_input">�R�����g</label>
                <input class="input" type="text" name="comment" maxlength="20">
                <div class="text_underline"></div>
                <span class="setting">20�����ȓ�</span>
            </div>
            <p id="img" ></p>
            <p id="btn" onclick="trimming()">�g���~���O</p>
            <div class="textStart">
                <label class="upload-img-btn">
                    TOP�摜
                    <input type="file"  multiple="multiple" accept="image/*" onchange="OnFileSelect(this); showTrimming();" id="profileImage">
                    <input type="hidden" name="base64Image" id="base64Image">
                </label>
            </div>
                <%--    �������Ă͂����Ȃ���--%>
            <script src="/legame/js/fabric.js"></script>
            <script src="/legame/js/darkroom.js"></script>
                <%--    �������Ă͂����Ȃ���--%>
            <div class="registerStart">
                <input class="btn-border" type="button" value="�o�^">
            </div>
        </form>
    </c:param>
</c:import>