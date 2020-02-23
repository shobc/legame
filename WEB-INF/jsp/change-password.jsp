<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/change-password.css' />">
        <script src="<c:url value='/js/change-password.js' />"></script>
        <title>�p�X���[�h�ύX</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">�p�X���[�h�ύX</h2>
            <a class="back" href="HomePageServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <form id="form" action="PassWordChangeServlet" method="post">
            <span class="error_old_pass">${error}</span>
            <div class="ok_pass">
                <span>${OK}</span>
            </div>
            <div class="changeArea">
                <label for="input" class="l_input">�Â��p�X���[�h</label>
                <input type="password" id="old_pass" class="text" name="old_pass" maxlength='20' required>
                <div class="text_underline"></div>
            </div>
            <div class="old_error">
            </div>
            <div class="changeArea">
                <label for="input" class="l_input">�V�����p�X���[�h(8�����ȏ�20�����ȉ�)</label>
                <input type="password" class="text" id="new_pass" name="new_pass" maxlength='20' required>
                <div class="text_underline"></div>
            </div>
            <div class="changeArea">
                <label for="input" class="l_input">�p�X���[�h(�m�F)</label>
                <input type="password" class="text" id="confirm" name="confirm" maxlength='20' required>
                <div class="text_underline"></div>
            </div>
            <div class="error">
            </div>
            <div class="changeBtn">
<%--                <input type="submit" class="btn-border" value="�ύX">--%>
                <button type="button" class="btn-border">�ύX</button>
            </div>
        </form>
    </c:param>
</c:import>