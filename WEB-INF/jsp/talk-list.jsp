<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/talk-list.css' />">
        <script>
            function sendPost(action,parameter,id){
                $('<form/>',{action:action, method:"post"})
                    .append("<input type='hidden' name='"+parameter+"' value='"+id+"'>")
                    .appendTo($('body'))
                    .submit();
            }
        </script>
        <title>トーク一覧</title>
    </c:param>
    <c:param name="title">
        <div class="titleStart">
            <center>
                <h2>トークリスト</h2>
            </center>
        </div>
    </c:param>
    <c:param name="content">
        <div class="talk_list">
            <c:forEach var="cl" items="${chatList}">
                <a onclick="sendPost('TalkPageServlet','chat_id',${cl.chat_id})" href="#">
                    <div class="container">
                        <div class="user">
                            <img src="data:image;base64,${cl.top_picture}" class="top_picture">
                            <span>${cl.name}</span>
                            <span>${cl.content}</span>
                        </div>
                        <div>
                            <c:if test="${cl.not_read_count != 0}">
                                <span>${cl.not_read_count}</span>
                            </c:if>
                            <a class="delete" href="DeleteChatServlet?chat_id=${cl.chat_id}">削除</a>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </c:param>
</c:import>