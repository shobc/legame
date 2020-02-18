<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<html>
<head>
    <title>�v���t�B�[���ݒ�</title>
    <link rel="stylesheet" href="/legame/css/darkroom.css" />
    <script src="/legame/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" language="javascript">

            function OnFileSelect( inputElement )
            {
                var fileList = inputElement.files;
                var imageList = "";
                var fileReader = new FileReader();
                var file = fileList[ 0 ];
                fileReader.onload = function() {
                    imageList += "<p><img src=\"" + this.result + "\" height='400px' width='400px' onclick='trimming()' id='target' /></p>\r\n";
                    document.getElementById( "img" ).innerHTML = imageList;
                    $("#base64Image").val(this.result);
                    $(".darkroom-toolbar").css("display","none");
                };
                fileReader.readAsDataURL( file );
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
               $("#btn").on("click",function(){
                   $(".darkroom-toolbar").css("display","block");
               });
            });
    </script>
</head>
<body>
<h1>�v���t�B�[���ݒ�</h1>
���K�{
<form action="RegisterProfileServlet" method="post">
    <input type="hidden" name="user_id" value="${user_id}">
�����O:<input type="text" name="name"><br>
��ID:<input type="text" name="id"><br>
�R�����g:<input type="text" name="comment"><br>
    <p id="btn" onclick="trimming()">�g���~���O</p>
    �摜:<input type="file"  onchange="OnFileSelect( this );" name="profileImage" id="profileImage" placeholder="�ʐ^��I��"><br>
    <p id="img" ></p>
    <input type="hidden" name="base64Image" id="base64Image">
<%--    �������Ă͂����Ȃ�--%>
    <script src="/legame/js/fabric.js"></script>
    <script src="/legame/js/darkroom.js"></script>
<%--    �������Ă͂����Ȃ�--%>
    <input type="submit" value="���M">
</form>

</body>
</html>
