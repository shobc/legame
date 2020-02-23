$(function(){
    $('.single').slick({
        autoplay: false,
        accessibility:false,
        arrows:false,
        dots: true,
        infinite: false,
    });
});
function ajaxLike(id,count){
    var Id = "#"+id;
    var count_judge = count;
    $.ajax({
        url: "LikeServlet",
        type: "GET",
        data: {timeline_id :id,likeJudge:count_judge}
    }).done(function (result) {
        if(count_judge==0){
            $(Id).attr("src",heard_url);
            $(Id).attr("onclick","ajaxLike("+id+",1)");
            var count = parseInt($("#c"+id).text());
            $("#c"+id).text(count+1);
        }else if(count_judge==1){
            $(Id).attr("src",white_heard_url);
            $(Id).attr("onclick","ajaxLike("+id+",0)");
            var count = parseInt($("#c"+id).text());
            $("#c"+id).text(count-1);
        }
    }).fail(function () {
        console.log("読み込み失敗");
    }).always(function (result) {
    });
}
var reply_user_name = null;
var user_id;
function reply(name,id) {
    if($("#message").val().indexOf(reply_user_name)){
        $("#message").val("@"+name+" "+$("#message").val());
        reply_user_name = "@"+name+" ";
        user_id = id;
    }
}

function ajaxLikeComment(tid,cid,count){
    var Id = "#"+(tid+cid)+"comment";
    var id = (tid+cid);
    var timeline_id = tid;
    var comment_id = cid;
    var count_judge = count;
    $.ajax({
        url: "CommentLikeServlet",
        type: "POST",
        data: {timeline_id :tid,comment_id :cid,likeJudge:count_judge}
    }).done(function (result) {
        if(count_judge===0){
            $(Id).attr("src",heard_url);
            $(Id).attr("onclick","ajaxLikeComment("+timeline_id+","+comment_id+",1)");
            var count = parseInt($("#c"+id).text());
            $("#c"+id).text(count+1);
        }else if(count_judge===1){
            $(Id).attr("src",white_heard_url);
            $(Id).attr("onclick","ajaxLikeComment("+timeline_id+","+comment_id+",0)");
            var count = parseInt($("#c"+id).text());
            $("#c"+id).text(count-1);
        }
    }).fail(function () {
        alert("読み込み失敗");
    }).always(function (result) {
    });
}
function profilePage(action,id){
    $('<form/>',{action:action, method:"post"})
        .append("<input type='hidden' name='id' value='"+id+"'>")
        .appendTo($('body'))
        .submit();
}
function ajaxReportTimeLine(id){
    $.ajax({
        url: "AjaxReportTimeLineServlet",
        type: "GET",
        data: {timeline_id :id}
    }).done(function (result) {
    }).fail(function () {
        alert("読み込み失敗");
    }).always(function (result) {
    });
}