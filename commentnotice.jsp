<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>�R�����g�ʒm</title>
</head>
<body>
<h1>�R�����g�ʒm</h1>
<table>
    <c:forEach var="cn" items="${commentNotice}">
        <tr>
            <td><a href="CommentSearchServlet?timeline_id=${cn.timeline_id}">���e������</a></td>
            <td>${cn.name}���񂩂�R�����g������܂���</td>
            <td>${cn.timeline_time}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>