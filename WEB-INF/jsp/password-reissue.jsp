<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <title>�p�X���[�h�̍Ĕ��s</title>
    </c:param>
    <c:param name="content">
        <form action="PassChangServlet" method="post">
            <input type ="hidden" value="${param.RandomCode}" name="Random" required>
            <div class="mallStart">
                <label for="input" class="l_input">�p�X���[�h</label>
                <input class="input" type="password" name="pass">
                <div class="text_underline"></div>
            </div>
            <div class="passStart">
                <label for="input" class="l_input">�p�X���[�h(�m�F)</label>
                <input class="input" type="password" name="confirm">
                <div class="text_underline"></div>
            </div>
            <div class="loginStart">
                <input class="btn-border" type="submit" value="�ύX">
            </div>
        </form>
    </c:param>
</c:import>