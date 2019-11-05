<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>プロフィール設定</title>

    <script type="text/javascript" language="javascript">
        function OnFileSelect( inputElement )
        {
            var fileList = inputElement.files;
            var imageList = "";
            var fileReader = new FileReader();
            var file = fileList[ 0 ];
            fileReader.onload = function() {
                imageList += "<p><img src=\"" + this.result + "\" height='400px' width='400px' /></p>\r\n";
                document.getElementById( "img" ).innerHTML = imageList;
            };
            fileReader.readAsDataURL( file );
        }
    </script>
</head>
<body>
<p>${requestScope.user_id}</p>
<h1>プロフィール設定</h1>
※必須
<form action="RegisterProfileServlet" enctype="multipart/form-data" method="post">
    <input type="hidden" name="user_id" value="${sessionScope.user_id}">
※名前:<input type="text" name="name"><br>
※ID:<input type="text" name="id"><br>
コメント:<input type="text" name="comment"><br>
画像:<input type="file"  onchange="OnFileSelect( this );" name="profileImage" id="profileImage" placeholder="写真を選択"><br>
    <p id="img" ></p>
    <input type="submit" value="送信">
</form>
</body>
</html>
