<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <title>利用できません</title>
        <style>
            span{
                position: relative;
                top:300px;
            }
        </style>
    </c:param>
    <c:param name="content">
        <center>
            <span>PC・タブレットからはアクセスできません</span>
        </center>
    </c:param>
</c:import>