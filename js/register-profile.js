function OnFileSelect( inputElement )
{
    var fileList = inputElement.files;
    var imageList = "";
    var fileReader = new FileReader();
    var file = fileList[ 0 ];
    fileReader.onload = function() {
        imageList += "<p><img src=\"" + this.result + "\" height='400px' width='350px' onclick='trimming()' id='target' /></p>\r\n";
        document.getElementById( "img" ).innerHTML = imageList;
        $("#base64Image").val(this.result);
        $(".darkroom-toolbar").css("display","none");
    };
    fileReader.readAsDataURL( file );
}

function showTrimming(){
    $("#btn").css("display","block");
}
function trimming(){
    $(".darkroom-toolbar").css("display","block");
    $("#btn").get(0).onclick = "";
    var dkrm = new Darkroom('#target', {
        plugins: {
            crop: {
                minHeight: 50,
                minWidth: 50,
                ratio: 1
            },
            save: {
                callback: function(){
                    var newImage = dkrm.canvas.toDataURL();
                    $("#base64Image").val(newImage);
                    $(".darkroom-toolbar").css("display","none");
                    return false;
                }
            }
        }
    });
}
$(function(){
    $(".btn-border").on("click",function(){
        var user_name = $("#user_name").val();
        var search_id = $("#search_id").val();
        $(".error").empty();
        $(".name_error").empty();
        if(user_name.length===0){
            $(".name_error").css("display","inline");
            $(".name_error").empty();
            $(".name_error").append("“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
        }else if(search_id.length===0){
            $(".error").css("display","inline");
            $(".error").empty();
            $(".error").append("“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
        }else if(search_id.length<8){
            $(".error").css("display","inline");
            $(".error").empty();
            $(".error").append("8•¶ŽšˆÈã‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
        }else{
            $('form').submit();
        }
    });
    $("#btn").on("click",function(){
        $(".darkroom-toolbar").css("display","block");
    });
    $(document).on('keydown', '#search_id', function(e){
        let k = e.keyCode;
        let str = String.fromCharCode(k);
        if(!(str.match(/[0-9a-zA-Z]/) || e.shiftKey || (37 <= k && k <= 40) || k === 8 || k === 46)){
            return false;
        }
    });
    $(document).on('keyup', '#search_id', function(e){
        if(e.keyCode === 9 || e.keyCode === 16) return;
        this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
    });

    $(document).on('blur', '#search_id',function(){
        this.value = this.value.replace(/[^0-9a-zA-Z]+/i,'');
    });
});