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
    console.log($('#'+id));
    var Id = '#'+id;
    var count_judge = count;
    console.log(Id);
    console.log(count);
    $.ajax({
        url: 'LikeServlet',
        type: 'GET',
        data: {timeline_id :id,likeJudge:count}
    }).done(function (result) {
        if(count_judge==0){
            $(Id).attr("src","/legame/image/heart.png");
            $(Id).attr("onclick","ajaxLike("+id+",1)");
            var count = parseInt($("#c"+id).text());
            $("#c"+id).text(count+1);
        }else if(count_judge==1){
            $(Id).attr("src","/legame/image/white_heart.png");
            $(Id).attr("onclick","ajaxLike("+id+",0)");
            var count = parseInt($("#c"+id).text());
            $("#c"+id).text(count-1);
        }
    }).fail(function () {
        console.log("読み込み失敗");
    }).always(function (result) {
    });
}
function accessJudge(){
    var ref = document.referrer;
    console.log(ref);
    location.href=ref;
}
function sendPost(action,parameter,id){
    $('<form/>',{action:action, method:"post"})
        .append("<input type='hidden' name='"+parameter+"' value='"+id+"'>")
        .appendTo($('body'))
        .submit();
}