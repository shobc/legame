<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/non-login-layout.css' />">
        ${param.head}
    </c:param>
    <c:param name="content">
        <div class="titleStart">
            <center>
                <img src="<c:url value='/image/logo.PNG' />" alt="legame" width="50%" height="%">
            </center>
        </div>
        ${param.content}
    </c:param>
</c:import>