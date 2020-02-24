<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"
        isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <title>ログイン</title>
    </c:param>
    <c:param name="content">
        <form action="/legame/HomePageServlet" method="post">
            <!--メールアドレスの枠-->
            <div class="mallStart">
                <label for="input" class="l_input">メールアドレス</label>
                <input class="input" placeholder="xxx@legame.co.jp" type="email" name="mail" required>
                <div class="text_underline"></div>
            </div>
            <!--パスワードの枠-->
            <div class="passStart">
                <label for="input" class="l_input">パスワード</label>
                <input class="input" type="password" name="pass" required>
                <div class="text_underline"></div>
            </div>
                <div class="error_message">
                    <p>${pageContext.exception.message}</p>
                </div>
                <!-- ログインボタン -->
            <div class="loginStart">
                <input class="btn-border" type="submit" value="ログイン">
            </div>
        </form>
        <!-- フッター -->
        <div class="footer">
            <center>パスワードを忘れた方は<a href="mail" >こちら</a></center><br>
            <center><nav><a href="register-account">新規会員登録</a></nav></center>
        </div>
    </c:param>
</c:import>