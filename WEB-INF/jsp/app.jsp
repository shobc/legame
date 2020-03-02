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
                var location = {
                    '東京都':0,'神奈川県':0,'埼玉県':102,'千葉県':206,
                    '茨城県':308, '栃木県':308, '群馬県':508, '山梨県':608
                };
                $(".news").each(function(i, o){
                    if($(o).children('a').text()==='${title}'){
                        $(o).children('a').css("border-bottom","solid 1px black");
                        $(".news-slide").scrollLeft(genre_location['${title}']);
                       }
                });
                $(".weather").each(function(i, o){
                    if($(o).children('a').text()==='${location}'){
                        $(o).children('a').css("border-bottom","solid 1px black");
                        $(".weather-slide").scrollLeft(location['${location}']);
                       }
                });
            });
        </script>
        <title>app</title>
    </c:param>
    <c:param name="title">
        <div class="titleStart">
            <center>
                <h2><img src="<c:url value='/image/App_logo.png' />" width="150px" height="37px"></h2>

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
        <div class="weather_list">
            <div class="news_title">
                <span>weather<small style="font-size: 18px">(関東)</small></span>
            </div>
            <div class="slide-wrap weather-slide">
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=1&number=${number}">東京都</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=2&number=${number}">神奈川県</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=3&number=${number}">埼玉県</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=4&number=${number}">千葉県</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=5&number=${number}">茨城県</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=6&number=${number}">栃木県</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=7&number=${number}">群馬県</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=8&number=${number}">山梨県</a>
                </div>
            </div>
            <div>
                <div class="weather">
                    <div>
                        <img src="<c:url value='/image/weather_icon/${wb.image_path}' />" height="70px" width="135px">
                    </div>
                    <div>
                        <span>${wb.weather}</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="newsList">
            <div class="news_title">
                <span>news</span>
            </div>
            <div class="slide-wrap news-slide">
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=1">主要</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=2">国内</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=3">国際</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=4">経済</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=5">エンタメ</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=6">スポーツ</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=7">IT</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=8">科学</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=9">地域</a>
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
