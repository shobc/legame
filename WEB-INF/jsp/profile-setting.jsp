<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/profile-setting-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/profile-setting.css' />">
        <title>プロフィール設定</title>
    </c:param>
    <c:param name="back">
        <a class="back" href="HomePageServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
    </c:param>
    <c:param name="content">
        <div class="container">
            <div>
                <a href="change-top-picture"><img class="top_picture" src="data:image;base64,${sessionScope.ub.top_picture}" width="100%" height="100%"></a>
            </div>
        </div>
        <div class="profile-element-outer">
            <span>名前</span>
            <a href="change-name">
                <div class="profile-element">
                    <p>${sessionScope.ub.name}</p>
                </div>
            </a>
        </div>
        <div class="profile-element-outer">
            <span>検索ID</span>
            <a href="change-searchId">
                <div class="profile-element">
                    <p>${sessionScope.ub.search_id}</p>
                </div>
            </a>
        </div>
        <div class="profile-element-outer">
            <span>一言</span>
            <a href="change-singleWord">
                <div class="profile-element">
                    <p class="single_word">${sessionScope.ub.single_word}</p>
                </div>
            </a>
        </div>
    </c:param>
</c:import>