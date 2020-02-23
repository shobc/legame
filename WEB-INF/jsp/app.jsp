<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/app.css' />">
        <script>
            $(function(){
                var genre_location = {
                    '主要':0,'国内':0,'国際':102,'経済':204,'エンタメ':306,
                    'スポーツ':408, 'IT':710, '科学':812, '地域':1080
                };
                $(".slide-box").each(function(i, o){
                    if($(o).children('a').text()==='${title}'){
                        $(o).children('a').css("border-bottom","solid 1px black");
                        $(".slide-wrap").scrollLeft(genre_location['${title}']);
                       }
                });
            });
        </script>
        <title>app</title>
    </c:param>
    <c:param name="title">
        <div class="titleStart">
            <center>
                <h2><img src="<c:url value='/image/App_logo.png' />" height="37px"></h2>

            </center>
        </div>
    </c:param>
    <c:param name="content">
        <div class="apps">
            <div class="app">
                <a href="WalletPageServlet"><img src="<c:url value='/image/LegPay.png' />" height="80px" width="150px"></a>
            </div>
            <div class="app">
                <a href="#"><img src="<c:url value='/image/LegTicke.png' />" height="80px" width="150px"></a>
            </div>
        </div>
        <div class="newsList">
            <div class="news_title">
                <span>news</span>
            </div>
            <div class="slide-wrap">
                <div class="slide-box">
                    <a href="AccessAppServlet?number=1">主要</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=2">国内</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=3">国際</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=4">経済</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=5">エンタメ</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=6">スポーツ</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=7">IT</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=8">科学</a>
                </div>
                <div class="slide-box">
                    <a href="AccessAppServlet?number=9">地域</a>
                </div>
            </div>
<%--            <div class="caption">--%>
<%--                <span>${title}</span>--%>
<%--            </div>--%>
            <div>
                <c:forEach var="nl" items="${newsList}">
                    <a href="${nl.link}" target="_blank">
                        <div class="container">
                            <div>
                                <img src="<c:url value='/image/yahoo_icon.png' />" height="20px" width="20px">
                                <div class="news">
                                    <span>${nl.title}</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </c:param>
</c:import>
