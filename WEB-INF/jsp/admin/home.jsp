<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/admin/layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/admin/home.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick-theme.css' />">
        <link rel="stylesheet" href="<c:url value='/css/slick.css' />">
        <script src="<c:url value='/js/slick.min.js'/>"></script>
        <script>
            $(function(){
                $('.single').slick({
                    autoplay: false,
                    accessibility:false,
                    arrows:false,
                    dots: true,
                    infinite: false,
                });
            });
        </script>
    </c:param>
    <c:param name="title" value="${caption}"/>
    <c:param name="caption" value="${caption}"/>
    <c:param name="content">
        <div id="timeline">
            <c:forEach var="tll" items="${timelineList}">
                <div class="timelineContent">
                    <div class="container">
                        <div>
                            <img class="top_picture" src="data:image;base64,${tll.top_picture}" height="100%">
                            <span class="user_name">${tll.name}</span>
                        </div>
                        <div>
                            <span>${tll.time}</span>
                            <c:if test="${not empty tll.report_count}">
                                <span>í ïÒêî${tll.report_count}</span>
                            </c:if>
                            <span><a href="DeleteTimeLineServlet?timeline_id=${tll.timeline_id}">çÌèú</a></span>
                        </div>
                    </div>
                    <div>
                        <div class="single">
                            <c:forEach var="tt" items="${tll.timeline_picutre}">
                                <div>
                                    <img class="sentence_image" src="data:image;base64,${tt.base64Image}" width="100%" height="100%"/>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="timeline_sentence">
                        <p>${tll.timeline_sentence}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <c:if test="${empty timelineList}">
            <h2 id="noContent">Ç»Ç…Ç‡Ç†ÇËÇ‹ÇπÇÒ</h2>
        </c:if>
    </c:param>
</c:import>