$(function(){
    $(".btn-border").on("click",function(){
        var new_pass = $("#new_pass").val();
        var confirm = $("#confirm").val();
        var old_pass = $("#old_pass").val();
        if(new_pass!==confirm){
            $(".error").css("display","block");
            $(".error").empty();
            $(".error").append("<span>�p�X���[�h����v���Ă��܂���</span>");
        }else if(!old_pass){
            console.log(11);
            $(".old_error").css("display","block");
            $(".old_error").empty();
            $(".old_error").append("<span>���͂��Ă�������</span>");
        }else if(confirm.length==0||new_pass.length==0){
            $(".error").css("display","block");
            $(".error").empty();
            $(".error").append("<span>�l����͂��Ă�������</span>");
        }else if(confirm.length<8||new_pass.length<8){
            $(".error").css("display","block");
            $(".error").empty();
            $(".error").append("<span>8�����ȏ�œ��͂��Ă�������</span>");
        }else{
            $('form').submit();
        }
    });
});