<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0">
    <title>名前を変更</title>
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
<h1>名前</h1>
<table>
    <img src="data:image;base64,${sessionScope.ub.top_picture}" style="height: 200px;width: 200px;"><br>
    <form action="ChangeTopPictureServlet"  enctype="multipart/form-data" method="post">
画像:<input type="file"  onchange="OnFileSelect( this );" name="profileImage" id="profileImage" placeholder="写真を選択"><br>
        <input type="submit" value="変更">
    </form>
</table>
<p id="img" ></p>
</body>
</html>