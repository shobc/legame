<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/change-password.css' />">
        <title>�p�X���[�h�ύX</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">�p�X���[�h�ύX</h2>
            <a class="back" href="HomePageServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <form id="form" action="PassWordChangeServlet" method="post">
            <div id="changeArea">
                <label for="input" class="l_input">�Â��p�X���[�h</label>
                <input type="password" class="text" name="old_pass" required>
                <div class="text_underline"></div>
            </div>
            <div id="changeArea">
                <label for="input" class="l_input">�V�����p�X���[�h</label>
                <input type="password" class="text" name="new_pass" required>
                <div class="text_underline"></div>
            </div>
            <div id="changeArea">
                <label for="input" class="l_input">�p�X���[�h(�m�F)</label>
                <input type="password" class="text" name="confirm" required>
                <div class="text_underline"></div>
            </div>
            <div class="changeBtn">
                <input type="submit" class="btn-border" value="�ύX">
            </div>
        </form>
    </c:param>
</c:import>