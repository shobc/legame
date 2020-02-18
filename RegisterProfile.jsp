<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<html>
<head>
    <title>プロフィール設定</title>
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
<h1>プロフィール設定</h1>
※必須
<form action="RegisterProfileServlet" method="post">
    <input type="hidden" name="user_id" value="${user_id}">
※名前:<input type="text" name="name"><br>
※ID:<input type="text" name="id"><br>
コメント:<input type="text" name="comment"><br>
    <p id="btn" onclick="trimming()">トリミング</p>
    画像:<input type="file"  onchange="OnFileSelect( this );" name="profileImage" id="profileImage" placeholder="写真を選択"><br>
    <p id="img" ></p>
    <input type="hidden" name="base64Image" id="base64Image">
<%--    動かしてはいけない--%>
    <script src="/legame/js/fabric.js"></script>
    <script src="/legame/js/darkroom.js"></script>
<%--    動かしてはいけない--%>
    <input type="submit" value="送信">
</form>

</body>
</html>
