$(function(){
    $("#container").click(function(){
        $("#frinedList").slideToggle(400);  //スライドイン０，４秒
        setTimeout(function(){
            $("#up , #down").toggleClass("active passive");
        },250);
    });
});

function profilePage(action,id){
    $('<form/>',{action:action, method:"post"})
        .append("<input type='hidden' name='id' value='"+id+"'>")
        .appendTo($('body'))
        .submit();
}
var noticeFlag = 0;
function FriendNotice(){
    $.ajax({
        url: "AjaxFriendNoticeServlet",
        type: "GET",
        data: {}
    }).done(function (result) {
        if(noticeFlag===0){
            if(result=='new'){
                if($("#notice").text()!='new'){
                    $("#notice").append(result);
                    noticeFlag++;
                }
            }
        }
    }).fail(function () {
        alert("読み込み失敗");
    }).always(function (result) {
    });
}
setInterval(FriendNotice, 1000);