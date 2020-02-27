var today = new Date();//今日の日付を取得

//今日の西暦
var year = today.getFullYear(); //2020
//今日の月
var mon = today.getMonth() + 1;//1

window.onload = function () {

    $(function () {
            //年
            //2017~2019
            for(i=2017; i<=year-1; i++){
                $("#year").append("<option value='"+i+"'>"+i+"</option>")
            }
            //2020
            $("#year").append("<option value='"+year+"' selected>"+year+"</option>");
        

            //月
            //1~12
            for(i=1; i<=mon-1; i++){
                $("#mon").append("<option value='"+i+"'>"+i+"</option>")
            }
            //13
            $("#mon").append("<option value='"+mon+"' selected>"+mon+"</option>");
    });
}

//onchangeで呼ぶ
function update(){

    var vyear = document.getElementsByName("year")[0];
    var now = vyear.options[vyear.selectedIndex].value;
    //要素を削除

        $("#mon").empty();

    
    //現在の年が選択された場合
    if(now == year){//2020

        // $(function(){
        //     $("#year").empty();
        // });

        //年

        // 2017~2019

        // for(i=2017; i<=year-1; i++){
        //     $("#year").append("<option value='"+i+"'>"+i+"</option>")
        // }
        // //2020
        // $("#year").append("<option value='"+year+"' selected>"+year+"</option>");

        //月
        //1~12
        for(i=1; i<=mon-1; i++){
            $("#mon").append("<option value='"+i+"'>"+i+"</option>");
        }
        //13
        $("#mon").append("<option value='"+mon+"' selected>"+mon+"</option>");
    
    //昨年以前の年が選択された場合
    }else if(now < year){
        for(i=1; i<=12; i++){
            $("#mon").append("<option value='"+i+"'>"+i+"</option>");
        }
    //来年以降の年が選択された場合
    }else if(now > year){
        $("#mon").append("<option disabled selected value>選択できません</option>");
    }
}
