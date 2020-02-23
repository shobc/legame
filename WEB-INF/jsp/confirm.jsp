<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/confirmation-screen.css' />">
        <script src="<c:url value='/js/confirm.js' />"></script>
    </c:param>
    <c:param name="content">
        <div class="confirm">
            <h3>${message}</h3>
            <P>ƒƒOƒCƒ“‰æ–Ê‚É–ß‚è‚Ü‚·</P>
        </div>
    </c:param>
</c:import>