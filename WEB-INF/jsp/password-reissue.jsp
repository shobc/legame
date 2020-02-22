<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <title>パスワードの再発行</title>
    </c:param>
    <c:param name="content">
        <form action="PassChangServlet" method="post">
            <input type ="hidden" value="${param.RandomCode}" name="Random" required>
            <div class="mallStart">
                <label for="input" class="l_input">パスワード</label>
                <input class="input" type="password" name="pass">
                <div class="text_underline"></div>
            </div>
            <div class="passStart">
                <label for="input" class="l_input">パスワード(確認)</label>
                <input class="input" type="password" name="confirm">
                <div class="text_underline"></div>
            </div>
            <div class="loginStart">
                <input class="btn-border" type="submit" value="変更">
            </div>
        </form>
    </c:param>
</c:import>