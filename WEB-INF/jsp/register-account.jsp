<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <script src="<c:url value='/js/register-account.js' />"></script>
        <title>新規アカウント登録</title>
    </c:param>
    <c:param name="content">
        <form action="InputAccountServlet" method="post">
            <div class="mallStart">
                <label for="input" class="l_input">メールアドレス</label>
                <input class="input" placeholder="xxx@legame.co.jp" id="mail" type="email" name="mail" required>
                <div class="text_underline"></div>
            </div>
            <div class="mail_error">
            </div>
            <div class="passStart">
                <label for="input" class="l_input">パスワード</label>
                <input class="input" type="password" id="new_pass" name="pass" maxlength="20" required>
                <div class="text_underline"></div>
            </div>

            <div class="passStart">
                <label for="input" class="l_input">パスワード(再確認)</label>
                <input class="input" type="password" id="confirm" name="pass1" maxlength="20" required>
                <div class="text_underline"></div>
            </div>
            <span class="error"></span>
            <span class="setting">8文字以上20文字以下</span>
<%--            <div class="error">--%>
<%--            </div>--%>
            <div class="loginStart">
                <input class="btn-border" type="button" value="登録">
            </div>
        </form>
    </c:param>
</c:import>