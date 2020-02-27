<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/adminShop/shop-admin-top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/adminShop/login.css' />">
        <title>���X�̊Ǘ��҃��O�C��</title>
    </c:param>
    <c:param name="content">
        <div class="header">
            <div>
                <img src="<c:url value='/image/LegamePay_logo.png' />" alt="legame" width="30%" height="80%">
            </div>
        </div>
        <div class="admin_shop_login">
            <div class="group">
                <form action="ShopAdminHomeServlet" method="post">
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
                    <div class="submit_box">
                        <div class="submit_inner">
                            <input class="btn" type="submit" value="���O�C��">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="register_reminder">
            <div>
                <div>
                    <span>�p�X���[�h�Y�ꂽ����<a href="reminder-password">������</a></span>
                </div>
                <div class="register">
                    <span><a href="new-account">�V�K�o�^</a></span>
                </div>
            </div>
        </div>
    </c:param>
</c:import>