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