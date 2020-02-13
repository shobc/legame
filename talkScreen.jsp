<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="js/jquery-3.4.1.min.js"></script>
    <title>トーク一覧</title>
    <script>
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
                $('#frienddDeleteOrBlock').remove();
                $('#sendMessage').html('<input id="message" type="text">' +
                    '<input onclick=wsSendMessage(); value="Echo" type="button">');
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
    </script>
    <style>
        #contact{
            display: none;
        }
        #imageSend{
            display: none;
        }
    </style>
</head>
<body>
${friendJudge}
<h1><a href="ChatPageServlet" onclick="wsCloseConnection();">チャット一覧ページに戻る</a>トーク一覧</h1>
<table border="1" id="table">
    <tr><td>名前</td><td>画像</td><td>時間</td><td>内容</td><td>既読</td></tr>
    <c:forEach var="tl" items="${talkList}">
        <tr>
            <td>${tl.name}</td>
            <td style="width: 20%;height: 100%;"><img src="data:image;base64,${tl.image}" height="10%" width="100%" style="border: solid;"></td>
            <td>${tl.mess_time}</td>
            <td>${tl.content}</td>
            <td>${tl.read_flag}</td>
        </tr>
    </c:forEach>
</table>
<div id="imageSend">
    <img id="preview" width="10%" height="10%">
    <input type="file" id="myImage" accept="image/*">
    <input onclick='wsSendMessage();' value='Echo' type='button'>
</div>
<button id="sendPicture" onclick="sdImageContact()">写真</button>

<div id="sendMessage">
    ${inputText}
</div>
<button onclick="sdContact()">連絡先</button>
<div id="contact">
    <table>
        <c:forEach var="fl" items="${frieadList}">
                <tr>
                    <td style="width: 20%;height: 100%;"><img src="data:image;base64,${fl.top_picture}" height="10%" width="100%" style="border: solid;"></td>
                    <td>${fl.name}</td>
                    <td><button onclick="wsSendContact('${fl.name}','${fl.user_id}')">送信</button></td>
                </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
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
        webSocket.send(message.value);
        $("#preview").attr('src','');
        var str = message.value;
        if(message.value.length>=2000){
            str = "<img src='"+message.value+"' height='100px' width='100px'>";
        }
        $("#table").append("<tr id='#a"+i+"'><td>${sessionScope.ub.name}</td> "+
            "<td><img src='data:image;base64,${sessionScope.ub.top_picture}' style='height: 100px;width: 100px;'></td>'" +
            "<td>"+getDate()+"</td>" +
            "<td>"+str+"</td>" +
            "<td></td></tr>");
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
            str = "<img src='"+message.data+"' height='10%' width='10%'>";
        }
        $("#table").append("<tr><td>${requestScope.yub.name}</td>" +
            "<td><img src='data:image;base64,${requestScope.yub.top_picture}' style='height: 100px;width: 100px;'></td>" +
            "<td>"+getDate()+"</td>" +
            "<td>"+str+"</td>" +
            "<td></td></tr>");
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
            console.log(e.target.result);
            message.value=e.target.result;
            $("#myImage").val("");
        };
        reader.readAsDataURL(e.target.files[0]);
    });

</script>
</body>
</html>