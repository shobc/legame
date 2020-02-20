<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/profile-setting-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/change-profile.css' />">
        <script>
            $(function(){
                var path = '${pageContext.request.servletPath}';
                if(path === '/change-name'){

                    $('#form').attr('action', 'ChangeNameServlet');
                    $('.text').attr('name', 'name');
                    $('.text').attr('value', '${sessionScope.ub.name}');
                    $("#caption").text('名前変更');

                }else if(path === '/change-searchId'){

                    $('#form').attr('action', 'ChangeSearchIdServlet');
                    $('.text').attr('name', 'search_id');
                    $('.text').attr('value', '${sessionScope.ub.search_id}');
                    $("#caption").text('検索ID変更');

                }else if(path === '/change-singleWord'){

                    $('#form').attr('action', 'ChangeSingleWordServlet');
                    $('.text').attr('name', 'single_word');
                    $('.text').attr('value', '${sessionScope.ub.single_word}');
                    $("#caption").text('一言変更');

                }else if(path === '/change-top-picture'){
                    $("#form").empty();
                    $(".imageChange").css('display','block');
                    $("#caption").text('TOP画像変更');

                }else if(path === '/change-password'){
                    $('#form').attr('action', 'ChangeNameServlet');
                    $('.text').attr('type', 'password');
                    $('.text').attr('name', 'confirm');
                    $('.text').before('パスワード(確認)');
                    $('#form').prepend('新しいパスワード<input type="password" class="text" name="new_pass" required><br>');
                    $('#form').prepend('古いパスワード<input type="password" class="text" name="old_pass" required><br>');
                    $("#caption").text('パスワード変更');
                    // $(".l_input").text('パスワード変更');
                }
            });
            function OnFileSelect( inputElement )
            {
                $("#btn-border").css('margin-top','10px');
                var fileList = inputElement.files;
                var imageList = "";
                var fileReader = new FileReader();
                var file = fileList[ 0 ];
                fileReader.onload = function() {
                    imageList += "<p><img src=\"" + this.result + "\" height='400px' width='400px' /></p>\r\n";
                    document.getElementById( "img" ).innerHTML = imageList;
                    $("#base64Image").val(this.result);
                    $("#profileImage").val();
                };
                fileReader.readAsDataURL( file );
            }
        </script>
        <title>プロフィール変更</title>
    </c:param>
    <c:param name="content">
        <div class="header">
                <h2 id="caption"></h2>
                <a class="back" href="profile-setting"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <form id="form" action="" method="post">
            <div id="changeArea">
                <label for="input" class="l_input"></label>
                <input type="text" class="text" name="" value="" required>
                <div class="text_underline"></div>
            </div>
            <div class="changeBtn">
                    <input type="submit" class="btn-border" value="変更">
            </div>
        </form>
        <div class="imageChange">
            <div class="image">
                <div class="imageInner">
                    <img class="top_picture" src="data:image;base64,${sessionScope.ub.top_picture}" height="10%"><br>
                </div>
            </div>
            <p id="img" ></p>
            <form action="ChangeTopPictureServlet" method="post">
                <div class="textStart">
                    <label class="upload-img-btn">
                        TOP画像
                        <input type="file"  onchange="OnFileSelect(this); showTrimming();" id="profileImage">
                        <input type="hidden" value="${sessionScope.ub.top_picture}" name="base64Image" id="base64Image">
                    </label>
                </div>
                <div id="changeBtn">
                    <input type="submit" id="btn-border" value="変更">
                </div>
            </form>
        </div>
    </c:param>
</c:import>