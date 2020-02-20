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
            <center>
               <h2>プロフィール設定</h2>
            </center>
        </div>
        <div class="content">
                ${param.content}
        </div>
    </c:param>
</c:import>