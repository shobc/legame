$(function(){
    $(".btn-border").on("click",function(){
        var pass = $("#pass").val();
        var confirm = $("#confirm").val();
        if(confirm.length<8||pass.length<8){
            $(".error").css("display","inline");
            $(".error").empty();
            $(".error").append("8�����ȏ�œ��͂��Ă�������");
        }else if(pass!==confirm){
            $(".error").css("display","inline");
            $(".error").empty();
            $(".error").append("�p�X���[�h����v���Ă��܂���");
        }else{
            $('form').submit();
        }
    });
});