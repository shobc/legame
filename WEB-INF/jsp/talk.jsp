<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/talk.css' />">
    </c:param>
    <c:param name="content">
        <div class="btUnder">
            <div id="drawer">
                <a href="TalkListPageServlet" onclick="wsCloseConnection();">
                    <img class="back" src="<c:url value='/image/back.png' />" height="60px">
                </a>
            </div>
            <div>
                <div class="item2">${requestScope.yub.name}</div>
            </div>
            <div>
                <div class="item3">${friendJudge}</div>
            </div>
        </div>
        <!-- 実際の会話 -->
        <div class="talk_list">
            <div class="line-bc">
                <c:forEach var="tl" items="${talkList}">
                    <!--左-->
                    <c:if test="${sessionScope.ub.name != tl.name}">
                        <div class="balloon6">
                            <div class="face_icon">
                                <img src="data:image;base64,${tl.image}">
                            </div>
                            <div class="chatting">
                                <div class="says">
                                    <p>${tl.content}</p>
                                </div>
                                <div class="時間">${tl.mess_time}</div>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.ub.name == tl.name}">
                        <!--右-->
                        <div class="my_comment">
                            <p>
                                ${tl.content}
                            </p>
                        </div>
                        <span class="item">${tl.read_flag}</span>
                        <span>${tl.mess_time}</span>
                    </c:if>
                </c:forEach>
            </div>
        </div>
<%--        <div class="js-modal__btn"><img src="img/talkMenu.png" height="30px" width="30px"></div>--%>
<%--        <div class="js-modal__bg"></div>--%>
<%--        <div class="js-modal__main">--%>
<%--            <p><c:forEach var="fl" items="${frieadList}"><img src="img/tel.png"></c:forEach></p>--%>
<%--            <p></p>--%>
<%--            <p></p>--%>
<%--            <p></p>--%>
<%--            <p class="js-modal__btn--close--fix"></p>--%>
<%--        </div>--%>
<%--        <div class="com"><img src="img/pk.png" height="41px" width="35px"></div>--%>


<%--        <input id='message' type='text'>--%>
<%--        <input onclick='wsSendMessage();' value='Echo' type='button'>--%>
<%--        <div class="sendMessage">--%>
<%--            <textarea id='message' class="text_area" style="border: 0;">--%>
<%--            </textarea> <!-- ${inputText} -->--%>
<%--        </div>--%>
<%--        <label>--%>
<%--            <!-- この部分を表示させる -->--%>
<%--            <span class="filelabel">--%>
<%--       <i class="fab fa-adn"></i>送信--%>
<%--    </span>--%>
            <!-- この部分は表示上から隠す -->
<%--            <input type="submit" id="send">--%>
<%--        </label>--%>
        <div class="footer">
            <c:if test="${not empty inputText}">
                <div class="input">
                    <div>
                        <div class="sendPicture">
                            <label class="upload-img-btn">
                                <img class="change" src='<c:url value="/image/image.png"/>'>
                                <input type="file" id="myImage" accept="image/*">
                            </label>
                        </div>
                        <div class="sendMessage">
                            <img onclick="changePic()" src='<c:url value="/image/chat.png"/>'>
                        </div>
                    </div>
                    <div class="sendMessageBox">
                        <textarea id='message' class="text_area"></textarea>
                    </div>
                    <div class="sendPictureBox">
                            <%--                    <img id="preview">--%>
                    </div>
                    <div>
                        <img class="btn-flat-border" src="<c:url value='/image/send.png' />" onclick='wsSendMessage();' width="100%" height="100%"/>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty inputText}">
                <div class="input">
                    <div class="block_judge_area">
                        <span class="block_text">ブロック中</span>
                    </div>
                    <div class="judge_text">
                        <div class="sendPicture">
                            <label class="upload-img-btn">
                                <img class="change" src='<c:url value="/image/image.png"/>'>
                                <input type="file" id="myImage" accept="image/*">
                            </label>
                        </div>
                        <div class="sendMessage">
                            <img onclick="changePic()" src='<c:url value="/image/chat.png"/>'>
                        </div>
                    </div>
                    <div class="sendMessageBox judge_text">
                        <textarea id='message' class="text_area"></textarea>
                    </div>
                    <div class="sendPictureBox">
                    </div>
                    <div class="judge_text">
                        <img class="btn-flat-border" src="<c:url value='/image/send.png' />" onclick='wsSendMessage();' width="100%" height="100%"/>
                    </div>
                </div>
            </c:if>
        </div>







        <script>
            function changePic(){
                $(".sendPictureBox").css("display", "none");
                $(".sendMessageBox").css("display", "block");
                $(".sendPicture").css("display", "block");
                $(".sendMessage").css("display", "none");
                $("#message").val("");
            }
            $(function(){
                $("#myImage").on("change",function(){
                        $(".sendMessageBox").css("display", "none");
                        $(".sendPictureBox").css("display", "block");
                        $(".sendPicture").css("display", "none");
                        $(".sendMessage").css("display", "block");

                });
            });
            function ajaxFriendAdd(id){
                $.ajax({
                    url: "AjaxFriendAddServlet",
                    type: "GET",
                    data: {chat_id :id}
                }).done(function (result) {
                    $('#friendAdd').remove();
                }).fail(function () {
                    alert("読み込み失敗");
                }).always(function (result) {
                });
            }
            function ajaxFriendRelease(id){
                $.ajax({
                    url: "AjaxFriendReleaseServlet",
                    type: "GET",
                    data: {chat_id :id}
                }).done(function (result) {
                    $(".judge_text").css("display","block");
                    $(".block_judge_area").css("display","none");
                    $("#frienddDeleteOrBlock").empty();
                    ResurrectionMessage();
                }).fail(function () {
                    alert("読み込み失敗");
                }).always(function (result) {
                });
            }
            $(function () {
                window.scrollTo(0,document.body.scrollHeight);
            });
            var showFlag = 0;
            function sdContact() {
                if(showFlag===0){
                    $('#contact').css("display","block");
                    showFlag++;
                }else if(showFlag===1){
                    $('#contact').css("display","none");
                    showFlag--;
                }
            }
            var showImageFlag = 0;
            function sdImageContact() {
                if(showFlag===0){
                    $('#imageSend').css("display","block");
                    $('#sendMessage').css("display","none");
                    $('#sendPicture').text("テキスト");
                    showFlag++;
                }else if(showFlag===1){
                    $('#imageSend').css("display","none");
                    $('#sendMessage').css("display","block");
                    $('#sendPicture').text("写真");
                    showFlag--;
                }
            }
            $(function(){
                var modalBtn = $('.js-modal__btn');
                var modalBtnClose = $('.js-modal__btn--close');
                var modalBtnCloseFix = $('.js-modal__btn--close--fix');
                var modalBg = $('.js-modal__bg');
                var modalMain = $('.js-modal__main');
                modalBtn.on('click', function (e) {
                    $(this).next(modalBg).fadeIn();
                    $(this).next(modalBg).next(modalMain).removeClass("_slideDown");
                    $(this).next(modalBg).next(modalMain).addClass("_slideUp");
                });
                modalBtnClose.on('click', function (e) {
                    modalBg.fadeOut();
                    modalMain.removeClass("_slideUp");
                    modalMain.addClass("_slideDown");
                });
                modalBtnCloseFix.on('click', function (e) {
                    modalBg.fadeOut();
                    modalMain.removeClass("_slideUp");
                    modalMain.addClass("_slideDown");
                });
                modalMain.on('click', function (e) {
                    e.stopPropagation();
                });
                modalBg.on('click', function () {
                    $(this).fadeOut();
                    $(this).next(modalMain).removeClass("_slideUp");
                    $(this).next(modalMain).addClass("_slideDown");
                });
            });

            var webSocket = new WebSocket("ws://localhost:8080/legame/websocketendpoint");
            var echoText = document.getElementById("echoText");
            var message = document.getElementById("message");
            function ResurrectionMessage(){
                message = document.getElementById("message");
            }
            webSocket.onopen = function(message){ wsOpen(message);};
            webSocket.onmessage = function(message){ wsGetMessage(message);};
            webSocket.onclose = function(message){ wsClose(message);};
            webSocket.onerror = function(message){ wsError(message);};
            function wsOpen(message){
                // echoText.value += "Connected ... \n";
            }
            var i=1;
            function wsSendMessage(){
                console.log(message.value);
                webSocket.send(message.value);
                $("#preview").attr('src','');
                var str = message.value;
                if(message.value.length>=2000){
                    str = "<img src='"+message.value+"' >";
                }
                $(".line-bc").append("<div class='my_comment'>"+
                "<p>"+str+"</p>"+
                "</div>"+
                "<span class='item'>?</span>"+
                "<span>"+getDate()+"</span>");
                message.value = "";
                $('html, body').animate({
                    scrollTop: $(document).height()
                },0);
                return false;
            }
            function wsSendContact(name,user_id){
                webSocket.send("<a href='ProfilePageServlet?id="+user_id+"'>"+name+"</a>");
                $("#table").append("<a href='ProfilePageServlet?id="+user_id+"'>"+name+"</a>");
                message.value = "";
                $('html, body').animate({
                    scrollTop: $(document).height()
                },0);
                return false;
            }
            function wsCloseConnection(){
                webSocket.close();
            }
            function wsGetMessage(message){
                var str = message.data;
                if(message.data.length>=2000){
                    str = "<img src='"+message.data+"'>";
                }
                $(".line-bc").append("<div class='balloon6'>"+
                    "<div class='face_icon'>"+
                    "<img src='data:image;base64,${requestScope.yub.top_picture}'>"+
                    "</div>"+
                    "<div class='chatting'>"+
                    "<div class='says'>"+
                    "<p>"+str+"</p>"+
                    "</div>"+
                    "<div class='時間'>"+getDate()+"</div>"+
                    "</div>"+
                    "</div>");
                $('html, body').animate({
                    scrollTop: $(document).height()
                },0);
                return false;
            }
            function wsClose(message){
                // echoText.value += "Disconnect ... \n";
            }

            function wsError(message){
                // echoText.value += "Error ... \n";
            }
            function getDate(){
                var now = new Date();
                var y = now.getFullYear();
                var m = now.getMonth() + 1;
                var d = now.getDate();
                var h = now.getHours();
                var mi = now.getMinutes();
                console.log(y + '年' + m + '月' + d + '日' + h + '時' + mi + '分');
                return y + '年' + m + '月' + d + '日' + h + '時' + mi + '分';
            }



            $('#myImage').on('change', function (e) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $("#preview").attr('src', e.target.result);
                    $("#preview").css('display', 'block');
                    message.value=e.target.result;
                    $("#myImage").val("");
                };
                reader.readAsDataURL(e.target.files[0]);
            });
        </script>
<%--        <script src="<c:url value='/js/talk.js' />">--%>
    </c:param>
</c:import>