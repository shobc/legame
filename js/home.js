$(function(){
    $("#container").click(function(){
        $("#frinedList").slideToggle(400);  //�X���C�h�C���O�C�S�b
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
        alert("�ǂݍ��ݎ��s");
    }).always(function (result) {
    });
}
setInterval(FriendNotice, 1000);