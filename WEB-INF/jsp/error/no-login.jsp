<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"
        isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/error/error-layout.jsp">
    <c:param name="head">
        <title>�G���[</title>
    </c:param>
    <c:param name="errorMessage">
        ${pageContext.exception.message}
    </c:param>
    <c:param name="content">
        <div class="timeline_delete">
            <span><a href="login">���O�C����ʂɖ߂�</a></span>
        </div>
    </c:param>
</c:import>