$(function(){
    if(path === '/change-name'){

        $('#form').attr('action', 'ChangeNameServlet');
        $('.text').attr('name', 'name');
        $('.text').attr('maxlength', '10');
        $('.text').attr('value', name);
        $("#caption").text('名前変更');
        $("#setting").text('10文字以内');

    }else if(path === '/change-searchId'){

        $('#form').attr('action', 'ChangeSearchIdServlet');
        $('.text').attr('name', 'search_id');
        $(".text").addClass("not");
        $(".btn-borde").addClass("search_btn");
        $('.text').attr('value', search_id);
        $("#caption").text('検索ID変更');
        $("#setting").text('8文字以上20文字以内');
        $(".btn-border").attr('type',"button");

    }else if(path === '/change-singleWord'){

        $('#form').attr('action', 'ChangeSingleWordServlet');
        $('.text').attr('name', 'single_word');
        $('.text').removeAttr('required');
        $('.text').removeAttr('minlength');
        $('.text').attr('value', single_word);
        $("#caption").text('一言変更');

    }else if(path === '/change-top-picture'){
        $("#form").empty();
        $(".imageChange").css('display','block');
        $("#caption").text('TOP画像変更');

    }else if(path === '/change-password'){
        $('#form').attr('action', 'ChangeNameServlet');
        $('.text').attr('type', 'password');
        $('.text').attr('name', 'confirm');
        $('.text').before('パスワード(確認)');
        $('#form').prepend('新しいパスワード<input type="password" class="text" name="new_pass" required><br>');
        $('#form').prepend('古いパスワード<input type="password" class="text" name="old_pass" required><br>');
        $("#caption").text('パスワード変更');
        // $(".l_input").text('パスワード変更');
    }
    $(document).on('keydown', '.not', function(e){
        let k = e.keyCode;
        let str = String.fromCharCode(k);
        if(!(str.match(/[0-9a-zA-Z]/) || e.shiftKey || (37 <= k && k <= 40) || k === 8 || k === 46)){
            return false;
        }
    });
    $(document).on('keyup', '.not', function(e){
        if(e.keyCode === 9 || e.keyCode === 16) return;
        this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
    });

    $(document).on('blur', '.not',function(){
        this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
    });
    $(".search_btn").on("click",function(){
        var search_id = $(".text").val();
        if(search_id.length<8){
            $("#error").css("display","inline");
            $("#error").empty();
            $("#error").append("8文字以上で入力してください");
        }else{
            $('#form').submit();
        }
    });
});
function OnFileSelect( inputElement )
{
    $("#btn-border").css('margin-top','10px');
    var fileList = inputElement.files;
    var imageList = "";
    var fileReader = new FileReader();
    var file = fileList[ 0 ];
    fileReader.onload = function() {
        imageList += "<p><img src=\"" + this.result + "\" height='' width='375px' /></p>\r\n";
        document.getElementById( "img" ).innerHTML = imageList;
        $("#base64Image").val(this.result);
        $("#profileImage").val();
    };
    fileReader.readAsDataURL( file );
}
