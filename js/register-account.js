$(function(){
    $(".btn-border").on("click",function(){
        var new_pass = $("#new_pass").val();
        var confirm = $("#confirm").val();
        var mail = $("#mail").val();
        $(".mail_error").empty();
        $(".error").empty();
        if(new_pass!==confirm) {
            $(".error").css("display", "inline");
            $(".error").append("パスワードが一致していません");
        }else if(confirm.length==0||new_pass.length==0){
            $(".error").css("display","inline");
            $(".error").append("値を入力してください");
        }else if(mail.length==0){
            $(".mail_error").css("display","inline");
            $(".mail_error").append("<span>値を入力してください</span>");
        }else if(!mail.match(/^([a-zA-Z0-9])+([a-zA-Z0-9\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\._-]+)+$/)){
            $(".mail_error").css("display","inline");
            $(".mail_error").append("<span>メールの書式で入力してください</span>");
        }else if(confirm.length<8||new_pass.length<8){
            $(".error").css("display","inline");
            $(".error").append("8文字以上で入力してください");
        }else{
            $('form').submit();
        }
    });
});