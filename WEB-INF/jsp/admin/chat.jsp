<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/admin/layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/admin/chat.css' />">
    </c:param>
    <c:param name="title" value="�ʕ񂳂ꂽ�`���b�g�ꗗ"/>
    <c:param name="caption" value="�ʕ񂳂ꂽ�`���b�g�ꗗ"/>
    <c:param name="content">
        <div class="containers">
            <div class="chat_list">
                <c:forEach var="cl" items="${chatList}">
                    <div class="container">
                        <div class="user">
                            <img class="top_picture" src="data:image;base64,${cl.top_picture}" height="10%">
                            <span>${cl.name}</span>
                            <span >${cl.last_talk}</span>
                        </div>
                        <div class="item">
                            <a href="TalkPageServlet?chat_id=${cl.chat_id}">�g�[�N������</a>
                            <a href="ReleaseChatServlet?chat_id=${cl.chat_id}">����</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <c:if test="${empty chatList}">
            <h2 id="noContent">��������܂���</h2>
        </c:if>
    </c:param>
</c:import>