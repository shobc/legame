var today = new Date();//�����̓��t���擾

//�����̐���
var year = today.getFullYear(); //2020
//�����̌�
var mon = today.getMonth() + 1;//1

window.onload = function () {

    $(function () {
            //�N
            //2017~2019
            for(i=2017; i<=year-1; i++){
                $("#year").append("<option value='"+i+"'>"+i+"</option>")
            }
            //2020
            $("#year").append("<option value='"+year+"' selected>"+year+"</option>");
        

            //��
            //1~12
            for(i=1; i<=mon-1; i++){
                $("#mon").append("<option value='"+i+"'>"+i+"</option>")
            }
            //13
            $("#mon").append("<option value='"+mon+"' selected>"+mon+"</option>");
    });
}

//onchange�ŌĂ�
function update(){

    var vyear = document.getElementsByName("year")[0];
    var now = vyear.options[vyear.selectedIndex].value;
    //�v�f���폜

        $("#mon").empty();

    
    //���݂̔N���I�����ꂽ�ꍇ
    if(now == year){//2020

        // $(function(){
        //     $("#year").empty();
        // });

        //�N

        // 2017~2019

        // for(i=2017; i<=year-1; i++){
        //     $("#year").append("<option value='"+i+"'>"+i+"</option>")
        // }
        // //2020
        // $("#year").append("<option value='"+year+"' selected>"+year+"</option>");

        //��
        //1~12
        for(i=1; i<=mon-1; i++){
            $("#mon").append("<option value='"+i+"'>"+i+"</option>");
        }
        //13
        $("#mon").append("<option value='"+mon+"' selected>"+mon+"</option>");
    
    //��N�ȑO�̔N���I�����ꂽ�ꍇ
    }else if(now < year){
        for(i=1; i<=12; i++){
            $("#mon").append("<option value='"+i+"'>"+i+"</option>");
        }
    //���N�ȍ~�̔N���I�����ꂽ�ꍇ
    }else if(now > year){
        $("#mon").append("<option disabled selected value>�I���ł��܂���</option>");
    }
}
