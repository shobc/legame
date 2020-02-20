<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/confirmation-screen.css' />">
        <script>
            function openLoginPage(){
                location.href='login';
            }

            setTimeout("openLoginPage()", 3000);
        </script>
    </c:param>
    <c:param name="content">
        <div class="confirm">
            <P>ìoò^äÆóπ</P>
            <P>ÉçÉOÉCÉìâÊñ Ç…ñﬂÇËÇ‹Ç∑</P>
        </div>
    </c:param>
</c:import>