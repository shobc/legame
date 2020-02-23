$(function(){
    $(".btn-border").on("click",function(){
        var new_pass = $("#new_pass").val();
        var confirm = $("#confirm").val();
        var old_pass = $("#old_pass").val();
        if(new_pass!==confirm){
            $(".error").css("display","block");
            $(".error").empty();
            $(".error").append("<span>パスワードが一致していません</span>");
        }else if(!old_pass){
            console.log(11);
            $(".old_error").css("display","block");
            $(".old_error").empty();
            $(".old_error").append("<span>入力してください</span>");
        }else if(confirm.length==0||new_pass.length==0){
            $(".error").css("display","block");
            $(".error").empty();
            $(".error").append("<span>値を入力してください</span>");
        }else if(confirm.length<8||new_pass.length<8){
            $(".error").css("display","block");
            $(".error").empty();
            $(".error").append("<span>8文字以上で入力してください</span>");
        }else{
            $('form').submit();
        }
    });
});