<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <title>�p�X���[�h�̍Ĕ��s</title>
    </c:param>
    <c:param name="content">
        <form action="ReminderPassServlet" method="post">
            <div class="mallStart">
                <label for="input" class="l_input">���[���A�h���X</label>
                <input class="input" placeholder="xxx@legame.co.jp" type="mail" name="mail" required>
                <div class="text_underline"></div>
            </div>

            <div class="loginStart">
                <input class="btn-border" type="submit" value="���M">
            </div>
        </form>
    </c:param>
</c:import>