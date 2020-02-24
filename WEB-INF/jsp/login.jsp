<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"
        isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/jsp/non-login-layout.jsp">
    <c:param name="head">
        <title>���O�C��</title>
    </c:param>
    <c:param name="content">
        <form action="/legame/HomePageServlet" method="post">
            <!--���[���A�h���X�̘g-->
            <div class="mallStart">
                <label for="input" class="l_input">���[���A�h���X</label>
                <input class="input" placeholder="xxx@legame.co.jp" type="email" name="mail" required>
                <div class="text_underline"></div>
            </div>
            <!--�p�X���[�h�̘g-->
            <div class="passStart">
                <label for="input" class="l_input">�p�X���[�h</label>
                <input class="input" type="password" name="pass" required>
                <div class="text_underline"></div>
            </div>
                <div class="error_message">
                    <p>${pageContext.exception.message}</p>
                </div>
                <!-- ���O�C���{�^�� -->
            <div class="loginStart">
                <input class="btn-border" type="submit" value="���O�C��">
            </div>
        </form>
        <!-- �t�b�^�[ -->
        <div class="footer">
            <center>�p�X���[�h��Y�ꂽ����<a href="mail" >������</a></center><br>
            <center><nav><a href="register-account">�V�K����o�^</a></nav></center>
        </div>
    </c:param>
</c:import>