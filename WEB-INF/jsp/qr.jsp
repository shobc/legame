<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/qr.css' />">
        <title>QRコード</title>
        <script>
            function accessJudge(){
                var ref = document.referrer;
                console.log(ref);
                if(ref.match(/PaymentQRCodeServlet/)){
                    location.href='WalletPageServlet';
                }else{
                    location.href=ref;
                }
            }
        </script>
    </c:param>
    <c:param name="content">
        <div class="header">
            <h2 id="caption">${title}</h2>
            <a class="back" href="#" onclick="accessJudge()"><img src="<c:url value='/image/back.png' />" width="20px"></a>
        </div>
        <div class="overall_qr">
            <center>
                <div class="supace">
                    <span class="blinking">カメラで読み取ってくだい</span>
                    <div class="qr">
                        <img src="<c:url value='/QRImage/${picURI}.png' />" height="40%" width="70%">
                    </div>
                </div>
            </center>
        </div>
        <c:if test="${pageContext.request.servletPath == '/payment'}">
            <div class="qrChange">
                <span >
                    <a class="change" href="PaymentQRCodeServlet">QRコードを更新する</a>
                </span>
            </div>
        </c:if>

    </c:param>
</c:import>