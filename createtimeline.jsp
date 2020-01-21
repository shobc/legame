<%@page pageEncoding="Windows-31J"
        contentType="text/html; charset=Windows-31J"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
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
    <title>ìäçe</title>
</head>
<body>
<p id="img" ></p>
    <form action="CreateTimeLineServlet" method="post"  enctype="multipart/form-data">
        âÊëú:<input type="file" multiple="multiple" accept="image/*" onchange="OnFileSelect( this );" name="timelineImage" id="profileImage" placeholder="é ê^ÇëIë"><br>
        <input type="text" name="timeline_sentence"><br>
        <input type="submit" value="ìäçe">
    </form>
</body>
</html>