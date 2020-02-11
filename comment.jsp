<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>�^�C�����C��</title>
    <script>
        function ajaxLike(id){
            var Id = "#"+id;
            $.ajax({
                url: "LikeServlet",
                type: "GET",
                data: {timeline_id :id,likeJudge:$(Id).text()}
            }).done(function (result) {
                if($(Id).text()==="0"){
                    $(Id).text("1");
                }else if($(Id).text()==="1"){
                    $(Id).text("0");
                }
            }).fail(function () {
                alert("�ǂݍ��ݎ��s");
            }).always(function (result) {
            });
        }
        var i = 0;
        function reply(name,id) {
            if(i===0){
                $("#comment").before("<span>@"+name+"</span><br>").before("<input type='hidden' name='reply_user_id' value="+id+">");
                i++;
            }
        }

        function ajaxLikeComment(tid,cid){
            var Id = "#"+(tid+cid)+"comment";
            $.ajax({
                url: "CommentLikeServlet",
                type: "POST",
                data: {timeline_id :tid,comment_id :cid,likeJudge:$(Id).text()}
            }).done(function (result) {
                if($(Id).text()==="0"){
                    $(Id).text("1");
                }else if($(Id).text()==="1"){
                    $(Id).text("0");
                }
            }).fail(function () {
                alert("�ǂݍ��ݎ��s");
            }).always(function (result) {
            });
        }
        function profilePage(action,id){
            $('<form/>',{action:action, method:"post"})
                .append("<input type='hidden' name='id' value='"+id+"'>")
                .appendTo($('body'))
                .submit();
        }
    </script>
</head>
<body>
<h1>�I�����ꂽ�^�C�����C��</h1>
<table border="1">
<tr><th>���O&�ʐ^</th><th>����</th><th>�R�����g</th><th>������</th></tr>
    <tr>
        <td><a onclick="profilePage('ProfilePageServlet',${tlb.user_id});return false;" href="#">${tlb.name}<img src="data:image;base64,${tlb.top_picture}" height="10%"></a></td>
        <td>${tlb.timeline_time}</td>
        <td>${tlb.timeline_sentence}</td>
        <c:choose>
            <c:when test = "${empty tlb.timeline_like_id}">
                <td><button id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id})">0</button></td>
            </c:when>
            <c:otherwise>
                <td><button id="${tlb.timeline_id}" onclick="ajaxLike(${tlb.timeline_id})">1</button></td>
            </c:otherwise>
        </c:choose>
    </tr>
</table>
<h1>�R�����g�ꗗ</h1>
<table border="1">
    <tr><th>���O&�ʐ^</th><th>����</th><th>�R�����g</th><th>������</th><th>�ԐM</th><th>�ԐM���[�U�[</th></tr>
    <c:forEach var="ca" items="${commentArray}">
        <tr>
            <td><a onclick="profilePage('ProfilePageServlet',${ca.user_id});return false;" href="#">${ca.name}<img src="data:image;base64,${ca.top_picture}" height="10%"></a></td>
            <td>${ca.comment_time}</td>
            <td>${ca.comment_sentence}</td>
            <c:choose>
                <c:when test = "${empty ca.comment_like_id}">
                    <td><button id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id})">0</button></td>
                </c:when>
                <c:otherwise>
                    <td><button id="${ca.timeline_id+ca.comment_id}comment" onclick="ajaxLikeComment(${ca.timeline_id},${ca.comment_id})">1</button></td>
                </c:otherwise>
            </c:choose>
            <td><button onclick="reply('${ca.name}',${ca.user_id})">�ԐM</button></td>
            <td>${ca.reply_user_name}</td>
        </tr>
    </c:forEach>
</table>
<form action="CommentAddServlet" method="post" id="form">
    <input type="hidden" value="${tlb.timeline_id}" name="timeline_id">
    <input type="text" id="comment" name="comment"><br>
    <input type="submit" value="�R�����g">
</form>
</body>
</html>