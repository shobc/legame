<%@page pageEncoding="windows-31j"
         contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="profilePage">�v���t�B�[���ɖ߂�</a>
    <h1>����</h1>
    <p><a href="BlockUserListServlet">�u���b�N���X�g</a></p>
    <form action="FriendSearchServlet" method="post">
        <input type="search" name="id" placeholder="ID�����" required>
        <input type="submit" value="����">
    </form>
    <table>
        <tr>
            <td>picture</td><td>search_id</td><td>name</td><td>single_word</td>
        </tr>
        <tr><c:if test="${not empty requestScope.ub}">
            <td><img src="${requestScope.ub.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${requestScope.ub.search_id}</td>
            <td>${requestScope.ub.name}</td>
            <td>${requestScope.ub.single_word}</td>
            <td><a href="FriendAddServlet?friend_id=${requestScope.ub.user_id}">�ǉ�</a></td>
        </c:if>
        </tr>
    </table>
<p>�F�B����</p>
    <table>
        <tr>
            <td>picture</td><td>search_id</td><td>name</td><td>single_word</td>
        </tr>
        <c:forEach var="nfl" items="${newFrinedList}">
            <tr><td><img src="${nfl.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${nfl.search_id}</td>
            <td>${nfl.name}</td>
            <td>${nfl.single_word}</td>
            <td><a href="FriendAddServlet?friend_id=${nfl.user_id}">�ǉ�</a></td></tr>
        </c:forEach>
    </table>
</body>
</html>