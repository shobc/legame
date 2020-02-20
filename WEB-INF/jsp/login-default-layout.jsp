<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/login-default-layout.css' />">
        <script>
            $(function(){
                if('${pageContext.request.servletPath}'==='/app'){
                    $(".application").attr("src","<c:url value='/image/app_1.png' />");
                }else if('${pageContext.request.servletPath}'==='/talk-list'){
                    $(".talk").attr("src","<c:url value='/image/talk_1.png' />");
                }else if('${pageContext.request.servletPath}'==='/home'){
                    $(".home").attr("src","<c:url value='/image/home_1.png' />");
                }else if('${pageContext.request.servletPath}'==='/timeline'){
                    $(".tl").attr("src","<c:url value='/image/tl_1.png' />");
                }
            });
        </script>
        ${param.head}
    </c:param>
    <c:param name="content">
        ${param.title}
        ${param.content}
        <div class="footer">
            <div class="inner_footer">
                <a href="HomePageServlet">
                    <div class="item">
                            <img class="footer_img home" src="<c:url value='/image/home2.png' />" height="25%" width="40%">
                        <center>
                            <span class="footer_text">home</span>
                        </center>
                    </div>
                </a>
                <a href="TalkListPageServlet">
                    <div class="item">
                        <img class="footer_img talk" src="<c:url value='/image/talk2.png' />" height="28%" width="40%">
                        <center>
                            <span class="footer_text">talk</span>
                        </center>
                    </div>
                </a>
                <a href="TimeLineServlet">
                    <div class="item">
                        <img class="footer_img tl" src="<c:url value='/image/tl3.png' />" height="25%" width="40%">
                        <center>
                            <span class="footer_text">timeline</span>
                        </center>
                    </div>
                </a>
                <a href="AccessAppServlet">
                    <div class="item">
                        <img class="footer_img application" src="<c:url value='/image/app2.png' />" height="25%" width="40%">
                        <center>
                            <span class="footer_text">app</span>
                        </center>
                    </div>
                </a>
            </div>
        </div>
    </c:param>
</c:import>