<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/error/error-layout.jsp">
    <c:param name="head">
        <title>404�y�[�W</title>
    </c:param>
    <c:param name="errorMessage">
        �w�肵���y�[�W��������܂���
    </c:param>
    <c:param name="content">
        <div class="url_404">
            <span><a href="HomePageServlet">home�ɖ߂�</a></span>
        </div>
    </c:param>
</c:import>