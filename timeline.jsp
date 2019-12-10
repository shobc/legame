<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <script>
        function ajaxLike(id){
            var Id = "#"+id;
            console.log("ajax");
            console.log(id);
            console.log($(Id).text());
            $.ajax({
                url: "LikeServlet",
                type: "GET",
                data: {timeline_id :id,likeJudge:$(Id).text()}
            }).done(function (result) {
                if($(Id).text()==="0"){
                    console.log("LIKE");
                    $(Id).text("1");
                }else if($(Id).text()==="1"){
                    console.log("NOT LIKE");
                    $(Id).text("0");
                }
            }).fail(function () {
                alert("�ǂݍ��ݎ��s");
            }).always(function (result) {
            });
        }
    </script>
    <title>�^�C�����C��</title>
</head>
<body>
<p><a href="createtimeline">���e����</a></p>
    <table border="1">
        <tr><th>���O&�ʐ^</th><th>����</th><th>���e���e</th><th>�R�����g����</th><th>������</th></tr>
        <c:forEach var="tla" items="${timelineArray}">
            <tr>
                <td><a href="${tla.user_id}">${tla.name}<img src="${tla.top_picture}"></a></td>
                <td>${tla.timeline_time}</td>
                <td>${tla.timeline_sentence}</td>
                <td><a href="CommentSearchServlet?timeline_id=${tla.timeline_id}">comment</a></td>
                <c:choose>
                    <c:when test = "${empty tla.timeline_like_id}">
                        <td><button id="${tla.timeline_id}" onclick="ajaxLike(${tla.timeline_id})">0</button></td>
                    </c:when>
                    <c:otherwise>
                        <td><button id="${tla.timeline_id}" onclick="ajaxLike(${tla.timeline_id})">1</button></td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</body>
</html>