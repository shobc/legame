<%@page pageEncoding="windows-31j"
         contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<a href="HomePageServlet">�v���t�B�[���ɖ߂�</a>
<a href="ShowFriendQRServlet">QR�\��</a>
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
        <tr><c:if test="${not empty requestScope.fb}">
            <td><img src="data:image;base64,${requestScope.fb.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${requestScope.fb.search_id}</td>
            <td>${requestScope.fb.name}</td>
            <td>${requestScope.fb.single_word}</td>
            <td><a href="FriendAddServlet?friend_id=${requestScope.fb.user_id}">�ǉ�</a></td>
        </c:if>
        </tr>
    </table>
<p>�F�B����</p>
    <table>
        <tr>
            <td>picture</td><td>name</td><td>�ڍ�</td><td>�ǉ�</td><td>�u���b�N</td>
        </tr>
        <c:forEach var="nfl" items="${newFrinedList}">
            <tr><td><img src="data:image;base64,${nfl.top_picture}" height="20%" width="10%" style="border: solid;"></td>
            <td>${nfl.name}</td>
            <td><a href="NewFriendInfoServlet?friend_id=${nfl.user_id}">�ڍ�</a></td>
            <td><a href="FriendAddServlet?friend_id=${nfl.user_id}">�ǉ�</a></td>
            <td><a href="NoFriendBlockServlet?friend_id=${nfl.user_id}">�u���b�N</a></td></tr>
        </c:forEach>
    </table>
</body>
</html>