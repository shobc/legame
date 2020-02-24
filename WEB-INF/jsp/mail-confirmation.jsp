<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/confirmation-screen.css' />">
        <title>確認</title>
    </c:param>
    <c:param name="content">
        <div class="confirm">
            <h2>メールを確認してください</h2>
        </div>
    </c:param>
</c:import>