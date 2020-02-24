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
            $(".mail_error").append("<span>@の書式で入力してください</span>");
        }else if(confirm.length<8||new_pass.length<8){
            $(".error").css("display","inline");
            $(".error").append("8文字以上で入力してください");
        }else{
            $('form').submit();
        }
    });
    // $(document).on('keydown', '.not', function(e){
    //     let k = e.keyCode;
    //     let str = String.fromCharCode(k);
    //     if(!(str.match(/[0-9a-zA-Z]/) || e.shiftKey || (37 <= k && k <= 40) || k === 8 || k === 46)){
    //         return false;
    //     }
    // });
    // $(document).on('keyup', '.not', function(e){
    //     if(e.keyCode === 9 || e.keyCode === 16) return;
    //     this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
    // });
    //
    // $(document).on('blur', '.not',function(){
    //     this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
    // });
});