$(function(){
    $(".btn-border").on("click",function(){
        var new_pass = $("#new_pass").val();
        var confirm = $("#confirm").val();
        var mail = $("#mail").val();
        $(".mail_error").empty();
        $(".error").empty();
        if(new_pass!==confirm) {
            $(".error").css("display", "inline");
            $(".error").append("�p�X���[�h����v���Ă��܂���");
        }else if(confirm.length==0||new_pass.length==0){
            $(".error").css("display","inline");
            $(".error").append("�l����͂��Ă�������");
        }else if(mail.length==0){
            $(".mail_error").css("display","inline");
            $(".mail_error").append("<span>�l����͂��Ă�������</span>");
        }else if(!mail.match(/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/)){
            $(".mail_error").css("display","inline");
            $(".mail_error").append("<span>���[���̏����œ��͂��Ă�������</span>");
        }else if(confirm.length<8||new_pass.length<8){
            $(".error").css("display","inline");
            $(".error").append("8�����ȏ�œ��͂��Ă�������");
        }else{
            $('form').submit();
        }
    });
});