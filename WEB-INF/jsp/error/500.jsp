<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/error/error-layout.jsp">
    <c:param name="head">
        <title>500ページ</title>
    </c:param>
    <c:param name="errorMessage">
        内部エラーが起きました
    </c:param>
    <c:param name="content">
        <div class="url_404">
            <span><a href="HomePageServlet">homeに戻る</a></span>
        </div>
    </c:param>
</c:import>