<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/password-reissue.css' />">
        <script src="<c:url value='/js/password-reissue.js'/>"></script>
        <title>�p�X���[�h�̍Ĕ��s</title>
    </c:param>
    <c:param name="content">
        <form action="NoLoginPassWordChangeChangeServlet" method="post">
            <input type ="hidden" value="${param.RandomCode}" name="Random">
            <div class="mallStart">
                <label for="input" class="l_input">�p�X���[�h</label>
                <input class="input" type="password" name="pass" id="pass" maxlength="20" required>
                <div class="text_underline"></div>
            </div>
            <div class="passStart">
                <label for="input" class="l_input">�p�X���[�h(�m�F)</label>
                <input class="input" type="password" name="confirm" id="confirm" maxlength="20" required>
                <div class="text_underline"></div>
            </div>
            <span class="error"></span>
            <span class="setting">8�����ȏ�20�����ȉ�</span>
            <div class="loginStart">
                <input class="btn-border" type="button" value="�ύX">
            </div>
        </form>
    </c:param>
</c:import>