$(function(){
    $("#container").click(function(){
        $("#frinedList").slideToggle(400);  //ÉXÉâÉCÉhÉCÉìÇOÅCÇSïb
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
                if($(".noticeCount").text()!='new'){
                    $(".noticeCount").append(result);
                    noticeFlag++;
                }
            }
        }
    }).fail(function () {
        console.log("ì«Ç›çûÇ›é∏îs");
    }).always(function (result) {
    });
}
setInterval(FriendNotice, 1000);