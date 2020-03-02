$(function(){
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
});


function profileInfo(action,id){
    $('<form/>',{action:action, method:"post"})
        .append("<input type='hidden' name='friend_id' value='"+id+"'>")
        .appendTo($('body'))
        .submit();
}