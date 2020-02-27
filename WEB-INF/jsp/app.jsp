<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/login-default-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/app.css' />">
        <script>
            $(function(){
                var genre_location = {
                    '��v':0,'����':0,'����':102,'�o��':204,'�G���^��':306,
                    '�X�|�[�c':408, 'IT':710, '�Ȋw':812, '�n��':1080
                };
                var location = {
                    '�����s':0,'�_�ސ쌧':0,'��ʌ�':102,'��t��':206,
                    '��錧':308, '�Ȗ،�':308, '�Q�n��':508, '�R����':608
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
        <div class="weather_list">
            <div class="news_title">
                <span>weather<small style="font-size: 18px">(�֓�)</small></span>
            </div>
            <div class="slide-wrap weather-slide">
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=1&number=${number}">�����s</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=2&number=${number}">�_�ސ쌧</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=3&number=${number}">��ʌ�</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=4&number=${number}">��t��</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=5&number=${number}">��錧</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=6&number=${number}">�Ȗ،�</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=7&number=${number}">�Q�n��</a>
                </div>
                <div class="slide-box weather">
                    <a href="AccessAppServlet?weather_num=8&number=${number}">�R����</a>
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
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=1">��v</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=2">����</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=3">����</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=4">�o��</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=5">�G���^��</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=6">�X�|�[�c</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=7">IT</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=8">�Ȋw</a>
                </div>
                <div class="slide-box news">
                    <a href="AccessAppServlet?weather_num=${weather_num}&number=9">�n��</a>
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
