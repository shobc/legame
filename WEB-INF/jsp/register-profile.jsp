<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/darkroom.css' />" />
        <link rel="stylesheet" href="<c:url value='/css/register-profile.css' />" />
        <title>�v���t�B�[���ݒ�</title>
        <script type="text/javascript" language="javascript">

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
                $("#btn").on("click",function(){
                    $(".darkroom-toolbar").css("display","block");
                });
            });
        </script>
    </c:param>
    <c:param name="content">
        <div class="caption">
            <h3>�v���t�B�[���ݒ�</h3>
        </div>
        <span>���K�{</span>
        <form action="RegisterProfileServlet" method="post">
            <input type="hidden" name="user_id" value="${user_id}">
            <div class="textStart">
                <label for="input" class="l_input">�����O</label>
                <input class="input" type="text" name="name" required>
                <div class="text_underline"></div>
            </div>
            <div class="textStart">
                <label for="input" class="l_input">������ID</label>
                <input class="input" type="text" name="id" required>
                <div class="text_underline"></div>
            </div>
            <div class="textStart">
                <label for="input" class="l_input">�R�����g</label>
                <input class="input" type="text" name="comment" required>
                <div class="text_underline"></div>
            </div>
            <p id="img" ></p>
            <p id="btn" onclick="trimming()">�g���~���O</p>
            <div class="textStart">
                <label class="upload-img-btn">
                    TOP�摜
                    <input type="file"  onchange="OnFileSelect(this); showTrimming();" id="profileImage">
                    <input type="hidden" name="base64Image" id="base64Image">
                </label>
            </div>
                <%--    �������Ă͂����Ȃ���--%>
            <script src="/legame/js/fabric.js"></script>
            <script src="/legame/js/darkroom.js"></script>
                <%--    �������Ă͂����Ȃ���--%>
            <div class="registerStart">
                <input class="btn-border" type="submit" value="�o�^">
            </div>
        </form>
    </c:param>
</c:import>