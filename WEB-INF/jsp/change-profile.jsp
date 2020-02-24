<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/profile-setting-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/change-profile.css' />">
        <script>
            var path = '${pageContext.request.servletPath}';
            var name = '${sessionScope.ub.name}';
            var search_id = '${sessionScope.ub.search_id}';
            var single_word = '${sessionScope.ub.single_word}';
        </script>
        <script src="<c:url value='/js/change-profile.js' />"></script>
        <title>プロフィール変更</title>
    </c:param>
    <c:param name="back">
        <a class="back" href="profile-setting"><img src="<c:url value='/image/back.png' />" width="20px"></a>
    </c:param>
    <c:param name="content">

        <div class="header">
                <h2 id="caption"></h2>
        </div>
        <form id="form" action="" method="post">
            <div id="changeArea">
                <label for="input" class="l_input"></label>
                <input type="text" class="text" name="" maxlength="20" value="" required>
                <div class="text_underline"></div>
                <span id="error"></span>
                <span id="setting"></span>
            </div>
            <div class="changeBtn">
                    <input type="submit" class="btn-border" value="変更">
            </div>
        </form>
        <div class="imageChange">
            <div class="image">
                <div class="imageInner">
                    <img class="top_picture" src="data:image;base64,${sessionScope.ub.top_picture}" height="10%"><br>
                </div>
            </div>
            <p id="img" ></p>
            <form action="ChangeTopPictureServlet" method="post">
                <div class="textStart">
                    <label class="upload-img-btn">
                        TOP画像
                        <input type="file"  multiple="multiple" accept="image/*" onchange="OnFileSelect(this); showTrimming();" id="profileImage" required>
                        <input type="hidden" value="${sessionScope.ub.top_picture}" name="base64Image" id="base64Image">
                    </label>
                </div>
                <div id="changeBtn">
                    <input type="submit" id="btn-border" value="変更">
                </div>
            </form>
        </div>
    </c:param>
</c:import>