<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>

<html>
<head>
    <title>�v���t�B�[���ݒ�</title>
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
<h1>�v���t�B�[���ݒ�</h1>
���K�{
<form action="RegisterProfileServlet" enctype="multipart/form-data" method="post">
    <input type="hidden" name="user_id" value="${user_id}">
�����O:<input type="text" name="name"><br>
��ID:<input type="text" name="id"><br>
�R�����g:<input type="text" name="comment"><br>
�摜:<input type="file"  onchange="OnFileSelect( this );" name="profileImage" id="profileImage" placeholder="�ʐ^��I��"><br>
    <p id="img" ></p>
    <input type="submit" value="���M">
</form>
</body>
</html>
