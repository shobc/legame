<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/confirmation-screen.css' />">
    </c:param>
    <c:param name="content">
        <div class="confirm">
            <h1>アカウントを削除しました</h1>
            <a href="loginPage">ログイン画面に戻る</a>
        </div>
    </c:param>
</c:import>