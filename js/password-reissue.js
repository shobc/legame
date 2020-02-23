$(function(){
    $(".btn-border").on("click",function(){
        var pass = $("#pass").val();
        var confirm = $("#confirm").val();
        if(confirm.length<8||pass.length<8){
            $(".error").css("display","inline");
            $(".error").empty();
            $(".error").append("8文字以上で入力してください");
        }else if(pass!==confirm){
            $(".error").css("display","inline");
            $(".error").empty();
            $(".error").append("パスワードが一致していません");
        }else{
            $('form').submit();
        }
    });
});