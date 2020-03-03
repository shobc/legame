<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"
        isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/admin/error/error-layout.jsp">
    <c:param name="head">
        <title>エラー</title>
    </c:param>
    <c:param name="errorMessage">
        ${pageContext.exception.message}
    </c:param>
    <c:param name="content">
        <div class="login_page">
            <span><a href="login">ログイン画面に戻る</a></span>
        </div>
    </c:param>
</c:import>