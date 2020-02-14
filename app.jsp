<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>アプリ一覧</title>
</head>
<body>
    <a href="WalletPageServlet">ウォレット</a>
    <a href="#">映画（まだいけないよ）</a>
    <h1></h1>
    <h1>${title}</h1>
    <a href="AccessAppServlet?number=1">主要</a>
    <a href="AccessAppServlet?number=2">国内</a>
    <a href="AccessAppServlet?number=3">国際</a>
    <a href="AccessAppServlet?number=4">経済</a>
    <a href="AccessAppServlet?number=5">エンタメ</a>
    <a href="AccessAppServlet?number=6">スポーツ</a>
    <a href="AccessAppServlet?number=7">IT</a>
    <a href="AccessAppServlet?number=8">科学</a>
    <a href="AccessAppServlet?number=9">地域</a>
    <table border="1">
        </tr>
        <c:forEach var="nl" items="${newsList}">
            <tr>
                <td>
                    <span><a href="${nl.link}" target="_blank">${nl.title}</a></span><br>
                    <span>${nl.time}</span>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>