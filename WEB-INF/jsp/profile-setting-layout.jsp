<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/profile-setting-layout.css' />">
        ${param.head}
    </c:param>
    <c:param name="content">
        <div class="titleStart">
            <h2 id="title">�v���t�B�[���ݒ�</h2>
            ${param.back}
        </div>
        <div class="content">
                ${param.content}
        </div>
    </c:param>
</c:import>