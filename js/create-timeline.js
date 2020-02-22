$(function(){
    $('#icon').on('change',function(){
        $(".slide-wrap").empty();
        if ( !this.files.length ) {
            return;
        }if($(this)[0].files.length>=11){
            $(".slide-wrap").append('<p id="image_error">‰æ‘œ‚Í10–‡‚µ‚©“ü‚ê‚ç‚ê‚Ü‚¹‚ñ</p>');
            $('input[type=file]').val('');
        }else{
            $('#img').text('');
            var $files = $(this).prop('files');
            var len = $files.length;
            for ( var i = 0; i < len; i++ ) {
                var file = $files[i];
                var fr = new FileReader();
                fr.onload = function(e) {
                    var src = e.target.result;
                    var img = '<img class="image" src="'+ src +'" >';
                    $(".slide-wrap").append('<div class="slide-box"><p id="img" >'+img+'</p></div>');
                };
                fr.readAsDataURL(file);
            }
            $('#img').css('display','block');
        }
    });
    $("#create_timeline_btn").on("click",function(){
        fileCheck = $("#icon").val().length;
        if(fileCheck!=0){
            $(".timeline_sentence").removeAttr("required");
        }
    });
    $('#icon').on('change',function(){
        if($(this)[0].files.length>0&&$(this)[0].files.length<=10){
            $("#create_timeline_btn").css("color","black");
        }else if($(".timeline_sentence")!=null&&$(".timeline_sentence").val().length!=0){
            $("#create_timeline_btn").css("color","black");
        }else{
            $("#create_timeline_btn").css("color","#b7b7b7");
        }
    });
    $('.timeline_sentence').on('keyup',function(){
        if($(this)!=null&&$(this).val().length!=0){
            $("#create_timeline_btn").css("color","black");
        }else if($("#icon")[0].files.length>0&&$("#icon")[0].files.length<=10){
            $("#create_timeline_btn").css("color","black");
        }else{
            $("#create_timeline_btn").css("color","#b7b7b7");
        }
    });
});
