<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-admin-top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/adminShop/login.css' />">
        <title>���X�̊Ǘ��҃��O�C��</title>
        <style>
            .upload-img-btn {
                margin: 22px auto;
                padding: 15px;
                border-radius: 4px;
                text-align: center;
                display: block;
                color: rgba(5, 210, 5, 0.79);
                cursor: pointer;
            }
        </style>
    </c:param>
    <c:param name="content">
        <div class="header">
            <div>
                <h1>�o�^</h1>
            </div>
        </div>
        <div class="admin_shop_login">
            <div class="group">
                <form enctype="multipart/form-data" action="RegisterShopAccountServlet" method="post">
                    <div class="value_box">
                        <div class="value_inner">
                            <input class="insert" type="text" name="name" placeholder="���[�U�[��" required>
                        </div>
                    </div>
                    <div class="value_box">
                        <div class="value_inner">
                            <input class="insert" type="email" name="mail" placeholder="���[���A�h���X" required>
                        </div>
                    </div>
                    <div class="value_box">
                        <div class="value_inner">
                            <input class="insert" type="password" name="pass" placeholder="�p�X���[�h�����" required>
                        </div>
                    </div>
                    <div class="value_box">
                        <label class="upload-img-btn">
                            ���X�̋��摜
                            <input type="file" name="shop_image" multiple="multiple" accept="image/*" style="display: none" class="profileImage" required>
                        </label>
                    </div>
                    <div class="submit_box">
                        <div class="submit_inner">
                            <input class="btn" type="submit" value="�o�^">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </c:param>
</c:import>