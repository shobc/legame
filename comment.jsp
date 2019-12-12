<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>�^�C�����C��</title>
</head>
<body>
<h1>�I�����ꂽ�^�C�����C��</h1>
<table border="1">
<tr><th>���O&�ʐ^</th><th>����</th><th>�R�����g</th></tr>
    <tr>
        <td><a href="${tlb.user_id}">${tlb.name}<img src="${tlb.top_picture}"></a></td>
        <td>${tlb.timeline_time}</td>
        <td>${tlb.timeline_sentence}</td>
    </tr>
</table>
<h1>�R�����g�ꗗ</h1>
<table border="1">
    <tr><th>���O&�ʐ^</th><th>����</th><th>�R�����g</th></tr>
    <c:forEach var="ca" items="${commentArray}">
        <tr>
            <td><a href="${ca.user_id}">${ca.name}<img src="${ca.top_picture}"></a></td>
            <td>${ca.comment_time}</td>
            <td>${ca.comment_sentence}</td>
        </tr>
    </c:forEach>
</table>
<form action="CommentAddServlet" method="post">
    <input type="hidden" value="${tlb.timeline_id}" name="timeline_id">
    <input type="text" name="comment"><br>
    <input type="submit" value="�R�����g">
</form>
</body>
</html>