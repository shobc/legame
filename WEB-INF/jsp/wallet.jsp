<%@page pageEncoding="windows-31j"
        contentType="text/html; charset=windows-31j"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:import url="/WEB-INF/jsp/top-layout.jsp">
    <c:param name="head">
        <link rel="stylesheet" href="<c:url value='/css/wallet.css' />">
    </c:param>
    <c:param name="content">
        <div class="titleStart">
            <div>
                <a class="back" href="AccessAppServlet"><img src="<c:url value='/image/back.png' />" width="20px"></a>
            </div>
            <div>
                <h2>LegPay</h2>
            </div>
            <div>
                <span><a href="PaymentQRCodeServlet"><img src="<c:url value='/image/qr.png' />" width="50px"></a></span>
            </div>
        </div>
        <div class="pay">
            <div>
                <span class="money">${bb.moneyTotal}円</span>
            </div>
            <div>
                <span>ポイント${bb.pointTotal}</span>
            </div>
        </div>
        <div class="">
            <form method="post" action="QRDepositMoneyServlet">
                <div class="pay_input">
                    <div class="cp_iptxt">
                        <label class="ef">
                            <input type="number" name="money" min="100" step="10" placeholder="金額を入力">
                        </label>
                    </div>
                    <div>
                        <input type="submit" class="submit_box" value="入金">
                    </div>
                </div>
            </form>
        </div>
        <div class="page">
            <c:if test="${not empty param['d-49653-p']}">
                <span>${param['d-49653-p']}ページ</span>
            </c:if>
            <c:if test="${empty param['d-49653-p']}">
                <span>1ページ</span>
            </c:if>
        </div>
        <div class="container">
            <div>
                <display:table name="${sessionScope.propertylist}" pagesize="15" >
                    <display:column property="money" title="" />
                    <display:column class="point" property="point" title=""  />
                    <display:column class="history" property="history" title=""  />
                    <display:column class="historydate" property="historydate" title="" />
                </display:table>
            </div>
        </div>
    </c:param>
</c:import>